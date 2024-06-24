package com.demo.user.util;

import com.demo.user.entity.EGenero;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ClienteResponseDTO {

   private Long personaId;

   private String nombre;

   private String direccion;

   private String telefono;

   private EGenero genero;

   private Integer edad;

   private Boolean estado;
}
