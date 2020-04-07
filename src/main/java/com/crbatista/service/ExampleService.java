package com.crbatista.service;

import com.crbatista.payload.GreetingPayload;
import reactor.core.publisher.Mono;

public interface ExampleService {

    Mono<GreetingPayload> findById(Long id);

}
