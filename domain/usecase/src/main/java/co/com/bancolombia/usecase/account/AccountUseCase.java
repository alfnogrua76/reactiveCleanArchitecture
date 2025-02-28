package co.com.bancolombia.usecase.account;

import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.model.account.gateways.AccountRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AccountUseCase {

    private final AccountRepository repository;

    public Mono<Account> register(String name){
        Account account = Account.builder()
                .id(999L)
                .name(name)
                .build();

        return legalValidation(account)
                .flatMap(text -> externalService(account))
                .flatMap(text -> repository.createAccount(account));
        //return Mono.just(account);
    }

    private Mono<String> legalValidation(Account account){
        //logic
        return Mono.just("OK");
    }

    private Mono<String> externalService(Account account){
        //logic
        return Mono.just("Service ok");
    }
}
