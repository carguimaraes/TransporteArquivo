package br.com.gma.transportearquivo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Arquivo {

	private String serverFtp;
	private int portFtp;
	private String userFtp;
	private String pswFtp;
	private FTPClient ftpClient;
	private String pastaSaida;
	

	public Arquivo() {
		// TODO pegar arquivo prop
		this.serverFtp = "192.168.15.2";
		this.portFtp = 21;
		this.userFtp = "gma";
		this.pswFtp = "123";
		this.pastaSaida="/ftp/dados/";
		
		this.ftpClient = new FTPClient();
	}

	public void openConexao() {
		try {
			ftpClient.connect(serverFtp, portFtp);
			ftpClient.login(userFtp, pswFtp);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeConexao() {
		try {
			if (ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<ArquivoInfo> getListaArquivo() {

		List<ArquivoInfo> listArquivoInfo = null;

		try {

			listArquivoInfo = new ArrayList<>();
			FTPFile[] files = ftpClient.listFiles();
			DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (FTPFile file : files) {

				listArquivoInfo
						.add(new ArquivoInfo(file.getName(), dateFormater.format(file.getTimestamp().getTime())));

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return listArquivoInfo;
	}

	public void download(List<ArquivoInfo> listArquivoInfo) {

		try {

			for (ArquivoInfo item : listArquivoInfo) {

				 
				String remoteFile = item.getNome();
				String localFile = item.getNome();
				File downloadFile = new File(pastaSaida + localFile);
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
				boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
				outputStream.close();

				if (success) {
					System.out.println("Sucesso download: "+item.getNome());
				}

			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();

		}

	}
}
