package com.okulyonetimi.okulyonetimi.controller;

import com.okulyonetimi.okulyonetimi.model.Ogretmen;
import com.okulyonetimi.okulyonetimi.service.OgretmenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private OgretmenService ogretmenService;

    @GetMapping({"/", "/login"})
    public String loginForm(HttpSession session) {

        String rol = (String) session.getAttribute("rol");
        if ("mudur".equals(rol)) {
            return "redirect:/mudur/menu";
        } else if ("ogretmen".equals(rol)) {
            return "redirect:/ogretmen/menu";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(
            @RequestParam String kullaniciAdi,
            @RequestParam String sifre,
            Model model,
            HttpSession session
    ) {

        if ("mudur".equals(kullaniciAdi) && "1234".equals(sifre)) {
            session.setAttribute("rol", "mudur");
            return "redirect:/mudur/menu";
        }

        Ogretmen ogretmen = ogretmenService.login(kullaniciAdi, sifre);
        if (ogretmen != null) {
            session.setAttribute("rol", "ogretmen");
            session.setAttribute("ogretmenId", ogretmen.getId());
            return "redirect:/ogretmen/menu";
        }
        model.addAttribute("hata", "Kullanıcı adı veya şifre hatalı!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
