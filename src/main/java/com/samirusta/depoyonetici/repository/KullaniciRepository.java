package com.samirusta.depoyonetici.repository;

import com.samirusta.depoyonetici.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findBySicil(String sicil);
}
