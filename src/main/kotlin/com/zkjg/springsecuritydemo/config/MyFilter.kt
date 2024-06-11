package com.zkjg.springsecuritydemo.config

import jakarta.annotation.Resource
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * @Author jw
 * @Date 2024/6/7
 */
@Component
class MyFilter : OncePerRequestFilter() {

    @Resource
    lateinit var authenticationManager: AuthenticationManager

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken("123456", "zs")
        authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        SecurityContextHolder.getContext()
            .setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response)
    }
}