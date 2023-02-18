package br.com.cristal.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TelefoneDTO {
    private Long id;
    private String pais;
    private String ddd;
    private String numero;
    private boolean principal;
}
