package br.com.gma.transportearquivo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Arquivo {
	
	public void listar() {
		String serverFtp="192.168.15.2";
		int portFtp=21;
		String userFtp="gma";
		String pswFtp="123";
		
		FTPClient ftpClient = new FTPClient();
		try {
			
			ftpClient.connect(serverFtp, portFtp);
			ftpClient.login(userFtp, pswFtp);
			
			FTPFile[] files = ftpClient.listFiles();
			DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (FTPFile file : files) {
			    String details = file.getName();
			    if (file.isDirectory()) {
			        details = "[" + details + "]";
			    }
			    details += "\t\t" + file.getSize();
			    details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
			    System.out.println(details);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
