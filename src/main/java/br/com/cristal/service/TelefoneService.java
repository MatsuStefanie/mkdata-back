package br.com.cristal.service;


import br.com.cristal.domain.dto.TelefoneDTO;
import br.com.cristal.domain.model.Cliente;
import br.com.cristal.domain.model.Telefone;
import br.com.cristal.domain.repository.ClienteRepository;
import br.com.cristal.domain.repository.TelefoneRepository;
import br.com.cristal.resource.exception.GenericError;
import br.com.cristal.service.mapper.TelefoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;

    private final ClienteRepository clienteRepository;
    private final TelefoneMapper telefoneMapper;
    public void cadastrar(List<TelefoneDTO> telefones, Cliente cliente) {

        if(telefones.stream().filter(telefoneDTO -> telefoneDTO.isPrincipal())
                .count() > 1) {
            throw new RuntimeException("Telefones não vinculados, pois possuem mais de um principal");
        }

        List<Telefone> telefonesEntity = telefones.stream()
                .map(telefoneMapper::toEntity)
                .peek(telefone -> telefone.setCliente(cliente))
                .toList();

        telefoneRepository.saveAll(telefonesEntity);
    }

    public List<TelefoneDTO> buscarTodos(Long idCliente) {
        return telefoneRepository.findAllByClienteId(idCliente)
                .stream()
                .map(telefoneMapper::toResponse)
                .toList()
                ;
    }

    @Transactional
    public TelefoneDTO adicionar(Long idCliente, TelefoneDTO telefoneDTO) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> GenericError.notFound("Usuario não encontrado"));

        verificarTelefonePrincipal(idCliente, telefoneDTO);

        Telefone telefone = telefoneMapper.toEntity(telefoneDTO);

        telefone.setCliente(cliente);

        telefoneRepository.save(telefone);

        return telefoneMapper.toResponse(telefone);
    }

    @Transactional
    public void remover(Long idCliente, Long idTelefone) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> GenericError.notFound("Usuario não encontrado"));
        Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow(() -> GenericError.notFound("Telefone não encontrado"));

        if(telefone.isPricipal()) {
            Telefone telefone2 = cliente.getTelefones().stream()
                    .filter(telefone1 -> !telefone1.getId().equals(telefone.getId())).min(Collections.reverseOrder())
                    .orElseThrow(() -> GenericError.badRequest("Não foi possivel deletar o telefone pois não existe outro telene para esse cliente"));

            telefone2.setPricipal(true);
            telefoneRepository.save(telefone2);
        }

        telefoneRepository.delete(telefone);
    }

    @Transactional
    public TelefoneDTO alterar(Long idCliente, TelefoneDTO telefoneDTO) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> GenericError.notFound("Usuario não encontrado"));

        Telefone telefoneOld = cliente.getTelefones().stream().filter(telefone -> telefone.getId().equals(telefoneDTO.getId()))
                .findFirst()
                .orElseThrow(() -> GenericError.badRequest("Telefone não pertence ao cliente"));


        verificarTelefonePrincipal(idCliente, telefoneDTO);

        telefoneMapper.updateEntity(telefoneDTO, telefoneOld);

        Telefone telefoneNew = telefoneRepository.save(telefoneOld);
        return telefoneMapper.toResponse(telefoneNew);
    }

    private void verificarTelefonePrincipal(Long idCliente, TelefoneDTO telefoneDTO) {
        if(telefoneDTO.isPrincipal()) {
            telefoneRepository.flagPrincipal(idCliente);
        }
    }
}
