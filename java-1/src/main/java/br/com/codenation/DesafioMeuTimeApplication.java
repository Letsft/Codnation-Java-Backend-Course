package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	public List<Time> times = new ArrayList<>();
	public List<Jogador> jogadores = new ArrayList<>();
	public List<Capitao> capitaes = new ArrayList<>();

	public void encontrarTime(Long idTime) {
		if (times.stream().noneMatch(time -> time.getId().equals(idTime)))
			throw new TimeNaoEncontradoException();
	}

	public void encontrarJogador(Long idJogador) {
		if (jogadores.stream().noneMatch(jogador -> jogador.getId().equals(idJogador)))
			throw new JogadorNaoEncontradoException();
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (times.stream().anyMatch(time -> time.getId().equals(id)))
			throw new IdentificadorUtilizadoException();
		times.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		this.encontrarTime(idTime);
		if (jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id)))
			throw new IdentificadorUtilizadoException();
		jogadores.add(new Jogador(id, idTime, nome,dataNascimento, nivelHabilidade, salario));
	}

	public void definirCapitao(Long idJogador) {
		this.encontrarJogador(idJogador);
		Long timeJogadorCapitao = jogadores.stream()
				.filter(jogador -> jogador.getId().equals(idJogador))
				.map(jogador -> jogador.idTime)
				.collect(Collectors.toList()).get(0);
		capitaes.removeIf(capitao -> capitao.getIdTime().equals(timeJogadorCapitao));

		capitaes.add(new Capitao((Jogador) jogadores.stream()
				.filter(jogador -> jogador.getId().equals(idJogador))
				.findAny().get()));
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		this.encontrarTime(idTime);
		if (jogadores.stream().noneMatch(jogador -> jogador.getIdTime().equals(idTime)))
			throw new CapitaoNaoInformadoException();
		return capitaes.stream()
				.filter(capitao -> capitao.getIdTime().equals(idTime))
				.map(capitao -> capitao.id)
				.collect(Collectors.toList()).get(0);

	}

	public String buscarNomeJogador(Long idJogador) {
		this.encontrarJogador(idJogador);
		return jogadores.stream()
				.filter(jogador -> jogador.getId().equals(idJogador))
				.map(jogador -> jogador.nome)
				.collect(Collectors.toList()).get(0);

	}

	public String buscarNomeTime(Long idTime) {
		this.encontrarTime(idTime);
		return times.stream()
				.filter(time -> time.getId().equals(idTime))
				.map(time -> time.nome)
				.collect(Collectors.toList()).get(0);
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		this.encontrarTime(idTime);
		return jogadores.stream()
				.filter(jogador -> jogador.getIdTime().equals(idTime))
				.map(jogador -> jogador.id)
				.collect(Collectors.toList());
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		this.encontrarTime(idTime);
		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed())
				.collect(Collectors.toList()).get(0).getId();
	}


	public Long buscarJogadorMaisVelho(Long idTime) {
		this.encontrarTime(idTime);
		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getDataNascimento)
						.thenComparing(Jogador::getId))
				.collect(Collectors.toList()).get(0).getId();
	}

	public List<Long> buscarTimes() {

		return times.stream()
				.map(time -> time.id)
				.collect(Collectors.toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		this.encontrarTime(idTime);
		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getSalario).reversed()
						.thenComparing(Jogador::getId))
				.collect(Collectors.toList()).get(0).getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		this.encontrarJogador(idJogador);
		return jogadores.stream()
				.filter(jogador -> jogador.getId().equals(idJogador))
				.map(jogador -> jogador.salario)
				.collect(Collectors.toList()).get(0);

	}

	public List<Long> buscarTopJogadores(Integer top) {
		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
						.thenComparing(Jogador::getId))
				.map(jogador -> jogador.id).limit(top)
				.collect(Collectors.toList());
	}

}
