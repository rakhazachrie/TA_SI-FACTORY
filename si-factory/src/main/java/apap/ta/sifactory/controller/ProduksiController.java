package apap.ta.sifactory.controller;

import java.util.List;

import apap.ta.sifactory.model.ProduksiModel;
import apap.ta.sifactory.service.ProduksiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class ProduksiController {
    @Qualifier("produksiServiceImpl")
    @Autowired
    private ProduksiService produksiService;

    @GetMapping("produksi/viewall")
    public String listProduksi(Model model) {
        List<ProduksiModel> listProduksi = produksiService.getProduksiList();
        model.addAttribute("listProduksi", listProduksi);
        return "viewall-produksi";
    }
}