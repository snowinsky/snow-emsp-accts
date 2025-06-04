package cn.snow.emsp.accts.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class EMSPAccountCardController {

    @PostMapping(value = "/accounts")
    public void createAccounts() {

    }

    @PatchMapping(value = "/accounts/{email}/status")
    public void changeAccountStatus() {

    }

    @PostMapping(value = "/cards")
    public void createCards() {

    }

    @PutMapping(value = "/cards/{cardId}/assign?accountEmail={email}")
    public void assignCardToAccount() {

    }

    @PatchMapping(value = "/cards/{cardId}/status")
    public void changeCardStatus() {

    }

    @GetMapping(value = "/accounts?lastUpdatedAfter={timestamp}&page={page}&size={size}")
    public void queryAccounts() {

    }
}
