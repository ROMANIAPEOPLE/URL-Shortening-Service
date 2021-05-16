package com.mss.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Url {

    private static final String URL_PREFIX = "http://localhost:8080/";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL(message = "http 또는 https를 포함한 올바른 형태의 URL을 입력해주세요.")
    private String originUrl;

    @Column(length = 8)
    private String shortUrl;

    private Long count;

    public void increaseCount() {
        count++;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public static Url from(String originUrl) {
        return Url.builder()
            .originUrl(originUrl)
            .count(1L)
            .build();
    }

    public String createShortUrl() {
        return URL_PREFIX + this.shortUrl;
    }

    @Builder
    public Url(Long id, String originUrl, String shortUrl, Long count) {
        this.id = id;
        this.originUrl = originUrl;
        this.shortUrl = shortUrl;
        this.count = count;
    }
}
