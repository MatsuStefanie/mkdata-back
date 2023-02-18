package br.com.cristal.service;

import br.com.cristal.domain.dto.ClienteDTO;
import br.com.cristal.domain.model.Cliente;
import br.com.cristal.domain.repository.ClienteRepository;
import br.com.cristal.domain.repository.specifications.ClienteSpecification;
import br.com.cristal.resource.exception.GenericError;
import br.com.cristal.resource.filtro.FiltroCliente;
import br.com.cristal.service.mapper.ClienteMapper;
import br.com.cristal.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public Page<ClienteDTO> consultarClientes(FiltroCliente filtroCliente, Pageable pageable) {
        ClienteSpecification clienteSpecification = new ClienteSpecification(filtroCliente);

        return clienteRepository.findAll(clienteSpecification, pageable)
                .map(clienteMapper::toResponse)
                ;
    }

    public Cliente cadastrar(ClienteDTO clienteDTO) {

        validarClienteDTO(clienteDTO);
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return clienteRepository.save(cliente);
    }

    private void validarClienteDTO(ClienteDTO clienteDTO) {
        if (!StringUtils.onlyNumbers(clienteDTO.getIdentificacaoFederal()) || !StringUtils.onlyNumbers(clienteDTO.getRegistro())) {
            throw GenericError.badRequest("Parametros de identicacao federal e registro não devem conter mascara");
        }

        if (clienteRepository.existsByIdentificacaoFederal(clienteDTO.getIdentificacaoFederal())) {
            throw GenericError.badRequest("Cliente com identificacao federal já cadastrado");
        }
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> GenericError.notFound("Usuario não encontrado"))
                ;
    }

    public ClienteDTO alterar(Long id, ClienteDTO clienteDTO) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> GenericError.notFound("Usuario não encontrado"));

        clienteMapper.updateEntity(clienteDTO, cliente);
        clienteRepository.save(cliente);
        return clienteMapper.toResponse(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
