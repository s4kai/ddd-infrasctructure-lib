package com.sakai.infrastructure.events;

import com.sakai.domain.entity.AggregateRoot;
import com.sakai.domain.events.DomainEvent;
import com.sakai.domain.events.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(DomainEvent domainEvent) {
        publisher.publishEvent(domainEvent);
    }

    public void publish(AggregateRoot<?> aggregateRoot) {
        List<Object> events = Collections.singletonList(aggregateRoot.getDomainEvents());
        for (Object event : events) {
            publish((DomainEvent) event);
        }
        aggregateRoot.clearDomainEvents();
    }

}

