package br.com.cristal.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_telefones")
@NoArgsConstructor
@Data
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pais;
    private String ddd;
    private String numero;
    private boolean pricipal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
