package com.events.processor.event.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EventAcknowledgeMessage(UUID id, String message) {
}
