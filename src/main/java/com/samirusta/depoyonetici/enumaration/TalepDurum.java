package com.samirusta.depoyonetici.enumaration;

import com.samirusta.depoyonetici.exception.ApiException;

import java.util.Arrays;

public enum TalepDurum {
    BEKLIYOR,
    ONAYLANDI,
    TAMAMLANDI;

    public static TalepDurum of(String durum) throws Exception {
        return Arrays.stream(TalepDurum.values())
                .filter(talepDurum -> durum.equals(talepDurum.name()))
                .findFirst()
                .orElseThrow(() -> new ApiException("Uygunsuz durum."));
    }
}
