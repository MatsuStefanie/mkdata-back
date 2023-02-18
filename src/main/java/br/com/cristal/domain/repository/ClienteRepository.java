package br.com.cristal.domain.repository;

import br.com.cristal.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Long> , JpaSpecificationExecutor<Cliente> {

    boolean existsByIdentificacaoFederal(String identificacaoFederal);
}
