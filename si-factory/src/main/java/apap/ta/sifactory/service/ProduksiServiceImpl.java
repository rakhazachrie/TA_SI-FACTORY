package apap.ta.sifactory.service;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.ProduksiModel;
import apap.ta.sifactory.repository.MesinDb;
import apap.ta.sifactory.repository.PegawaiDb;
import apap.ta.sifactory.repository.ProduksiDb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProduksiServiceImpl implements ProduksiService {
    @Autowired
    private ProduksiDb produksiDb;

    @Override
    public ProduksiModel addProduksi(ProduksiModel produksi) {
        return produksiDb.save(produksi);
    }

    @Override
    public List<ProduksiModel> findAllByItem(String id_item) {
        return produksiDb.findAllByIdItem(id_item);
    }

    @Override
    public List<ProduksiModel> getProduksiList() {return produksiDb.findAll(); }
}
