package br.com.cristal.resource.controller;

import br.com.cristal.domain.dto.TelefoneDTO;
import br.com.cristal.resource.api.TelefoneAPI;
import br.com.cristal.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes/{idCliente}/telefones")
@RequiredArgsConstructor
public class TelefoneController implements TelefoneAPI {

    private final TelefoneService telefoneService;


    @GetMapping
    @Override
    public List<TelefoneDTO> buscarTodos(@PathVariable Long idCliente) {
        return telefoneService.buscarTodos(idCliente);
    }

    @PostMapping
    @Override
    public TelefoneDTO adicionar(@PathVariable Long idCliente, @RequestBody TelefoneDTO telefoneDTO) {
        return telefoneService.adicionar(idCliente, telefoneDTO);
    }

    @DeleteMapping("/{idTelefone}")
    @Override
    public void remover(@PathVariable Long idCliente, @PathVariable Long idTelefone) {
        telefoneService.remover(idCliente, idTelefone);
    }

    @PutMapping
    @Override
    public TelefoneDTO alterar(@PathVariable Long idCliente, @RequestBody TelefoneDTO telefoneDTO) {
        return telefoneService.alterar(idCliente, telefoneDTO);
    }
}
