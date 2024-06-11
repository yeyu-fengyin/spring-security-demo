package com.zkjg.springsecuritydemo.bean

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

/**
 * @Author jw
 * @Date 2024/6/7
 */
class User(private var passwrod: String, private var userName: String) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList()
    }

    override fun getPassword(): String {
        return this.passwrod
    }

    override fun getUsername(): String {
        return this.userName
    }
}