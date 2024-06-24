package com.demo.user.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.user.entity.Cliente;

@Mapper(mappingInheritanceStrategy = MappingInheritanceStrategy.EXPLICIT)
public interface ClienteMapper {

   ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

   Cliente toEntity(ClienteRequestDTO clienteRequestDTO);

   @Mappings({
      @Mapping(source = "personaId", target="personaId")
   })
   ClienteResponseDTO toDto(Cliente cliente);

   List<ClienteResponseDTO> toDtoList(List<Cliente> clienteList);
}
