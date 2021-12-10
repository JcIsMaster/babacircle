package com.example.babacircle.common.utils;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author JC
 * @date 2021/9/24 17:32
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权（链式编程）
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可访问，功能页面对应有权限用户开放
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level/**").hasRole("v1");
        //没有权限则跳转至登录页
        http.formLogin();

        //关闭csrf
        http.csrf().disable();
        //开启注销
        http.logout();
        //开启记住我cookie,默认保存2周
        http.rememberMe();

    }

    /**
     * 认证
     * @param auth
     * @throws Exception
     * 出现密码编码报错时 PasswordEncoder 设置密码加密
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存中虚拟的用户
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("v1")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123123")).roles("v1","v2","v3");
    }
}
