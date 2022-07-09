package apap.ta.sifactory.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import apap.ta.sifactory.rest.ListItemResponse;
import apap.ta.sifactory.rest.ProposeItem;
import apap.ta.sifactory.rest.APIResponseAgung;
import apap.ta.sifactory.rest.ItemDetail;
import apap.ta.sifactory.rest.ItemResponse;

import java.util.List;

@Service
public class ItemRestServiceImpl implements ItemRestService {
    private final WebClient webClient;
    private final WebClient webClientBusiness;

    public ItemRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://si-item.herokuapp.com/api").build();
        webClientBusiness = webClientBuilder.baseUrl("https://509a0b63-ea61-4e76-b04d-4dfea8ba7aba.mock.pstmn.io").build();
    }

    @Override
    public List<ItemDetail> getItemList() {
        ListItemResponse api = this.webClient.get().uri("/item").retrieve().bodyToMono(ListItemResponse.class).block();
        return api.getResult();
    }

    @Override
    public ItemDetail getItem(String uuid) {
        ItemResponse api = this.webClient.get().uri("/item/" + uuid).retrieve().bodyToMono(ItemResponse.class).block();
        return api.getResult();
    }

    @Override
    public ItemDetail updateItem(ItemDetail item) {
        Map<String, Integer> data = new HashMap<>();
        data.put("stok", item.getStok());
        System.out.println(data.toString());
        ItemResponse api = this.webClient.put().uri("/item/" + item.getUuid()).syncBody(data).retrieve()
                .bodyToMono(ItemResponse.class).block();
        if (api.getMessage().equals("updated")) {
            return api.getResult();
        }
        return null;
    }

    @Override
    public APIResponseAgung<ProposeItem> addItem(ProposeItem item) {
        APIResponseAgung<ProposeItem> api = webClientBusiness.post().uri("/api/request_item/add")
        .bodyValue(item)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<APIResponseAgung<ProposeItem>>(){}).block();
        return api;
    }
}
