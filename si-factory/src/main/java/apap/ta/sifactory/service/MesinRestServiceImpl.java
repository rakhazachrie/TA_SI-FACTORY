//
//  BELUM ADA API-NYA GUYS!
//

package apap.ta.sifactory.service;

import apap.ta.sifactory.model.MesinModel;
import apap.ta.sifactory.repository.MesinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MesinRestServiceImpl implements MesinRestService {
    @Autowired
    private MesinDb mesinDb;

    @Override
    public List<MesinModel> getMesinList() {
        return mesinDb.findAll();
    }
}
