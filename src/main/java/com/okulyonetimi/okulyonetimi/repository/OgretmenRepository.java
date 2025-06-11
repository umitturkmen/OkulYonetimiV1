package com.okulyonetimi.okulyonetimi.repository;

import com.okulyonetimi.okulyonetimi.model.Ogretmen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OgretmenRepository extends JpaRepository<Ogretmen, Long> {
    Ogretmen findByKullaniciAdiAndSifre(String kullaniciAdi, String sifre);
}
