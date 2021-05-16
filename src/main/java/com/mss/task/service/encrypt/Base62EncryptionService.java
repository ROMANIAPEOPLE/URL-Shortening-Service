package com.mss.task.service.encrypt;

import static com.mss.task.common.constants.Constants.BASE62;
import static com.mss.task.common.constants.Constants.BASE62_CHAR;

import org.springframework.stereotype.Service;

@Service
public class Base62EncryptionService implements EncryptionService {

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
