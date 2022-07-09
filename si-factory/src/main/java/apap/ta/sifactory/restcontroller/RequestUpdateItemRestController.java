package apap.ta.sifactory.restcontroller;

import apap.ta.sifactory.model.RequestUpdateItemModel;
import apap.ta.sifactory.service.RequestUpdateItemRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;

@RestController
public class RequestUpdateItemRestController {
    @Autowired
    private RequestUpdateItemRestService requestUpdateItemRestService;

    @PostMapping(value = "/cabang/{id_cabang}/req-update")
    private RequestUpdateItemModel createRequest(
        @Valid 
        @RequestBody RequestUpdateItemModel request,
        BindingResult bindingResult) {
            if (bindingResult.hasFieldErrors()) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
                );
            }
            else {
                return requestUpdateItemRestService.addRequest(request);
            }
    }
}