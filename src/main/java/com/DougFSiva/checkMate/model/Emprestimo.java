package com.DougFSiva.checkMate.model;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"ID"})
@ToString
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;
	
	@ManyToOne
	@JoinColumn(name = "emprestador_id", nullable = false)
	private Usuario emprestador;
	
	@ManyToOne
	@JoinColumn(name = "solicitante_id", nullable = false)
	private Usuario solicitante;
	
	@ManyToOne
	@JoinColumn(name = "recebedor_id", nullable = false)
	private Usuario recebedor;
	
	private LocalDateTime dataHoraEmprestimo;
	private LocalDateTime dataHoraDevolucao;
	private boolean devolvido;
	
	public Emprestimo(Item item, Usuario emprestador, Usuario solicitante, LocalDateTime dataHoraEmprestimo) {
		this.item = item;
		this.emprestador = emprestador;
		this.solicitante = solicitante;
		this.dataHoraEmprestimo = dataHoraEmprestimo;
		this.devolvido = false;
	}
	
}
