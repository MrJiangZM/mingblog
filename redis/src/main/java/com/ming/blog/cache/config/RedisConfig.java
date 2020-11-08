package com.ming.blog.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 一下类似于上面差不多

    /**
     * RedisTemplate配置
     * jdk序列方式，用来保存对象(key=对象)
     * @param factory
     * @return
     */
	  /*@Bean
	  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

	   StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
	   //自定义的redis序列化工具类（对object序列化）
       RedisObjectSerializer redisObjectSerializer = new RedisObjectSerializer();
       RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
       template.setConnectionFactory(factory);
       template.setKeySerializer(stringRedisSerializer);
       template.setValueSerializer(redisObjectSerializer);

	   return template;
	  } */
    /**
     * RedisTemplate配置
     * string的序列化方式，存储string格式（key=value）
     * @param factory
     * @return
     */
	  /*@Bean
	  public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
	   StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
	   stringRedisTemplate.setConnectionFactory(factory);
	   setSerializer(stringRedisTemplate);
	   stringRedisTemplate.afterPropertiesSet();
	   return stringRedisTemplate;
	  }*/

	  /*private void setSerializer(StringRedisTemplate template){
	        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
            Jackson2JsonRedisSerializer(Object.class);
	        ObjectMapper om = new ObjectMapper();
	        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	        jackson2JsonRedisSerializer.setObjectMapper(om);
	        template.setValueSerializer(jackson2JsonRedisSerializer);
    }*/

    /**
     * redis模板，存储关键字是字符串，值是Jdk序列化,这个写法和我com.jary.util中的RedisObjectSerializer写法更简单，测试后选择使用那个
     * @Description:
     * @param factory
     * @return
     */
//    @Bean
//    public RedisTemplate<?,?> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<?,?> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//        //key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误；
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
//        redisTemplate.setKeySerializer(redisSerializer);
//        redisTemplate.setHashKeySerializer(redisSerializer);
//
//        //JdkSerializationRedisSerializer序列化方式;
//        JdkSerializationRedisSerializer jdkRedisSerializer=new JdkSerializationRedisSerializer();
//        redisTemplate.setValueSerializer(jdkRedisSerializer);
//        redisTemplate.setHashValueSerializer(jdkRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

}
