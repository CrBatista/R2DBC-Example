package com.crbatista.service;

import com.crbatista.entity.Greeting;
import com.crbatista.repository.GreetingRepository;
import com.crbatista.service.impl.ExampleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.fail;

@ExtendWith(MockitoExtension.class)
public class ExampleServiceUnitTest {

    @Mock
    private GreetingRepository greetingRepository;

    @InjectMocks
    private ExampleServiceImpl exampleService;

    @Captor
    private ArgumentCaptor<Long> greetingCaptor;

    @Test
    public void findByIdSuccess() {
        final Greeting repositoryResponse = mock(Greeting.class);
        final Long greetingId = 1L;

        when(greetingRepository.findById(greetingCaptor.capture())).thenReturn(Mono.just(repositoryResponse));

        exampleService.findById(greetingId).subscribe(
                greeting -> {
                    assertEquals(repositoryResponse.getId(), greeting.getId());
                    assertEquals(repositoryResponse.getMessage(), greeting.getMessage());
                },
                errorPayload -> fail("This step should never be reached")
        );

        verify(greetingRepository, times(1)).findById(greetingId);
        assertEquals(greetingId, greetingCaptor.getValue());
    }
}
