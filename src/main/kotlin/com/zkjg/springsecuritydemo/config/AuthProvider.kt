package com.zkjg.springsecuritydemo.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

/**
 * @Author jw
 * @Date 2024/6/7
 */
class AuthProvider(private var userDetailsService: UserDetailsService, private var encoder: PasswordEncoder) :
    AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val loadUserByUsername = userDetailsService.loadUserByUsername(authentication?.name)
        println(loadUserByUsername)
        val encode = encoder.encode(loadUserByUsername.password)
        println(encode)
        return UsernamePasswordAuthenticationToken(
            loadUserByUsername.username,
            loadUserByUsername.password,
            Collections.emptyList()
        );
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}