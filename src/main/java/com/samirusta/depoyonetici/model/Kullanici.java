package com.samirusta.depoyonetici.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "KULLANICI")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Kullanici implements UserDetails {
    @Id
    @GeneratedValue(generator="kullanici_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="kullanici_seq",sequenceName="kullanici_seq", allocationSize=1)
    private Long id;
    @Column(name = "SICIL", unique = true)
    private String sicil;
    @Column(name = "TCKN", unique = true)
    private String tckn;
    @Column(name = "EPOSTA", unique = true)
    private String eposta;
    private String ad;
    private String soyad;
    private String sifre;
    private Boolean aktif;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "KULLANICI_ROL",
            joinColumns = @JoinColumn(name = "KULLANICI_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID"))
    private List<Rol> roller;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sifre;
    }

    @Override
    public String getUsername() {
        return sicil;
    }

    @Override
    public boolean isAccountNonExpired() {
        return aktif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return aktif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return aktif;
    }

    @Override
    public boolean isEnabled() {
        return aktif;
    }
}
