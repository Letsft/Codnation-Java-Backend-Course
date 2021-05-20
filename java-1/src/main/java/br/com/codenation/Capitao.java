package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class Capitao extends Jogador{

    public Capitao(Jogador jogador) {
        super(jogador.getId(), jogador.getIdTime(), jogador.getNome(), jogador.getDataNascimento(), jogador.getNivelHabilidade(), jogador.getSalario());
    }

}
