package com.mss.task.service.encrypt;

import org.springframework.stereotype.Service;

@Service
public class Base62EncryptionService implements EncryptionService {

    private static final String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int BASE62 = 62;

    @Override
    public String encoding(Long id) {
        StringBuilder builder = new StringBuilder();
        do {
            int idx = (int) (id % BASE62);
            builder.append(BASE62_CHAR.charAt(idx));
            id /= BASE62;
        } while (id > 0);
        return builder.toString();
    }

    @Override
    public Long decoding(String url) {
        long sum = 0;
        long power = 1;
        int length = url.length();
        for (int idx = 0; idx < length; idx++) {
            sum += BASE62_CHAR.indexOf(url.charAt(idx)) * power;
            power *= BASE62;
        }
        return sum;
    }
}
