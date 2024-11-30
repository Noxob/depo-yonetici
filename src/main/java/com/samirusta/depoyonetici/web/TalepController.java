package com.samirusta.depoyonetici.web;

import com.samirusta.depoyonetici.dto.TalepDTO;
import com.samirusta.depoyonetici.model.Talep;
import com.samirusta.depoyonetici.service.TalepService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/talep")
@RequiredArgsConstructor
public class TalepController {
    private final TalepService talepService;


//    @PreAuthorize("hasAuthority('DEPOCU')")
    @GetMapping
    public ResponseEntity<Page<List<Talep>>> getAll(@RequestParam(required = false) String sicil,
                                                    @RequestParam(required = false) String durum,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(talepService.findByKullaniciPaged(sicil, durum, PageRequest.of(page, size)));
    }

    @GetMapping("/owned")
    public ResponseEntity<Page<List<Talep>>> getUserTalepleri(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size){
        String sicil = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(talepService.findByKullaniciPaged(sicil, null, PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TalepDTO talepDTO){
        String sicil = SecurityContextHolder.getContext().getAuthentication().getName();
        talepService.saveWithSicilAndTalepDto(sicil, talepDTO);
        return ResponseEntity.ok().build();
    }


//    @PreAuthorize("hasAuthority('DEPOCU')")
    @PatchMapping("/id/{id}/durum/{durum}")
    public  ResponseEntity<Void> updateDurum(@PathVariable Long id, @PathVariable String durum) throws Exception {
        talepService.durumGuncelle(id, durum);
        return ResponseEntity.ok().build();
    }


}
