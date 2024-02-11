package com.events.processor.event.dto;

import java.util.UUID;

public record EventMessage(UUID id, String eventType, Event event) {
}
