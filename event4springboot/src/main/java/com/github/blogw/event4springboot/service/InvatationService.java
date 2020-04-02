package com.github.blogw.event4springboot.service;

import com.github.blogw.event4springboot.event.InvitationAcceptEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvatationService {
    private final ApplicationEventPublisher eventPublisher;

    void accept(final String customerId) {
        log.info("accept: {}", customerId);
        eventPublisher.publishEvent(new InvitationAcceptEvent(customerId));
    }
}
