package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.KategoriModel;
import apap.ta.sifactory.model.MesinModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesinDb extends JpaRepository<MesinModel, Integer> {
    List<MesinModel> findAllByKategori(KategoriModel id_kategori);
}
