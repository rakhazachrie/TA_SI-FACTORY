package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Integer> {
    RoleModel findByNamaRole(String namaRole);
}
