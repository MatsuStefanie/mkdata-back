package br.com.cristal.domain.model;

import br.com.cristal.domain.enums.TipoFederativo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoFederativo tipoFederativo;

    @Column(name = "identificacao_federal" , length = 14)
    private String identificacaoFederal;

    private String registro;

    private boolean situacao;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Telefone> telefones;
}
