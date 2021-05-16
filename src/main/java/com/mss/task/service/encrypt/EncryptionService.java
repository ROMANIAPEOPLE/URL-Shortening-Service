package com.mss.task.service.encrypt;

public interface EncryptionService {

    String encoding(Long id);

    Long decoding(String url);

}
