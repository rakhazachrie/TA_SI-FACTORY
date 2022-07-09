package apap.ta.sifactory.repository;

import apap.ta.sifactory.model.RequestUpdateItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestUpdateItemDb extends JpaRepository<RequestUpdateItemModel, Integer> {
    Optional<RequestUpdateItemModel> findById(Integer id_request);
}
