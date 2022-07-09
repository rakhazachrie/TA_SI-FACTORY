package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.ProduksiModel;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduksiDb extends JpaRepository<ProduksiModel, Integer> {
    List<ProduksiModel> findAllByIdItem(String id_item);
}
