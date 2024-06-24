package com.demo.user.exception;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
   CLIENT_NOT_FOUND("ERR_Cuenta_001", "Cliente no encontrado."),
   CLIENT_CREATION_ERROR("ERR_Cuenta_002", "Error al crear el cliente."),
   INVALID_CLIENT("ERR_Cuenta_003", "Parametros de cliente invalidos."),
   GENERIC_ERROR("ERR_Cuenta_004", "Error insesperado.");

   private final String code;

   private final String message;

   ErrorCatalog(String code, String message) {
      this.code = code;
      this.message = message;
   }
}
