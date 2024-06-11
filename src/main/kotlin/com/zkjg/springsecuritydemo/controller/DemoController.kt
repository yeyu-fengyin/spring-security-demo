package com.zkjg.springsecuritydemo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @Author jw
 * @Date 2024/6/7
 */
@RestController
class DemoController {
    @GetMapping("test")
    fun test(@RequestParam name: String): String {
        return "hello $name"
    }
}