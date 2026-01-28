package com.serafim.core_store.dto;

import java.time.Instant;

public record ExceptionResponseDTO(
        String message,
        String error,
        Instant timestamp
) {
}
