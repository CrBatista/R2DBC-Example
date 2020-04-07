package com.crbatista.repository;

import com.crbatista.entity.Greeting;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GreetingRepository extends ReactiveCrudRepository<Greeting, Long> {

}
