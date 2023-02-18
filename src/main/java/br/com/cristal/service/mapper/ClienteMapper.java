package br.com.cristal.service.mapper;

import br.com.cristal.domain.dto.ClienteDTO;
import br.com.cristal.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toResponse (Cliente cliente);

    @Mapping(target = "telefones", ignore = true)
    Cliente toEntity(ClienteDTO clienteDTO);

    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(ClienteDTO clienteDTO, @MappingTarget Cliente cliente);
}
