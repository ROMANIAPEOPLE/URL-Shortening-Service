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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originUrl;

    @Column(length = 8)
    private String shortUrl;

    private Long count;

    public void countIncrease() {
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

    @Builder
    public Url(String originUrl, String shortUrl, Long count) {
        this.originUrl = originUrl;
        this.count = count;
    }
}
