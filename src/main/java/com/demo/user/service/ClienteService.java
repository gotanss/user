package com.demo.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.user.entity.Cliente;
import com.demo.user.exception.ClienteNotFoundException;
import com.demo.user.exception.DuplicateResourceException;
import com.demo.user.repository.ClienteRepository;
import com.demo.user.util.ClienteMapper;
import com.demo.user.util.ClienteRequestDTO;
import com.demo.user.util.ClienteResponseDTO;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

   private ClienteRepository clienteRepository;

   private CuentaApiService cuentaApiService;

   @Transactional
   public ClienteResponseDTO crearCliente(ClienteRequestDTO request) {

      if (clienteRepository.existsClienteByTelefono(request.getTelefono())) {
         throw new DuplicateResourceException("error creación");
      }

      Cliente cliente = ClienteMapper.INSTANCE.toEntity(request);
      cliente.setEstado(true);
      ClienteResponseDTO clienteResponseDTO = ClienteMapper.INSTANCE.toDto(clienteRepository.save(cliente));

      cuentaApiService.crearCuentaByDefault(clienteResponseDTO.getPersonaId());

      return ClienteMapper.INSTANCE.toDto(clienteRepository.save(cliente));
   }

   public ClienteResponseDTO getCliente(Long id) {
      return ClienteMapper.INSTANCE.toDto(
            clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("CLiente con id [%s] no encontrado.".formatted(id))));
   }

   public List<ClienteResponseDTO> getAllCliente() {
      return ClienteMapper.INSTANCE.toDtoList(clienteRepository.findAll());
   }
}
