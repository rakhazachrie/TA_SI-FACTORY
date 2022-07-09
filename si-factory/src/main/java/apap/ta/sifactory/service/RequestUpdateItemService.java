package apap.ta.sifactory.service;

import java.util.List;

import apap.ta.sifactory.model.RequestUpdateItemModel;

public interface RequestUpdateItemService {
    List<RequestUpdateItemModel> getRequestList();
    RequestUpdateItemModel getRequestByIdRequest(Integer id_request);
}
