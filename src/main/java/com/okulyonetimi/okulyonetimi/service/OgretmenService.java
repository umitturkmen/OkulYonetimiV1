package com.okulyonetimi.okulyonetimi.service;

import com.okulyonetimi.okulyonetimi.model.Ogretmen;
import com.okulyonetimi.okulyonetimi.repository.OgretmenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OgretmenService {
    @Autowired
    private OgretmenRepository ogretmenRepository;

    public List<Ogretmen> findAll() {
        return ogretmenRepository.findAll();
    }

    public Ogretmen save(Ogretmen ogretmen) {
        return ogretmenRepository.save(ogretmen);
    }

    public void deleteById(Long id) {
        ogretmenRepository.deleteById(id);
    }

    public Ogretmen findById(Long id) {
        return ogretmenRepository.findById(id).orElse(null);
    }

    public Ogretmen login(String kullaniciAdi, String sifre) {
        return ogretmenRepository.findByKullaniciAdiAndSifre(kullaniciAdi, sifre);
    }
}
