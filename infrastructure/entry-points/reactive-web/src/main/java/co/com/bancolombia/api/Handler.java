package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.NameAccountDto;
import co.com.bancolombia.model.account.Account;
import co.com.bancolombia.usecase.account.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private  final AccountUseCase useCase;
//private  final UseCase2 useCase2;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        Mono<Account>  register = useCase.register("foo");
        return ServerResponse.ok().body(register, Account.class);
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        var register = serverRequest.bodyToMono(NameAccountDto.class)
                .flatMap(nameAccountDto -> useCase.register(nameAccountDto.getName()));
        return ServerResponse.ok().bodyValue("HELLO");
    }
}
