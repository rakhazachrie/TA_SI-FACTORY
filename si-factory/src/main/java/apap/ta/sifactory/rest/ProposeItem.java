package apap.ta.sifactory.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProposeItem {
    private String id;
    private String name;
    private int status;
    private int stock;
    private int price;
    private int category;
    private String cluster;
    @JsonProperty("uuid_approver")
    private String uuidApprover;
}