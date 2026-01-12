package es.fplumara.dam2.springdemo.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateNoteRequest(@NotBlank String text) {
}

