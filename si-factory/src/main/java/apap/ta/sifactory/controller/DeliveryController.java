package apap.ta.sifactory.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import apap.ta.sifactory.model.DeliveryModel;
import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.RoleModel;

import apap.ta.sifactory.rest.Cabang;

import apap.ta.sifactory.service.DeliveryService;
import apap.ta.sifactory.service.PegawaiService;
import apap.ta.sifactory.service.RoleService;

import org.springframework.ui.Model;

@Controller
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private PegawaiService pegawaiService;

    @GetMapping(value = "/delivery/viewall")
    private String viewAllDelivery(Model model, Authentication authentication) {
        PegawaiModel user = pegawaiService.findByUsername(authentication.getName());
        List<DeliveryModel> listDelivery = null;
        if(user.getRole().getNamaRole().equals("STAFF_KURIR")){
            listDelivery = deliveryService.getDeliveryListbyKurir(user);
        }else if(user.getRole().getNamaRole().equals("STAFF_OPERASIONAL")
                || user.getRole().getNamaRole().equals("ADMIN")){
            listDelivery = deliveryService.getDeliveryList();
        }
        model.addAttribute("listDelivery", listDelivery);
        model.addAttribute("deliveryUser", user);
        return "viewall-delivery";
    }

    @GetMapping("/delivery/{idDelivery}/send")
    public String sentDelivery(@PathVariable Integer idDelivery, Model model, Authentication authentication) {
        PegawaiModel user = pegawaiService.findByUsername(authentication.getName());
        DeliveryModel delivery = deliveryService.findDeliverybyIdDelivery(idDelivery);
        Cabang cabang = deliveryService.getDeliveryCabang(delivery);
        if(cabang != null){
            delivery.setSent(true);
            deliveryService.saveDelivery(delivery);
            model.addAttribute("cabang", cabang);
            user.setCounter(user.getCounter() + 1);
            pegawaiService.updatePegawai(user);
            return "success-pengiriman-delivery";
        }

        return "error-pengiriman-delivery";
    }
}
