package cn.snow.emsp.accts.api;

import cn.snow.emsp.accts.api.vo.*;
import cn.snow.emsp.accts.domain.model.Account;
import cn.snow.emsp.accts.domain.model.Card;
import cn.snow.emsp.accts.domain.model.enums.AccountStatus;
import cn.snow.emsp.accts.domain.model.enums.CardStatus;
import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import cn.snow.emsp.accts.service.AccountCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class EMSPAccountCardController {

    private final AccountCardService accountCardService;

    @PostMapping(value = "/accounts")
    public ResponseEntity<AccountResponse> createAccount(
            @RequestBody @Valid CreateAccountRequest request) {

        Account account = accountCardService.createAccount(request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountResponse.from(account));
    }

    @PatchMapping(value = "/accounts/{email}/status")
    public ResponseEntity<AccountResponse> changeAccountStatus(
            @PathVariable String email,
            @RequestBody @Valid ChangeAccountStatusRequest request) {

        Account account = accountCardService.changeStatusOfAccount(
                email,
                AccountStatus.valueOf(request.getAccountStatus())
        );
        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @GetMapping(value = "/accounts/list")
    public ResponseEntity<Page<PageAccountCard>> getAccountsByLastUpdated(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lastUpdated,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<DbAccountCard> accounts = accountCardService.getAccountsByLastUpdated(
                lastUpdated,
                page, size);
        return ResponseEntity.ok(accounts.map(a -> {
            PageAccountCard response = new PageAccountCard();
            response.setAccountId(a.getAccountId());
            response.setMarkedEmail(a.getMarkedEmail());
            response.setContractId(a.getContractId());
            response.setLastUpdated(a.getLastUpdated());
            response.setAccountStatus(a.getAccountStatusName());
            response.setCardId(a.getCardId());
            response.setCardStatus(a.getCardStatusName());
            response.setMarkedRfidVisibleNumber(a.getMarkedRfidVisibleNumber());
            return response;
        }));
    }


    @PostMapping(value = "/cards")
    public ResponseEntity<CardResponse> createCards(@RequestBody @Valid CreateCardRequest request) {
        Card card = accountCardService.createCard(request.getRfidUid(), request.getRfidVisibleNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CardResponse.from(card));
    }

    @PutMapping(value = "/cards/{cardId}/assign")
    public ResponseEntity<AccountResponse> assignCardToAccount(@PathVariable String cardId, @RequestParam String email) {
        Account account = accountCardService.assignCardToAccount(cardId, email);
        return ResponseEntity.ok(AccountResponse.from(account));
    }

    @PatchMapping(value = "/cards/{cardId}/status")
    public ResponseEntity<CardResponse> changeCardStatus(@PathVariable String cardId,
                                                         @RequestBody @Valid ChangeCardStatusRequest request) {
        Card card = accountCardService.changeStatusOfCard(
                cardId,
                CardStatus.valueOf(request.getStatus())
        );
        return ResponseEntity.ok(CardResponse.from(card));
    }

}
