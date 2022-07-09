package apap.ta.sifactory.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import apap.ta.sifactory.model.*;
import apap.ta.sifactory.repository.DeliveryDb;
import apap.ta.sifactory.rest.ItemDetail;
import apap.ta.sifactory.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class RequestUpdateItemController {

    @Autowired
    private RequestUpdateItemService requestUpdateItemService;

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

    @Autowired
    private DeliveryDb deliveryDb;

    @Autowired
    private RoleService roleService;

    @GetMapping("/request/viewall")
    public String listRequest(Model model) {
        List<RequestUpdateItemModel> listRequest = requestUpdateItemService.getRequestList();
        model.addAttribute("listRequest", listRequest);
        return "viewall-request";
    }

    @GetMapping("/request/update/{id_request}")
    public String updateRequestItemForm(@PathVariable Integer id_request, Principal user, Model model){
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestByIdRequest(id_request);
        ItemDetail item = itemRestService.getItem(requestUpdateItem.getId_item());
        ProduksiModel produksi = new ProduksiModel();
        KategoriModel kategori = kategoriService.findByNamaKategori(item.getKategori());
        produksi.setIdItem(requestUpdateItem.getId_item());
        produksi.setPegawai(pegawaiService.findByUsername(user.getName()));
        produksi.setId_kategori(kategori.getId_kategori());
        produksi.setTanggal_produksi(requestUpdateItem.getTanggal_request());
        produksi.setTambahan_stok(requestUpdateItem.getTambahan_stok());
        produksi.setRequestUpdateItem(requestUpdateItem);
        List<MesinModel> listMesin = mesinService.findAllByKategori(kategori);
        model.addAttribute("produksi", produksi);
        model.addAttribute("listMesin", listMesin);
        return "form-update-request";
    }

    @PostMapping("/request/update")
    public String updateRequestItemSubmit(
            @ModelAttribute ProduksiModel produksi) {
        ItemDetail item = new ItemDetail();
        produksi.getRequestUpdateItem().setExecuted(true);
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
                return "redirect:/request/viewall";
            }
        }
        return "error-update-item";
    }

    @GetMapping("request/assign-delivery/{id_request}")
    public String assignDeliveryForm(@PathVariable Integer id_request, Model model) {
        RequestUpdateItemModel requestUpdateItem = requestUpdateItemService.getRequestByIdRequest(id_request);
        DeliveryModel delivery = new DeliveryModel();
        RoleModel role = roleService.findByNamaRole("STAFF_KURIR");
        delivery.setRequestUpdateItem(requestUpdateItem);
        delivery.setTanggal_dibuat(LocalDate.now());
        delivery.setTanggal_dikirim(LocalDate.now());
        delivery.setSent(false);
        List<PegawaiModel> listKurir = pegawaiService.getPegawaiListByRole(role);
        model.addAttribute("delivery", delivery);
        model.addAttribute("listKurir", listKurir);
        return "form-assign-delivery";
    }

    @PostMapping("request/assign-delivery")
    public String assignDeliverySubmit( @ModelAttribute DeliveryModel delivery, Principal user ){
        if (delivery != null) {
            PegawaiModel staffOperasional = pegawaiService.findByUsername(user.getName());
            Integer c = staffOperasional.getCounter();
            staffOperasional.setCounter(c + 1);
            pegawaiService.updatePegawai(staffOperasional);
            deliveryDb.save(delivery);
        }
        return "redirect:/request/viewall";
    }
}
