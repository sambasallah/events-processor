package com.events.processor.dto;

import lombok.Builder;

@Builder
public record Alive(Boolean isAlive, String message) {
}
