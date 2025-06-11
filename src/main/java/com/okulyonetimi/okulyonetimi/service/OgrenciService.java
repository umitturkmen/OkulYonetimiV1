package com.okulyonetimi.okulyonetimi.service;

import com.okulyonetimi.okulyonetimi.model.Ogrenci;
import com.okulyonetimi.okulyonetimi.repository.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OgrenciService {
    @Autowired
    private OgrenciRepository ogrenciRepository;

    public List<Ogrenci> findAll() {
        return ogrenciRepository.findAll();
    }

    public Ogrenci save(Ogrenci ogrenci) {
        return ogrenciRepository.save(ogrenci);
    }

    public void deleteById(Long id) {
        ogrenciRepository.deleteById(id);
    }

    public Ogrenci findById(Long id) {
        return ogrenciRepository.findById(id).orElse(null);
    }
}
