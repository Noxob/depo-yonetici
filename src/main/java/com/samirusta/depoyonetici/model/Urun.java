package com.samirusta.depoyonetici.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "URUN")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Urun {
    @Id
    @GeneratedValue(generator="urun_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="urun_seq",sequenceName="urun_seq", allocationSize=1)
    private Long id;
    private String ad;
    private String barkod;
    private Integer miktar;
}
