package apap.ta.sifactory.service;

import apap.ta.sifactory.model.RequestUpdateItemModel;
import apap.ta.sifactory.repository.RequestUpdateItemDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RequestUpdateItemRestServiceImpl implements RequestUpdateItemRestService {
    @Autowired
    private RequestUpdateItemDb requestUpdateItemDb;

    @Override
    public RequestUpdateItemModel addRequest(RequestUpdateItemModel request) {
        return requestUpdateItemDb.save(request);
    }
}