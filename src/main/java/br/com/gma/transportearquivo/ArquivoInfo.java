package br.com.gma.transportearquivo;

public class ArquivoInfo {
	
	private String nome;
	private String data;
	
	public ArquivoInfo(String nome, String data){
		this.nome=nome;
		this.data=data;
	}

	public String getNome() {
		return nome;
	}

	public String getData() {
		return data;
	}

	
}
