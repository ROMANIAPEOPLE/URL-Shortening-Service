package com.mss.task.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    boolean existsByOriginUrl(String originUrl);

    Optional<Url> findByOriginUrl(String originUrl);
}
