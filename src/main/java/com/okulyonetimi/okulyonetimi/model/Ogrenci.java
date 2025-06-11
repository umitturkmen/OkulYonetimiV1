package com.okulyonetimi.okulyonetimi.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isim;
    private String soyisim;

    @ElementCollection
    private List<Double> notlar = new ArrayList<>();

    public Ogrenci() {}

    public Ogrenci(String isim, String soyisim) {
        this.isim = isim;
        this.soyisim = soyisim;
    }
    public double getOrtalama() {
        if (notlar == null || notlar.isEmpty()) return 0.0;
        return notlar.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }


    public void notEkle(double not) { notlar.add(not); }
    public void notGuncelle(int index, double yeniNot) { notlar.set(index, yeniNot); }
    public double ortalamaHesapla() {
        return notlar.stream().mapToDouble(n -> n).average().orElse(0.0);
    }

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public String getIsim() { return isim; }
    public void setIsim(String isim) { this.isim = isim; }
    public String getSoyisim() { return soyisim; }
    public void setSoyisim(String soyisim) { this.soyisim = soyisim; }
    public List<Double> getNotlar() { return notlar; }
    public void setNotlar(List<Double> notlar) { this.notlar = notlar; }
}
