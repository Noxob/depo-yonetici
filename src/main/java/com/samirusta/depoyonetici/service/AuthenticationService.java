package com.samirusta.depoyonetici.service;

import com.samirusta.depoyonetici.config.JwtService;
import com.samirusta.depoyonetici.dto.auth.AuthenticationResponse;
import com.samirusta.depoyonetici.dto.auth.LoginRequest;
import com.samirusta.depoyonetici.dto.auth.RegisterRequest;
import com.samirusta.depoyonetici.model.Kullanici;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final KullaniciService kullaniciService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        Kullanici kullanici = Kullanici.builder()
                .ad(registerRequest.getAd())
                .soyad(registerRequest.getSoyad())
                .sicil(registerRequest.getSicil())
                .eposta(registerRequest.getEposta())
                .sifre(passwordEncoder.encode(registerRequest.getSifre()))
                .tckn(registerRequest.getTckn())
                .aktif(Boolean.TRUE)
                .build();
        kullaniciService.save(kullanici);

        String token = jwtService.generatetoken(kullanici);

        return AuthenticationResponse.builder()
                .token(token)
                .successful(true)
                .build();
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getSicil(), loginRequest.getSifre()));
        Kullanici kullanici = kullaniciService.findBySicil(loginRequest.getSicil());
        String token = jwtService.generatetoken(kullanici);
        return AuthenticationResponse.builder()
                .token(token)
                .successful(true)
                .build();
    }

    public Kullanici getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return kullaniciService.findBySicil(currentUserName);
        }
        throw new UsernameNotFoundException("Kullanıcı bulunamadı.");
    }

}
