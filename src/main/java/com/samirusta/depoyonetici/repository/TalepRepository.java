package com.samirusta.depoyonetici.repository;

import com.samirusta.depoyonetici.model.Talep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TalepRepository extends JpaRepository<Talep, Long>{
    @Query("SELECT t FROM Talep t JOIN t.kullanici k WHERE (:sicil IS NULL OR k.sicil = :sicil) " +
            "AND (:durum IS NULL or t.durum = :durum) ORDER BY t.olusturmaZamani DESC")
    Page<List<Talep>> findByKullaniciSicilAndDurum(@Param("sicil") String sicil, @Param("durum") String durum, Pageable pageable);

    @Modifying
    @Query("UPDATE Talep t SET t.durum = :durum WHERE t.id = :id")
    int updateDurumById(@Param("id") Long id, @Param("durum") String durum);
}
