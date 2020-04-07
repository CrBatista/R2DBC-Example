package com.crbatista.controller;

import com.crbatista.payload.GreetingPayload;
import com.crbatista.service.ExampleService;
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
public class GreetingControllerUnitTest {

    @Mock
    private ExampleService exampleService;

    @InjectMocks
    private GreetingController greetingController;

    @Captor
    private ArgumentCaptor<Long> greetingCaptor;

    @Test
    public void getGreetingProperParam() {
        final GreetingPayload serviceResponse = mock(GreetingPayload.class);
        final Long greetingId = 1L;

        when(exampleService.findById(greetingCaptor.capture())).thenReturn(Mono.just(serviceResponse));

        greetingController.greeting(greetingId).subscribe(
            greetingPayload -> assertEquals(serviceResponse, greetingPayload),
            errorPayload -> fail("This step should never be reached")
        );

        verify(exampleService, times(1)).findById(greetingId);
        assertEquals(greetingId, greetingCaptor.getValue());
    }
}
