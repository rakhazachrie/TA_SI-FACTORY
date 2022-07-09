package apap.ta.sifactory.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import apap.ta.sifactory.model.KategoriModel;
import apap.ta.sifactory.model.MesinModel;
import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.ProduksiModel;
import apap.ta.sifactory.rest.APIResponseAgung;
import apap.ta.sifactory.rest.ItemDetail;
import apap.ta.sifactory.rest.ItemResponse;
import apap.ta.sifactory.rest.ProposeItem;
import apap.ta.sifactory.security.UserDetailsServiceImpl;
import apap.ta.sifactory.service.ItemRestService;
import apap.ta.sifactory.service.KategoriService;
import apap.ta.sifactory.service.MesinService;
import apap.ta.sifactory.service.PegawaiService;
import apap.ta.sifactory.service.ProduksiService;

@Controller
public class ItemController {
    @Autowired
    private ItemRestService itemRestService;

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private KategoriService kategoriService;

    @Autowired
    private MesinService mesinService;

    @Autowired
    private ProduksiService produksiService;

    @GetMapping("/item/add")
    public String addItem(Model model) {
        ProposeItem item = new ProposeItem();
        item.setCluster("A05");
        model.addAttribute("item",item);
        model.addAttribute("categories", kategoriService.findAll());
        return "form-propose-item";
    }

    @PostMapping("/item/add")
    public String addItemSubmit(
            @ModelAttribute ProposeItem item,
            Model model,
            Authentication authentication) {
        APIResponseAgung<ProposeItem> response = itemRestService.addItem(item);
        if(response.getStatus() == 200){
            PegawaiModel pegawai = pegawaiService.findByUsername(authentication.getName());
            pegawai.setCounter(pegawai.getCounter() + 1);
            return "success-propose-item";
        }
        return "error-propose-item";
    }

    @GetMapping("/item/viewall")
    public String listItem(Model model) {
        List<ItemDetail> listItem = itemRestService.getItemList();
        model.addAttribute("listItem", listItem);
        return "viewall-item";
    }

    @GetMapping("/item/update/{uuid}")
    public String updateItemForm(@PathVariable String uuid, Principal user, Model model) {
        ItemDetail item = itemRestService.getItem(uuid);
        ProduksiModel produksi = new ProduksiModel();
        KategoriModel kategori = kategoriService.findByNamaKategori(item.getKategori());
        produksi.setIdItem(uuid);
        produksi.setPegawai(pegawaiService.findByUsername(user.getName()));
        produksi.setId_kategori(kategori.getId_kategori());
        produksi.setTanggal_produksi(LocalDate.now());
        produksi.setRequestUpdateItem(null);
        List<MesinModel> listMesin = mesinService.findAllByKategori(kategori);
        model.addAttribute("produksi", produksi);
        model.addAttribute("listMesin", listMesin);
        return "form-update-item";
    }

    @GetMapping("/item/detail/{uuid}")
    public String viewItemDetail(@PathVariable String uuid, Principal user, Model model) {
        ItemDetail item = itemRestService.getItem(uuid);
        List<ProduksiModel> ListProduksi = produksiService.findAllByItem(item.getUuid());
        model.addAttribute("item", item);
        model.addAttribute("listProduksi", ListProduksi);
        return "view-detail-item";
    }

    @PostMapping("/item/update")
    public String updateItemSubmit(
            @ModelAttribute ProduksiModel produksi,
            Model model) {
        ItemDetail item = new ItemDetail();
        item.setStok(produksi.getTambahan_stok());
        item.setUuid(produksi.getIdItem());
        System.out.println(produksi.getIdItem());
        item = itemRestService.updateItem(item);
        if (item != null) {
            produksi = produksiService.addProduksi(produksi);
            if (produksi != null) {
                Integer c = produksi.getPegawai().getCounter();
                Integer k = produksi.getMesin().getKapasitas();

                produksi.getMesin().setKapasitas(k - 1);
                produksi.getPegawai().setCounter(c + 1);

                mesinService.updateMesin(produksi.getMesin());
                pegawaiService.updatePegawai(produksi.getPegawai());
                return "redirect:/produksi/viewall";
            }
        }
        return "error-update-item";
    }
}
