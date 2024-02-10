package com.events.processor.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EventAcknowledgeMessage(UUID id, String message) {
}
