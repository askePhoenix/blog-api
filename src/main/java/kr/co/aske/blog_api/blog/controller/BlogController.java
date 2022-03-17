package kr.co.aske.blog_api.blog.controller;

import kr.co.aske.blog_api.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService service;


}
