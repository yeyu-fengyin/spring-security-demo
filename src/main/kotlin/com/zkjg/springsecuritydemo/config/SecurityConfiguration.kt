package com.zkjg.springsecuritydemo.config

import com.zkjg.springsecuritydemo.bean.User
import jakarta.annotation.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * @Author jw
 * @Date 2024/6/7
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Resource
    lateinit var userDetailsService: UserDetailsService

    @Resource
    lateinit var myFilter: MyFilter

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/test").permitAll()
                    .anyRequest().authenticated()
//                it.anyRequest().permitAll()
            }
            .httpBasic {
                it.disable()
            }

//            .formLogin {
//                it.loginPage("/login")
//                    .permitAll()
//            }// 启用表单登录
            .formLogin {
                it.disable()
            }
        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        return AuthProvider(userDetailsService, passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun userDetails(): UserDetails {
        return User("123456", "zs")
    }
}