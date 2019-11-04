package com.lizhi.demo;

import com.lizhi.demo.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * @author: lizhi
 * @Date: 2019/11/1 15:22
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayFilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayFilterApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    /**
     * 自定义路由映射规则
     *  默认情况下，Zuul自动为服务创建的路由表
     达式会采用服务名作为前缀， 比如针对上面的userservice-vl 和userservice-v2, 它会产生/userservice-vl 和/userservice-v2两个路径表达式来映射，但是这样生 成出来的表达式规则较为单一，不利于通过路径规则来进行管理。
     通常的做法是为这些不
     同版本的微服务应用生成以版本代号作为路由前缀定义的路由规则， 比如 /vl/userservice/。 这时候，通过这样具有版本号前缀的URL路径，我们就可以很容
     易地通过路径表达式来归类和管理这些具有版本信息的微服务了。
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^ .+)-(?<version>v.+$)", "${version}/${name}");
    }
}
