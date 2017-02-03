package br.com.fiap.entidades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Escola {
	private int id;
	private String descricao;
	private String endereco;
	private Date dataFundacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public void setDataString(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.setDataFundacao(df.parse(data));
		} catch (Exception e) {
			this.setDataFundacao(new Date());
		}
	}

}
