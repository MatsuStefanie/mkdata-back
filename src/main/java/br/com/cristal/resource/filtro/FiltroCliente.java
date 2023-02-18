package br.com.cristal.resource.filtro;

import br.com.cristal.domain.enums.SituacaoCliente;
import br.com.cristal.domain.enums.TipoFederativo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FiltroCliente {
    private String nome;
    private String identificacaoFederal;
    private TipoFederativo tipoFederativo;
    private SituacaoCliente situacao;
}
