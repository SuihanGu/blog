package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Blog控制器
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    @GetMapping
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order, //顺序
                            @RequestParam(value = "tag", required = false) Long tag) {
        System.out.print("order:" + order + ";tag:" + tag);
        return "redirect:/index?order=" + order + "&tag=" + tag;
    }
}
