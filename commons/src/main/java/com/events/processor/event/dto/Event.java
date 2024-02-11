package com.events.processor.event.dto;


import java.util.HashMap;
import java.util.UUID;

public record Event(UUID id, HashMap<String, Object> message) {
}
