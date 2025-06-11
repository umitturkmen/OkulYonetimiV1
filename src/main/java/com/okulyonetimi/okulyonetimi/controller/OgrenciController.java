package com.okulyonetimi.okulyonetimi.controller;

import com.okulyonetimi.okulyonetimi.model.Ogrenci;
import com.okulyonetimi.okulyonetimi.service.OgrenciService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OgrenciController {

    @Autowired
    private OgrenciService ogrenciService;

    @GetMapping("/ogrenciler")
    public String ogrenciListesi(HttpSession session, Model model) {
        if (session.getAttribute("rol") == null) return "redirect:/login";
        model.addAttribute("ogrenciler", ogrenciService.findAll());
        return "ogrenciler";
    }

    @GetMapping("/ogrenci/ekle")
    public String ogrenciEkleForm(HttpSession session, Model model) {
        if (!"mudur".equals(session.getAttribute("rol"))) return "redirect:/login";
        model.addAttribute("ogrenci", new Ogrenci());
        return "ogrenci_ekle";
    }

    @PostMapping("/ogrenci/ekle")
    public String ogrenciEkle(HttpSession session, @ModelAttribute Ogrenci ogrenci) {
        if (!"mudur".equals(session.getAttribute("rol"))) return "redirect:/login";
        ogrenciService.save(ogrenci);
        return "redirect:/ogrenciler";
    }

    @GetMapping("/ogrenci/sil/{id}")
    public String ogrenciSil(HttpSession session, @PathVariable Long id) {
        if (!"mudur".equals(session.getAttribute("rol"))) return "redirect:/login";
        ogrenciService.deleteById(id);
        return "redirect:/ogrenciler";
    }

    @GetMapping("/ogrenci/sil-sec")
    public String ogrenciSilSec(HttpSession session, Model model) {
        if (!"mudur".equals(session.getAttribute("rol"))) return "redirect:/login";
        model.addAttribute("ogrenciler", ogrenciService.findAll());
        return "ogrenci_sil";
    }

    @PostMapping("/ogrenci/sil")
    public String ogrenciSilSubmit(HttpSession session, @RequestParam Long id) {
        if (!"mudur".equals(session.getAttribute("rol"))) return "redirect:/login";
        ogrenciService.deleteById(id);
        return "redirect:/ogrenciler";
    }

    @GetMapping("/ogrenciye-not-ekle")
    public String notEkleForm(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        if (!( "mudur".equals(rol) || "ogretmen".equals(rol) )) return "redirect:/login";
        model.addAttribute("ogrenciler", ogrenciService.findAll());
        return "not_ekle";
    }

    @PostMapping("/ogrenciye-not-ekle")
    public String notEkleSubmit(HttpSession session, @RequestParam Long ogrenciId, @RequestParam Double not) {
        String rol = (String) session.getAttribute("rol");
        if (!( "mudur".equals(rol) || "ogretmen".equals(rol) )) return "redirect:/login";
        Ogrenci ogr = ogrenciService.findById(ogrenciId);
        if (ogr != null) {
            ogr.getNotlar().add(not);
            ogrenciService.save(ogr);
        }
        return "redirect:/ogrenciler";
    }


    @GetMapping("/ogrenci-not-guncelle")
    public String notGuncelleForm(@RequestParam(value = "ogrenciId", required = false) Long ogrenciId, Model model) {
        model.addAttribute("ogrenciler", ogrenciService.findAll());
        if (ogrenciId != null) {
            Ogrenci ogrenci = ogrenciService.findById(ogrenciId);
            model.addAttribute("seciliOgrenci", ogrenci);
            model.addAttribute("notlar", ogrenci.getNotlar());
        }
        return "not_guncelle";
    }

    @PostMapping("/ogrenci-not-guncelle")
    public String notGuncelleSubmit(@RequestParam Long ogrenciId, @RequestParam Integer notIndex, @RequestParam Double yeniNot) {
        Ogrenci ogr = ogrenciService.findById(ogrenciId);
        if (ogr != null && ogr.getNotlar().size() > notIndex) {
            ogr.getNotlar().set(notIndex, yeniNot);
            ogrenciService.save(ogr);
        }
        return "redirect:/ogrenciler";
    }

}
