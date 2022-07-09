package apap.ta.sifactory.rest;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ListItemResponse {
    private String status;
    private String message;
    private List<ItemDetail> result;

    public ListItemResponse(String status, String message, List<ItemDetail> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
