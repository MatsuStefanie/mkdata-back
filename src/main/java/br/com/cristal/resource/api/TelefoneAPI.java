package br.com.cristal.resource.api;

import br.com.cristal.domain.dto.TelefoneDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Telefone de Clientes", description = "API para o gerencimanto de telefones dos clientes")
public interface TelefoneAPI {

    List<TelefoneDTO> buscarTodos(Long idCliente);

    TelefoneDTO adicionar(Long idCliente, TelefoneDTO telefoneDTO);

    void remover(Long idCliente, Long idTelefone);

    TelefoneDTO alterar(Long idCliente, TelefoneDTO telefoneDTO);

}
