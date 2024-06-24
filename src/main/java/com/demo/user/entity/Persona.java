package com.demo.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long personaId;

   private String nombre;

   private String direccion;

   private String telefono;

   @Enumerated(EnumType.STRING)
   private EGenero genero;

   private Integer edad;
}
