package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.DeliveryModel;
import apap.ta.sifactory.model.PegawaiModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDb extends JpaRepository<DeliveryModel, Integer> {
    public List<DeliveryModel> findAllByKurir(PegawaiModel pegawai);
}
