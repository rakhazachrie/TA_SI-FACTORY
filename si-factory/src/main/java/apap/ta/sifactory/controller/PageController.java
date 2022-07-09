package apap.ta.sifactory.controller;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.service.PegawaiService;

import org.springframework.ui.Model;

@Controller
public class PageController {
    @Autowired
    private PegawaiService pegawaiService;

    @RequestMapping("/")
    public String home(Model model, Authentication authentication){
        PegawaiModel user = pegawaiService.findByUsername(authentication.getName());
        model.addAttribute("pegawaiNama", user.getNama());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
