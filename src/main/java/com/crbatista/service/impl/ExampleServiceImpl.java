package com.crbatista.service.impl;

import com.crbatista.payload.GreetingPayload;
import com.crbatista.repository.GreetingRepository;
import com.crbatista.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExampleServiceImpl implements ExampleService {

    private GreetingRepository greetingRepository;

    @Autowired
    public ExampleServiceImpl(final GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @Override
    public Mono<GreetingPayload> findById(final Long id) {
        return greetingRepository.findById(id)
                .map(greeting -> new GreetingPayload(greeting.getId(), greeting.getMessage()));
    }
}
