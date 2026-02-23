package com.serafim.core_store.dto;

import java.time.Instant;

public record ExceptionResponseDTO(
        Object message,
        String error,
        Instant timestamp
) {
}
