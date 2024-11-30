package com.samirusta.depoyonetici.service;

import com.samirusta.depoyonetici.model.Kullanici;
import com.samirusta.depoyonetici.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KullaniciService {
    private final KullaniciRepository kullaniciRepository;

    public void save(Kullanici kullanici) {
        kullaniciRepository.save(kullanici);
    }

    public Kullanici findBySicil(String sicil) {
        return kullaniciRepository.findBySicil(sicil)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı."));
    }
}
