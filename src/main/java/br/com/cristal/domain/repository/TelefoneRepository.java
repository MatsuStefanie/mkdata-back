package br.com.cristal.domain.repository;

import br.com.cristal.domain.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {


    List<Telefone> findAllByClienteId(Long idCliente);

    @Modifying
    @Query(value = "update tb_telefones set pricipal = false where id_cliente = ?", nativeQuery = true)
    void flagPrincipal(Long idCliente);
}
