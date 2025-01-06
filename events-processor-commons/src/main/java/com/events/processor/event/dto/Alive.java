package com.events.processor.event.dto;

import lombok.Builder;

@Builder
public record Alive(Boolean isAlive, String message) {
}
