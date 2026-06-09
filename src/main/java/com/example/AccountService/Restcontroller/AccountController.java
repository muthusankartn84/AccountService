package com.example.AccountService.Restcontroller;

import com.example.AccountService.Dto.TransactionRequest;
import com.example.AccountService.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
        private final AccountService accountService;

        public AccountController(final AccountService accountService){
            this.accountService=accountService;
        }
        @PostMapping("/{accountId}/transactions")
        public ResponseEntity<Void> applyTransaction(
                @PathVariable String accountId,
                @RequestBody TransactionRequest request) {

            accountService.applyTransaction(request);

            return ResponseEntity.ok().build();
        }

        @GetMapping("/{accountId}/balance")
        public ResponseEntity<BigDecimal> balance(
                @PathVariable String accountId) {

            return ResponseEntity.ok(
                    accountService.getBalance(accountId));
        }

        @GetMapping("/health")
        public ResponseEntity<String> health() {
            return ResponseEntity.ok("UP");
        }
    }
