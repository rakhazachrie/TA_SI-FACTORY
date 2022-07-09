package apap.ta.sifactory.rest;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MesinResponse {
    private String status;
    private String message;
    private List<MesinDetail> result;

    public MesinResponse(String status, String message, List<MesinDetail> result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
