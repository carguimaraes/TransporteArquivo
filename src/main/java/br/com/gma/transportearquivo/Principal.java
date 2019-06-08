package br.com.gma.transportearquivo;

public class Principal {

	public static void main(String[] args) {
		 
		System.out.println("Inicio: "+Principal.class.getName());

		Arquivo arquivo = new Arquivo();
		arquivo.listar();
	}

}
