package com.samirusta.depoyonetici.repository;

import com.samirusta.depoyonetici.model.Urun;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UrunRepository extends JpaRepository<Urun, Long>{

    @Query("SELECT u FROM Urun u WHERE " +
            "(:ad IS NULL OR u.ad LIKE %:ad%) AND " +
            "(:barkod IS NULL OR u.barkod = :barkod)")
    Page<List<Urun>> findByAdAndBarkodPaged(
            @Param("ad") String ad,
            @Param("barkod") String barkod,
            Pageable pageable
    );
    @Query("SELECT u FROM Urun u WHERE LOWER(u.ad) LIKE LOWER(CONCAT('%', :ad, '%'))")
    List<Urun> findByAdLike(@Param("ad") String ad, Pageable pageable);

}
