package com.zkjg.springsecuritydemo.service.impl

import com.zkjg.springsecuritydemo.bean.User
import com.zkjg.springsecuritydemo.service.LoginService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

/**
 * @Author jw
 * @Date 2024/6/7
 */
@Service
class LoginServiceImpl : LoginService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return User("123456", "zs")
    }
}