package apap.ta.sifactory.service;

import java.util.List;
import java.util.Optional;

import apap.ta.sifactory.model.RequestUpdateItemModel;
import apap.ta.sifactory.repository.RequestUpdateItemDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestUpdateItemServiceImpl implements RequestUpdateItemService {
    @Autowired
    RequestUpdateItemDb requestUpdateItemDb;

    @Override
    public List<RequestUpdateItemModel> getRequestList() {
        return requestUpdateItemDb.findAll();
    }

    @Override
    public RequestUpdateItemModel getRequestByIdRequest(Integer id_request) {
        Optional<RequestUpdateItemModel> requestItem = requestUpdateItemDb.findById(id_request);
        if (requestItem.isPresent()) {
            return requestItem.get();
        }
        return null;
    }
}
