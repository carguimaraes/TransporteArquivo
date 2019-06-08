package br.com.gma.transportearquivo;

import java.util.List;

//https://www.codejava.net/java-se/ftp/java-ftp-list-files-and-directories-example
//https://www.codejava.net/java-se/ftp/java-ftp-file-download-tutorial-and-example

public class Principal {

	public static void main(String[] args) {
		 
		System.out.println("Inicio: "+Principal.class.getName());

		Arquivo arquivo = new Arquivo();
		
		List<ArquivoInfo> listArquivoInfo=	arquivo.getListaArquivo();
		
		for(ArquivoInfo item:listArquivoInfo) {
			System.out.println("===>"+item.getNome()+" - "+item.getData());
		}
		//arquivo.download();
	}

}
