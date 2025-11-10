package com.sakai.infrastructure.events;

import domain.entity.AggregateRoot;
import domain.events.DomainEvent;
import domain.events.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(DomainEvent domainEvent) {
        publisher.publishEvent(domainEvent);
    }

    @Override
    public void publish(AggregateRoot<?> aggregateRoot) {
        List<DomainEvent> events = aggregateRoot.getDomainEvents();
        for (DomainEvent event : events) {
            publish(event);
        }
        aggregateRoot.clearDomainEvents();
    }

}

