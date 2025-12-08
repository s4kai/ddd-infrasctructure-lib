package com.sakai.infrastructure.events;

import com.sakai.domain.events.DomainEvent;
import com.sakai.domain.events.DomainEventPublisher;
import com.sakai.logging.DomainLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher publisher;
    private final DomainLogger logger = DomainLogger.get(SpringDomainEventPublisher.class);

    @Override
    public void publish(DomainEvent domainEvent) {
        logger.trace("Evento publicado: {}", domainEvent);
        publisher.publishEvent(domainEvent);
    }

    public void publishAllEvents(List<DomainEvent> events) {
        logger.trace("Publicando {} eventos", events.size());
        events.forEach(this::publish);
    }
}

