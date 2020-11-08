package com.ming.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * 以上代码中 @Profile({"dev", "test"})注解是在开发环境和测试环境的
 * 时候加载该类，线上生产环境为安全不建议创建swagger的bean
 *
 * @author Jiang Zaiming
 * @date 2019/11/25 5:30 下午
 */
@Configuration
//@Profile({"dev", "test"})
public class SwaggerTwoConfig {

    @Value("${swagger2.basePackage:com.ming.blog.controller}")
    private String swagger2BasePackage;
    @Value("${swagger2.title:系统API文档}")
    private String swagger2Title;
    @Value("${swagger2.api.version:2.0}")
    private String apiVersion;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("spring-test-interface")
                //加载配置信息
                .apiInfo(apiInfo())
                //设置全局参数
                .globalOperationParameters(globalParamBuilder())
                //设置全局响应参数
                .globalResponseMessage(RequestMethod.GET, responseBuilder())
                .globalResponseMessage(RequestMethod.POST, responseBuilder())
                .globalResponseMessage(RequestMethod.PUT, responseBuilder())
                .globalResponseMessage(RequestMethod.DELETE, responseBuilder())
                .globalResponseMessage(RequestMethod.OPTIONS, responseBuilder())
                .select()
                //加载swagger 扫描包
                .apis(RequestHandlerSelectors.basePackage(swagger2BasePackage))
                .paths(PathSelectors.any()).build()
                //设置安全认证
                .securitySchemes(securitySchemes());
    }

    /**
     * 获取swagger创建初始化信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("Mr_JiangZM", "", "Mr_JiangZM@163.com");
        return new ApiInfoBuilder()
                .title(swagger2Title)
                .version(apiVersion)
                .contact(contact)
                .description("测试swagger2文档")
                .build();
    }

    /**
     * 安全认证参数
     *
     * @return
     */
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("token", "令牌", "header"));
        return apiKeyList;
    }

    /**
     * 构建全局参数列表
     *
     * @return
     */
    private List<Parameter> globalParamBuilder() {
        List<Parameter> pars = new ArrayList<>();
        pars.add(parameterBuilder("token", "令牌", "string", "header", false).build());
        return pars;
    }

    /**
     * 创建参数
     *
     * @return
     */
    private ParameterBuilder parameterBuilder(String name, String desc, String type, String parameterType, boolean required) {
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name(name).description(desc).modelRef(new ModelRef(type)).parameterType(parameterType).required(required).build();
        return tokenPar;
    }

    /**
     * 创建全局响应值
     *
     * @return
     */
    private List<ResponseMessage> responseBuilder() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("响应成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());
        return responseMessageList;
    }


    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }


}
