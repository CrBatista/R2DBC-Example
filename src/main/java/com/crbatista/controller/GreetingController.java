package com.crbatista.controller;

import com.crbatista.payload.GreetingPayload;
import com.crbatista.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GreetingController {

    private ExampleService exampleService;

    @Autowired
    public GreetingController(final ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @RequestMapping("/greeting")
    public Mono<GreetingPayload> greeting(@RequestParam(value="id") Long id) {
        return exampleService.findById(id);
    }
}
