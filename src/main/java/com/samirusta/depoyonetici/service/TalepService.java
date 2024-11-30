package com.samirusta.depoyonetici.service;

import com.samirusta.depoyonetici.dto.TalepDTO;
import com.samirusta.depoyonetici.enumaration.TalepDurum;
import com.samirusta.depoyonetici.exception.ApiException;
import com.samirusta.depoyonetici.model.Kullanici;
import com.samirusta.depoyonetici.model.Talep;
import com.samirusta.depoyonetici.model.Urun;
import com.samirusta.depoyonetici.repository.TalepRepository;
import com.samirusta.depoyonetici.repository.UrunRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TalepService {

    private final TalepRepository talepRepository;
    private final KullaniciService kullaniciService;
    private final UrunRepository urunRepository;

    public void save(Talep talep) {
        talepRepository.save(talep);
    }

    public void saveWithSicilAndTalepDto(String sicil, TalepDTO talepDTO) {
        Kullanici kullanici = kullaniciService.findBySicil(sicil);
        talepRepository.save(Talep.builder()
                .urun(talepDTO.getUrun())
                .kullanici(kullanici)
                .talepNotu(talepDTO.getNot())
                .durum(TalepDurum.BEKLIYOR.name())
                .olusturmaZamani(new Date())
                .build());
    }

    public Page<List<Talep>> findByKullaniciPaged(String sicil, String durum, Pageable pageable) {
        return talepRepository.findByKullaniciSicilAndDurum(sicil, durum, pageable);
    }

    @Transactional
    public void durumGuncelle(Long id, String durum) throws Exception {
        if (TalepDurum.of(durum) == TalepDurum.TAMAMLANDI) {
            Talep talep = talepRepository.findById(id)
                    .orElseThrow(() -> new ApiException("Talep bulunamadı."));
            talep.setSonuclanmaZamani(new Date());
            Urun urun = talep.getUrun();
            if (urun.getMiktar().compareTo(0) <= 0) {
                throw new ApiException("Talep edilen ürün bulunmuyor. Eğer ürün yeni geldiyse lütfen ürün girişi yapın.");
            }
            urun.setMiktar(urun.getMiktar() - 1);
            urunRepository.save(urun);
            talepRepository.updateDurumById(id, durum);
        }

        talepRepository.updateDurumById(id, durum);
    }
}
