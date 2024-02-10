package com.events.processor.dto;

import java.util.UUID;

public record EventMessage(UUID id, String eventType, Event event) {
}
