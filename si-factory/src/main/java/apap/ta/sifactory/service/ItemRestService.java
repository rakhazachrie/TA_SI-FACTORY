package apap.ta.sifactory.service;

import java.util.List;

import apap.ta.sifactory.rest.APIResponseAgung;
import apap.ta.sifactory.rest.ItemDetail;
import apap.ta.sifactory.rest.ProposeItem;

public interface ItemRestService {
    List<ItemDetail> getItemList();

    ItemDetail getItem(String uuid);

    ItemDetail updateItem(ItemDetail item);

    APIResponseAgung<ProposeItem> addItem(ProposeItem item);
}
