package com.mss.task.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.mss.task.controller.dto.UrlDto.ShortUrlResponse;
import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import com.mss.task.service.url.UrlService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class UrlViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlService urlService;

    private UrlConvertingRequest createRequestDto() {
        return UrlConvertingRequest.builder()
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }

    private String getConvertingUrl(ShortUrlResponse convertingUrl) {
        return convertingUrl.getShortUrl()
            .substring(convertingUrl.getShortUrl().lastIndexOf("/") + 1);
    }

    @DisplayName(" get방식으로 '/' 요청시 url 변환 페이지로 이동한다.")
    @Test
    public void urlConvertingView() throws Exception {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }

    @DisplayName("Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 한다.")
    @Test
    void redirectOriginUrl() throws Exception {
        UrlConvertingRequest requestDto = createRequestDto();
        ShortUrlResponse convertingUrl = urlService.getConvertingUrl(requestDto);
        String shortUrl = getConvertingUrl(convertingUrl);

        ResultActions resultActions = mockMvc.perform(get("/{shortUrl}", shortUrl))
            .andDo(print());

        resultActions.andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl(requestDto.getOriginUrl()));
    }


}
