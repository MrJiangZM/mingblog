package com.ming.blog.config.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.blog.config.common.CommonException;
import com.ming.blog.config.common.ResponseStatusEnum;
import com.ming.blog.config.security.AuthenticationBean;
import com.ming.blog.dao.UserDao;
import com.ming.blog.domain.UserDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 重写内置的登录接口功能
 *
 * @author Jiang Zaiming
 * @date 2020/4/1 9:58 上午
 */
@Slf4j
//@Component
public class MyLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    @Autowired
//    private UserDao userDao;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //attempt Authentication when Content-Type is json
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {

            // use jackson or fastjson to deserialize json
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream ins = request.getInputStream()) {
//                AuthenticationBean authenticationBean = mapper.readValue(ins,AuthenticationBean.class);
                AuthenticationBean authenticationBean = JSONObject.parseObject(ins, AuthenticationBean.class);

                // 验证用户名密码信息
//                String username = authenticationBean.getUsername();
//                String password = authenticationBean.getPassword();
//                UserDB userDB = userDao.findByUsername(username);
//                if (userDB == null) {
//                    log.error("cannot get user by username {}", username);
//                    throw new CommonException(ResponseStatusEnum.RESOURCE_NOT_FOUND, "用户名或密码错误");
//                }
//                userDB

                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUsername(), authenticationBean.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            }
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            // 如果不是json的形式，使用原有的方法
            // transmit it to UsernamePasswordAuthenticationFilter
            return super.attemptAuthentication(request, response);
        }
    }

}
