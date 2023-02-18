package br.com.cristal.domain.repository.specifications;

import br.com.cristal.domain.enums.SituacaoCliente;
import br.com.cristal.domain.enums.TipoFederativo;
import br.com.cristal.domain.model.Cliente;
import br.com.cristal.resource.filtro.FiltroCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class ClienteSpecification implements Specification<Cliente> {

    private final FiltroCliente filtroCliente;

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Specification<Cliente> spec = ClienteSpecification
                .filtrar(filtroCliente, FiltroCliente::getNome, "nome")
                .and(
                        ClienteSpecification
                                .filtrar(filtroCliente, FiltroCliente::getIdentificacaoFederal, "identificacaoFederal")
                ).and(
                        ClienteSpecification
                                .filtrar(filtroCliente, (filtroClienteparam -> Optional.ofNullable(filtroClienteparam.getTipoFederativo()).map(TipoFederativo::name).orElse(null)), "tipoFederativo")
                )
                .and(
                        ClienteSpecification
                                .filtrar(filtroCliente, (filtroClienteparam -> Optional.ofNullable(filtroClienteparam.getSituacao()).map(SituacaoCliente::getSituacao).map(Object::toString).orElse(null)), "situacao")
                );

        return spec.toPredicate(root, query, criteriaBuilder);
    }

    public static Specification<Cliente> filtrar(FiltroCliente filtroCliente, Function<FiltroCliente, String> function, String campo) {
        return ((root, query, criteriaBuilder) ->
                Optional.ofNullable(filtroCliente)
                        .map(function)
                        .map(String::toLowerCase)
                        .map(nomefilter ->
                                criteriaBuilder.and(
                                        criteriaBuilder.like(criteriaBuilder.lower(root.get(campo).as(String.class)), "%" + nomefilter + "%")
                                )
                        ).orElse(null)
        );
    }
}
