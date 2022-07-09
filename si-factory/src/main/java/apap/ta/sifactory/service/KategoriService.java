package apap.ta.sifactory.service;

import java.util.Optional;
import java.util.List;
import apap.ta.sifactory.model.KategoriModel;

public interface KategoriService {
    KategoriModel findByNamaKategori(String kategori);

    Optional<KategoriModel> findById_kategori(Integer id_kategori);
    List<KategoriModel> findAll();
}
