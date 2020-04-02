package com.github.blogw.event4springboot.listener;

import com.github.blogw.event4springboot.core.MemoryData;
import com.github.blogw.event4springboot.entity.MService;
import com.github.blogw.event4springboot.event.EntityCreateEvent;
import com.github.blogw.event4springboot.event.InvitationAcceptEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class DemoListener {
    @EventListener
    void onContextRefreshed(final ContextRefreshedEvent event) {
        log.info(
                "Context refreshed, number of beans: {}",
                event.getApplicationContext().getBeanDefinitionCount()
        );
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    void onBeforeCommit(final EntityCreateEvent<MService> event) {
        log.info("onBeforeCommit {}", event);
        log.info(MemoryData.stringData.get());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onAfterCommit(final EntityCreateEvent<MService> event) {
        log.info("onAfterCommit {}", event);
        log.info(MemoryData.stringData.get());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    @Async
    void onAfterCompletion(final EntityCreateEvent<MService> event) {
        log.info("onAfterCompletion {}", event);
        log.info(MemoryData.stringData.get());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    void onAfterRollback(final EntityCreateEvent<MService> event) {
        log.info("onAfterRollback {}", event);
        log.info(MemoryData.stringData.get());
    }

    @EventListener
    @Order(2)
    void onInvitationAccepted1(final InvitationAcceptEvent event) {
        log.info("onInvitationAccepted1 {}", event);
    }

    @EventListener(condition = "#event.customerId=='zidane'")
    @Order(1)
    void onInvitationAccepted2(final InvitationAcceptEvent event) {
        log.info("onInvitationAccepted2 {}", event);
    }
}
