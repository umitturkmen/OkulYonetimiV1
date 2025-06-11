package com.okulyonetimi.okulyonetimi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MudurController {
    @GetMapping("/mudur/menu")
    public String mudurMenu(HttpSession session) {
        if (!"mudur".equals(session.getAttribute("rol"))) {
            return "redirect:/login";
        }
        return "mudur_menu";
    }
}
