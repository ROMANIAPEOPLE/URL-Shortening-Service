package com.mss.task.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mss.task.controller.dto.UrlDto.UrlConvertingRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UrlApiControllerIntegrateTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private UrlConvertingRequest createRequestDto() {
        return UrlConvertingRequest.builder()
            .originUrl("http://store.musinsa.com/app/goods/1842348")
            .build();
    }

    private UrlConvertingRequest createRequestDtoInvalidUrl() {
        return UrlConvertingRequest.builder()
            .originUrl("www.musinsa.com")
            .build();
    }

    @DisplayName("올바른 형태의 URL 요청시 정상적으로 Short URL을 반환한다.")
    @Test
    void successConversionUrl() throws Exception {
        UrlConvertingRequest requestDto = createRequestDto();

        ResultActions resultActions = mockMvc.perform(post("/url-convert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
            .andDo(print());

        resultActions
            .andExpect(jsonPath("$.shortUrl").value("http://localhost:8080/B"))
            .andExpect(status().isOk());
    }

    @DisplayName("잘못된 형태의 URL 요청시 400코드와 에러 메시지를 반환한다.")
    @Test
    void failureConversionUrl() throws Exception {
        UrlConvertingRequest requestDto = createRequestDtoInvalidUrl();

        ResultActions resultActions = mockMvc.perform(post("/url-convert")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
            .andDo(print());

        resultActions
            .andExpect(status().isBadRequest())
            .andExpect(content().string("http 또는 https를 포함한 올바른 형태의 URL을 입력해주세요."));
    }
}