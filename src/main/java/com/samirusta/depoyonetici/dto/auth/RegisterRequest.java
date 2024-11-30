package com.samirusta.depoyonetici.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String ad;
    private String soyad;
    private String sicil;
    private String tckn;
    private String eposta;
    private String sifre;
}