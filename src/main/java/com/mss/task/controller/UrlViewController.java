package com.mss.task.controller;

import com.mss.task.service.url.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UrlViewController {

    private final UrlService urlService;

    @GetMapping
    public String urlConvertingView() {
        return "index";
    }

    @GetMapping("{shortUrl:[0-9a-zA-Z]+}")
    public String urlRedirect(@PathVariable String shortUrl) {
        System.out.println(shortUrl);
        return urlService.redirectToOriginUrl(shortUrl);
    }
}
