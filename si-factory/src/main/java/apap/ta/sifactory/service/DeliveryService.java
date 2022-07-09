package apap.ta.sifactory.service;

import java.util.List;

import apap.ta.sifactory.model.DeliveryModel;
import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.rest.APIResponseAgung;
import apap.ta.sifactory.rest.Cabang;

public interface DeliveryService {
    DeliveryModel findDeliverybyIdDelivery(Integer idDelivery);

    List<DeliveryModel> getDeliveryList();

    List<DeliveryModel> getDeliveryListbyKurir(PegawaiModel kurir);

    DeliveryModel saveDelivery(DeliveryModel delivery);

    List<Cabang> getCabangList();

    Cabang getDeliveryCabang(DeliveryModel delivery);
}