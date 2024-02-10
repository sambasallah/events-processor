package com.events.processor.dto;


import java.util.HashMap;
import java.util.UUID;

public record Event(UUID id, HashMap<String, Object> message) {
}
