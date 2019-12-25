package com.wxwyz.springboot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //SpringSecurity授权认证
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //跳转权限设置
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/bus/**").permitAll()
                .antMatchers("/business/**").permitAll()
                .antMatchers("/delete/**").permitAll()
                .antMatchers("/comment/**").permitAll()
                .antMatchers("/job/**").permitAll()
                .antMatchers("/add/**").permitAll()
                .antMatchers("/info/**").permitAll()
                .antMatchers("/admin/manager").hasRole("admin")
                .antMatchers("/admin/all/**").hasRole("admin")
                .antMatchers("/admin/delete/**").hasRole("admin");

        //跳转到登录界面
        http.formLogin();
        /**
         * 自定义权限登录界面
         * loginPage 用户自定义登录界面
         * usernameParameter 传递的用户名（name属性）
         * passwordParameter 传递的密码（name属性）
         * loginProcessingUrl 最终跳转的界面
         */
//        http.formLogin().loginPage("/toLogin")
//                .usernameParameter("user")
//                .passwordParameter("pwd")
//                .loginProcessingUrl("/login");

        //跳转到登出界面 /logout
        http.csrf().disable();//关闭 防止网站被GET请求攻击的功能
        http.logout().logoutSuccessUrl("/");

        //记住我
        http.rememberMe();

        //自定义记住我
        //rememberMeParameter 自定义checkBox属性名
        http.rememberMe().rememberMeParameter("remember");

    }

    //认证
    //passwordEncoder 编码 开启密码加密 BCryptPasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("admin");
    }

}
