package com.okulyonetimi.okulyonetimi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Kullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isim;
    private String kullaniciAdi;
    private String sifre;

    public Kullanici() {}

    public Kullanici(String isim, String kullaniciAdi, String sifre) {
        this.isim = isim;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
    }

    public abstract String getRol();

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public String getIsim() { return isim; }
    public void setIsim(String isim) { this.isim = isim; }
    public String getKullaniciAdi() { return kullaniciAdi; }
    public void setKullaniciAdi(String kullaniciAdi) { this.kullaniciAdi = kullaniciAdi; }
    public String getSifre() { return sifre; }
    public void setSifre(String sifre) { this.sifre = sifre; }
}
