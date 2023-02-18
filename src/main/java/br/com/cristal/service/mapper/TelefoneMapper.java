package br.com.cristal.service.mapper;

import br.com.cristal.domain.dto.TelefoneDTO;
import br.com.cristal.domain.model.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {

    TelefoneDTO toResponse (Telefone telefone);

    Telefone toEntity(TelefoneDTO telefoneDTO);

    void updateEntity(TelefoneDTO telefoneDTO, @MappingTarget Telefone telefone);
}
