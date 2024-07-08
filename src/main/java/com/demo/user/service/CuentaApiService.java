package com.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.user.util.CuentaRequestDTO;
import com.demo.user.util.CuentaResponseDTO;
import com.demo.user.util.ETipoCuenta;

import lombok.AllArgsConstructor;

@Service
public class CuentaApiService {

   private static final String ACCOUNT_CREATION_PATH = "/v1/cuenta";

   private final com.demo.clients.user.UserClient userClients;

   @Value("${app.cuenta-api.base-url}")
   private String cuentaBaseUrl;

   @Autowired
   private RestTemplate restTemplate;

   public CuentaResponseDTO crearCuentaByDefault(Long clienteId) {
      //cuenta en pesos y de ahorro
      CuentaRequestDTO cuentaRequestDTO = CuentaRequestDTO.builder()
            .tipoCuenta(ETipoCuenta.AHORROS)
            .currency("ARS")
            .cliente(clienteId)
            .build();
      return restTemplate.postForObject(cuentaBaseUrl + ACCOUNT_CREATION_PATH, cuentaRequestDTO, CuentaResponseDTO.class);
   }

   public CuentaResponseDTO crearCuentaByDefaultFeign(Long clienteId) {
      //cuenta en pesos y de ahorro
      CuentaRequestDTO cuentaRequestDTO = CuentaRequestDTO.builder()
                                                          .tipoCuenta(ETipoCuenta.AHORROS)
                                                          .currency("ARS")
                                                          .cliente(clienteId)
                                                          .build();
      return userClients.crearCliente(cuentaRequestDTO);
   }
}
