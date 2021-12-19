package maikon.cartas.api.entity;

import lombok.Getter;
import maikon.cartas.api.enumerated.ClasseCarta;
import maikon.cartas.api.enumerated.Tipo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
public class Carta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nome;

    private String descricao;

    private int ataque;

    private int defesa;

    @Enumerated(value = EnumType.STRING)
    private Tipo tipo;

    @Enumerated(value = EnumType.STRING)
    private ClasseCarta classeCarta;

}
