package br.com.cristal.resource.api;

import br.com.cristal.domain.dto.ClienteDTO;
import br.com.cristal.resource.filtro.FiltroCliente;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Tag(name = "Clientes", description = "API para o gerencimanto de clientes")
public interface ClienteAPI {

    Page<ClienteDTO> consultarClientes(
            @ParameterObject FiltroCliente filtroCliente,
            @ParameterObject Pageable pageable);

    ClienteDTO buscarClientePorId(Long id);

    void cadastrar(@RequestBody ClienteDTO clienteDTO);

    ClienteDTO alterar(Long id, @RequestBody ClienteDTO clienteDTO);

    void deletar(Long id);

}
