package apap.ta.sifactory.service;

import java.util.List;

import apap.ta.sifactory.model.KategoriModel;
import apap.ta.sifactory.model.MesinModel;

public interface MesinService {
    List<MesinModel> findAllByKategori(KategoriModel id_kategori);

    MesinModel updateMesin(MesinModel mesin);
}
