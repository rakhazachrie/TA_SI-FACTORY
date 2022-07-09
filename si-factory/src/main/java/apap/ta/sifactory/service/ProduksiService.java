package apap.ta.sifactory.service;

import java.util.*;
import apap.ta.sifactory.model.ProduksiModel;
import java.util.List;

public interface ProduksiService {
    ProduksiModel addProduksi(ProduksiModel produksi);
    List<ProduksiModel> findAllByItem(String id_item);
    List<ProduksiModel> getProduksiList();
}
