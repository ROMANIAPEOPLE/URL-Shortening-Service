package com.mss.task.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.mss.task.service.encrypt.Base62EncryptionService;
import com.mss.task.service.encrypt.EncryptionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Base62EncryptionServiceTest {

    private EncryptionService base62EncryptionService = new Base62EncryptionService();

    @DisplayName("임의의 ID 값으로 인코딩이 정상적으로 동작하는지 확인한다.")
    @Test
    void urlEncoding() {
        Long id = 1234L;

        String encodingResult = base62EncryptionService.encoding(id);

        assertThat(id).isEqualTo(base62EncryptionService.decoding(encodingResult));
    }

    @DisplayName("임의의 String 값으로 디코딩이 정상적으로 동작하는지 확인한다.")
    @Test
    void urlDecoding() {
        String shortUrl = "ABCD";

        Long id = base62EncryptionService.decoding(shortUrl);

        assertThat(shortUrl).isEqualTo(base62EncryptionService.encoding(id));
    }
}