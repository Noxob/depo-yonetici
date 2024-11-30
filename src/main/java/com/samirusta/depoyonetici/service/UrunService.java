package com.samirusta.depoyonetici.service;

import com.samirusta.depoyonetici.model.Urun;
import com.samirusta.depoyonetici.repository.UrunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrunService {
    private final UrunRepository urunRepository;

    public void save(Urun urun) {
        urunRepository.save(urun);
    }

    public void delete(Long id) {
        urunRepository.deleteById(id);
    }

    public Page<List<Urun>> findAllPaged(String ad, String barkod, Pageable pageable) {
        return urunRepository.findByAdAndBarkodPaged(ad, barkod, pageable);
    }

    public List<Urun> searchByAd(String ad) {
        return urunRepository.findByAdLike(ad, PageRequest.of(0, 10));
    }
}
