package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.PegawaiModel;
import apap.ta.sifactory.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Integer> {
    PegawaiModel findByUsername(String username);
    List<PegawaiModel> findAllByRole(RoleModel role);
}
