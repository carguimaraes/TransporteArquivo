package br.com.gma.transportearquivo;


//https://www.codejava.net/java-se/ftp/java-ftp-list-files-and-directories-example

public class Principal {

	public static void main(String[] args) {
		 
		System.out.println("Inicio: "+Principal.class.getName());

		Arquivo arquivo = new Arquivo();
		//arquivo.listar();
		arquivo.download();
	}

}
