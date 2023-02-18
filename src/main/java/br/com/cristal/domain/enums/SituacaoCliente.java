package br.com.cristal.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SituacaoCliente {
    INATIVO(0),
    ATIVO(1);

    private final int situacao;

    public int getSituacao() {
        return this.situacao;
    }
}
