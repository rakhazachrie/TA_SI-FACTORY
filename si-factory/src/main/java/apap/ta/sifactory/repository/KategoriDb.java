package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.KategoriModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriDb extends JpaRepository<KategoriModel, Integer> {
    KategoriModel findByKategori(String kategori);

    Optional<KategoriModel> findById(Integer id);
}
