package com.example.rest.domain.Nodos;

import jakarta.validation.constraints.NotNull;

public record NodoDtoPut(
        @NotNull
        Long id,
        @NotNull
        Nodo nodo,
        @NotNull
        Fase fase,
        @NotNull
        Zona zona


) {
}
