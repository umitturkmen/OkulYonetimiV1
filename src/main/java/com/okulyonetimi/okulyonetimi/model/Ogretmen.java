package com.okulyonetimi.okulyonetimi.model;

import jakarta.persistence.Entity;

@Entity
public class Ogretmen extends Kullanici {
    private String brans;

    public Ogretmen() {}
    public Ogretmen(String isim, String kullaniciAdi, String sifre, String brans) {
        super(isim, kullaniciAdi, sifre);
        this.brans = brans;
    }

    @Override
    public String getRol() {
        return "Ogretmen";
    }

    public String getBrans() { return brans; }
    public void setBrans(String brans) { this.brans = brans; }
}
