package com.samirusta.depoyonetici.web;

import com.samirusta.depoyonetici.dto.UrunDTO;
import com.samirusta.depoyonetici.model.Urun;
import com.samirusta.depoyonetici.service.UrunService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/urun")
@RequiredArgsConstructor
@CrossOrigin
public class UrunController {

    private final UrunService urunService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<List<Urun>>> getAll(@RequestParam(required = false) String ad,
                                                   @RequestParam(required = false) String barkod,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(urunService.findAllPaged(ad, barkod, PageRequest.of(page, size)));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody UrunDTO urunDTO){
        Urun urun = modelMapper.map(urunDTO, Urun.class);
        urunService.save(urun);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Urun> findByAd(@RequestParam String ad){
        return urunService.searchByAd(ad);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UrunDTO urunDTO){
        Urun urun = modelMapper.map(urunDTO, Urun.class);
        urun.setId(id);
        urunService.save(urun);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        urunService.delete(id);
        return ResponseEntity.ok().build();
    }
}
