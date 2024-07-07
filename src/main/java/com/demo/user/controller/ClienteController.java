package com.demo.user.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.jwt.JWTUtil;
import com.demo.user.service.ClienteService;
import com.demo.user.util.ClienteRequestDTO;
import com.demo.user.util.ClienteResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1/cliente")
@AllArgsConstructor
public class ClienteController {

   private ClienteService clienteService;

   private final JWTUtil jwtUtil;

   @PostMapping
   public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody @Validated ClienteRequestDTO request) {
      clienteService.crearCliente(request);
      String jwtToken = jwtUtil.issueToken(request.getNombre(), "ROLE_USER");
      return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtToken).build();
   }

   @GetMapping("/{clienteId}")
   public ClienteResponseDTO getClienteById(@PathVariable Long clienteId) {
      return clienteService.getCliente(clienteId);
   }

   public void updateCliente(@RequestBody @Validated ClienteRequestDTO request) {

   }

   public void eliminarCliente(@RequestBody @Validated ClienteRequestDTO request) {

   }

   @GetMapping
   public List<ClienteResponseDTO> getAllCliente() {
      return clienteService.getAllCliente();

   }
}
