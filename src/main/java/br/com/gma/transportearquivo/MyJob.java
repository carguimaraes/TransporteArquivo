package br.com.gma.transportearquivo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 
		Arquivo arquivo = new Arquivo();
		DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println("Processado em: "+dateFormater.format(date)); //2016/11/16 12:08:43
		 
		
		arquivo.openConexao();
		
		List<ArquivoInfo> listArquivoInfo=	arquivo.getListaArquivo();
	
		arquivo.download(listArquivoInfo);
		
		arquivo.closeConexao();
		
	}

}
