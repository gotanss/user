package com.demo.user.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.user.service.ClienteService;
import com.demo.user.util.ClienteRequestDTO;
import com.demo.user.util.ClienteResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/v1/cliente")
@AllArgsConstructor
public class ClienteController {

   private ClienteService clienteService;

   @PostMapping
   public ClienteResponseDTO crearCliente (@RequestBody @Validated ClienteRequestDTO request){
      return clienteService.crearCliente(request);
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
