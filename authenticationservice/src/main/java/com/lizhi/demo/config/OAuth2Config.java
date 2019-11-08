package com.lizhi.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author: lizhi
 * @Date: 2019/11/7 10:38
 * @Description: 用于哪些应用程序可以使用服务
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    /**
     * 改configure 来配置哪些客户端允许访问由OAuth服务保护的服务
     *
     * ClientDetailsServiceConfigurer 类支持两种不同类型的存储， 内存存储和jdbc 存储，
     * 对于本类来说， 我们将使用 clients.inMemory() 存储
     *
     * withClient("eagleeye")、secret("thisissecret") 提供了注册应用的名称（eagleeye）以及秘钥（thisissecret）
     * 该秘钥字啊EagleEye 应用程序调用OAuth2服务器以接受OAuth2访问令牌时提供
     * authorizedGrantTypes("refresh_token", "password", "client_credentials") 以逗号分隔的授权类型列表，
     * 这些授权类型有OAuth2服务支持，在这个服务中，我们将支持密码授权类型和客户端凭据授权类型
     * scopes("webclient", "mobileclient"); 用于调用应用程序在请求OAuth2服务器获取访问令牌时可以操作的范围
     */

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("eagleeye")
                .secret("thisissecret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
