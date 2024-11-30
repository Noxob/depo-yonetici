package com.samirusta.depoyonetici.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "TALEP")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Talep {
    @Id
    @GeneratedValue(generator="talep_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="talep_seq",sequenceName="talep_seq", allocationSize=1)
    private Long id;
    @ManyToOne
    @JoinColumn(name="kullanici_id", nullable=false)
    private Kullanici kullanici;
    @ManyToOne
    @JoinColumn(name="urun_id", nullable=false)
    private Urun urun;
    private String talepNotu;
    private String durum;
    private Date olusturmaZamani;
    private Date sonuclanmaZamani;
}
