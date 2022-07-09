package apap.ta.sifactory.restcontroller;

import apap.ta.sifactory.model.MesinModel;
import apap.ta.sifactory.service.MesinRestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class MesinRestController {
    @Autowired
    private MesinRestService mesinRestService;

    @GetMapping(value = "/list-mesin")
    private List<MesinModel> getListMesin() {
        return mesinRestService.getMesinList();
    }
}
