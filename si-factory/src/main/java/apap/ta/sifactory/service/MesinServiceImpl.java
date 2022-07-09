package apap.ta.sifactory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import apap.ta.sifactory.repository.KategoriDb;
import apap.ta.sifactory.repository.MesinDb;
import apap.ta.sifactory.model.KategoriModel;
import apap.ta.sifactory.model.MesinModel;

@Service
public class MesinServiceImpl implements MesinService {
    @Autowired
    private MesinDb mesinDb;

    @Autowired
    private KategoriDb kategoriDb;

    @Override
    public List<MesinModel> findAllByKategori(KategoriModel id_kategori) {
        return mesinDb.findAllByKategori(id_kategori);
    }

    @Override
    public MesinModel updateMesin(MesinModel mesin) {
        // TODO Auto-generated method stub
        return mesinDb.save(mesin);
    }
}
