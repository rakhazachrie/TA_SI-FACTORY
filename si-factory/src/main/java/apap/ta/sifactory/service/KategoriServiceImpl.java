package apap.ta.sifactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import apap.ta.sifactory.repository.KategoriDb;
import apap.ta.sifactory.model.KategoriModel;

@Service
public class KategoriServiceImpl implements KategoriService {
    @Autowired
    private KategoriDb kategoriDb;

    @Override
    public KategoriModel findByNamaKategori(String kategori) {
        return kategoriDb.findByKategori(kategori);
    }

    @Override
    public Optional<KategoriModel> findById_kategori(Integer id_kategori) {
        Optional<KategoriModel> kategori = kategoriDb.findById(id_kategori);

        if (kategori.isPresent()) {
            return kategori;
        }
        return null;
    }

    @Override
    public List<KategoriModel> findAll() {
        return kategoriDb.findAll();
    }
}
