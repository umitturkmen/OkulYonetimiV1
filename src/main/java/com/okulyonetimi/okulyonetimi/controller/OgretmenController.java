package com.okulyonetimi.okulyonetimi.controller;

import com.okulyonetimi.okulyonetimi.model.Ogretmen;
import com.okulyonetimi.okulyonetimi.service.OgretmenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OgretmenController {

    @Autowired
    private OgretmenService ogretmenService;

    @GetMapping("/ogretmen/menu")
    public String ogretmenMenu(HttpSession session) {
        if (!"ogretmen".equals(session.getAttribute("rol"))) {
            return "redirect:/login";
        }
        return "ogretmen_menu";
    }

    @GetMapping("/ogretmenler")
    public String ogretmenListesi(HttpSession session, Model model) {
        if (session.getAttribute("rol") == null) {
            return "redirect:/login";
        }
        model.addAttribute("ogretmenler", ogretmenService.findAll());
        return "ogretmenler";
    }

    @GetMapping("/ogretmen/ekle")
    public String ogretmenEkleForm(HttpSession session, Model model) {
        if (!"mudur".equals(session.getAttribute("rol"))) {
            return "redirect:/login";
        }
        model.addAttribute("ogretmen", new Ogretmen());
        return "ogretmen_ekle";
    }

    @PostMapping("/ogretmen/ekle")
    public String ogretmenEkle(HttpSession session, @ModelAttribute Ogretmen ogretmen) {
        if (!"mudur".equals(session.getAttribute("rol"))) {
            return "redirect:/login";
        }
        ogretmenService.save(ogretmen);
        return "redirect:/ogretmenler";
    }

    @GetMapping("/ogretmen/sil-sec")
    public String ogretmenSilSec(HttpSession session, Model model) {
        if (!"mudur".equals(session.getAttribute("rol"))) {
            return "redirect:/login";
        }
        model.addAttribute("ogretmenler", ogretmenService.findAll());
        return "ogretmen_sil";
    }

    @PostMapping("/ogretmen/sil")
    public String ogretmenSilSubmit(HttpSession session, @RequestParam Long id) {
        if (!"mudur".equals(session.getAttribute("rol"))) {
            return "redirect:/login";
        }
        ogretmenService.deleteById(id);
        return "redirect:/ogretmenler";
    }
}
