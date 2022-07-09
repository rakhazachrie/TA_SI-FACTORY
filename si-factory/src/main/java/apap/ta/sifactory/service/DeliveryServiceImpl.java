package apap.ta.sifactory.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.ta.sifactory.model.DeliveryModel;
import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.repository.DeliveryDb;
import apap.ta.sifactory.rest.APIResponseAgung;
import apap.ta.sifactory.rest.Cabang;

@Service
public class DeliveryServiceImpl implements DeliveryService{
    private final WebClient webClientRetail;

    @Autowired
    private DeliveryDb deliveryDb;

    public DeliveryServiceImpl(WebClient.Builder webClientBuilder) {
        webClientRetail = webClientBuilder.baseUrl("https://si-retail-a05-15.herokuapp.com").build();
    }

    @Override
    public List<DeliveryModel> getDeliveryList() {
        return deliveryDb.findAll();
    }

    @Override
    public List<DeliveryModel> getDeliveryListbyKurir(PegawaiModel kurir) {
        return deliveryDb.findAllByKurir(kurir);
    }

    @Override
    public Cabang getDeliveryCabang(DeliveryModel delivery) {
        List<Cabang> cabangList = getCabangList();
        System.out.println("List Cabang");
        for (Cabang cabang : cabangList) {
            System.out.println("ID " + cabang.getId());
        }
        System.out.println("ID Cabang Delivery " + delivery.getRequestUpdateItem().getId_cabang());
        Cabang cabang = cabangList.stream()
            .filter(o -> o.getId() == delivery.getRequestUpdateItem().getId_cabang())
            .findFirst()
            .orElse(null);

        return cabang;
    }

    @Override
    public List<Cabang> getCabangList() {
        APIResponseAgung<List<Cabang>> api = webClientRetail.get().uri("/api/v1/cabang/list")
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<APIResponseAgung<List<Cabang>>>(){}).block();
        return api.getResult();
    }

    @Override
    public DeliveryModel findDeliverybyIdDelivery(Integer idDelivery) {
        return deliveryDb.findById(idDelivery).get();
    }

    @Override
    public DeliveryModel saveDelivery(DeliveryModel delivery) {
        return deliveryDb.save(delivery);
    }
}