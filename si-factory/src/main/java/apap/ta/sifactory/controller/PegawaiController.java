package apap.ta.sifactory.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.RoleModel;
import apap.ta.sifactory.service.PegawaiService;
import apap.ta.sifactory.service.RoleService;

import org.springframework.ui.Model;

@Controller
public class PegawaiController {
    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/pegawai/add")
    private String addPegawaiFormPage(Model model) {
        PegawaiModel pegawai = new PegawaiModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("pegawai", pegawai);
        model.addAttribute("listRole", listRole);
        return "form-add-pegawai";
    }

    @PostMapping(value = "/pegawai/add")
    private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Principal user, Model model) {
        model.addAttribute("pegawai", pegawai);
        pegawai = pegawaiService.addPegawai(pegawai);
        if (pegawai != null) {
            PegawaiModel admin = pegawaiService.findByUsername(user.getName());
            Integer c = admin.getCounter();
            admin.setCounter(c + 1);
            pegawaiService.updatePegawai(admin);
            model.addAttribute("pegawai", pegawai);
            return "redirect:/pegawai/viewall";
        }
        return "error-username";
    }

    @GetMapping(value = "/pegawai/viewall")
    private String listPegawai(Model model) {
        List<PegawaiModel> listPegawai = pegawaiService.getPegawaiList();
        model.addAttribute("listPegawai", listPegawai);
        return "viewall-pegawai";
    }
}
