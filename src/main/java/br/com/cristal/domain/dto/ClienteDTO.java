package br.com.cristal.domain.dto;

import br.com.cristal.domain.enums.TipoFederativo;
import br.com.cristal.resource.validation.CpfCnpj;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    private TipoFederativo tipoFederativo;
    @NotNull
    @CpfCnpj
    private String identificacaoFederal;
    @NotNull
    private String registro;
    private boolean situacao;
    private List<TelefoneDTO> telefones;
}
