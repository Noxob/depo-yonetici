package com.samirusta.depoyonetici.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROL")
@Getter
@Setter
public class Rol implements GrantedAuthority {
    @Id
    private Long id;
    private String ad;

    @Override
    public String getAuthority() {
        return ad;
    }
}
