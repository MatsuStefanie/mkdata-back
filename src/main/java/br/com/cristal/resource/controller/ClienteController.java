package br.com.cristal.resource.controller;

import br.com.cristal.domain.dto.ClienteDTO;
import br.com.cristal.domain.model.Cliente;
import br.com.cristal.resource.api.ClienteAPI;
import br.com.cristal.resource.filtro.FiltroCliente;
import br.com.cristal.service.ClienteService;
import br.com.cristal.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController implements ClienteAPI {

    private final ClienteService clienteService;
    private final TelefoneService telefoneService;

    @GetMapping
    @Override
    public Page<ClienteDTO> consultarClientes(FiltroCliente filtroCliente, Pageable pageable) {
        return clienteService.consultarClientes(filtroCliente, pageable);
    }

    @GetMapping("/{id}")
    @Override
    public ClienteDTO buscarClientePorId(@PathVariable Long id) {
        return clienteService.buscarClientePorId(id);
    }

    @PostMapping
    @Override
    public void cadastrar(@RequestBody @Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.cadastrar(clienteDTO);
        telefoneService.cadastrar(clienteDTO.getTelefones(), cliente);
    }

    @PutMapping("/{id}")
    @Override
    public ClienteDTO alterar(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.alterar(id, clienteDTO);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }


}
