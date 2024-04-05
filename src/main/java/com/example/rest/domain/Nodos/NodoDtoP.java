package com.example.rest.domain.Nodos;

import jakarta.validation.constraints.NotNull;


public record NodoDtoP(
        @NotNull
        Nodo nodo,

        @NotNull
        Fase fase,

        @NotNull
        Zona zona
) {
}
