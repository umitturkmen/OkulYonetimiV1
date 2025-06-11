package com.okulyonetimi.okulyonetimi.model;
import jakarta.persistence.Entity;

@Entity
public class Mudur extends Kullanici {
    public Mudur() {}
    public Mudur(String isim, String kullaniciAdi, String sifre) {
        super(isim, kullaniciAdi, sifre);
    }

    @Override
    public String getRol() {
        return "Mudur";
    }
}
