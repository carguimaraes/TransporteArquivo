package br.com.gma.transportearquivo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class Arquivo {

	public void listar() {
		String serverFtp = "192.168.15.2";
		int portFtp = 21;
		String userFtp = "gma";
		String pswFtp = "123";

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(serverFtp, portFtp);
			ftpClient.login(userFtp, pswFtp);

			FTPFile[] files = ftpClient.listFiles();
			DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (FTPFile file : files) {
				String details = file.getName();

				details += "\t\t" + file.getSize();
				details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
				System.out.println(details);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void download() {
		String serverFtp = "192.168.15.2";
		int portFtp = 21;
		String userFtp = "gma";
		String pswFtp = "123";

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(serverFtp, portFtp);
			ftpClient.login(userFtp, pswFtp);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: using retrieveFile(String, OutputStream)
			String remoteFile1 = "Funcao-20190601.csv";
			String localFile1 = "Funcao.csv";
			File downloadFile1 = new File("E:/ftp/dados/"+localFile1 );
			OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
			boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
			outputStream1.close();

			if (success) {
				System.out.println("Sucesso download");
			}

			/*
			// APPROACH #2: using InputStream retrieveFileStream(String)
			String remoteFile2 = "/test/song.mp3";
			File downloadFile2 = new File("D:/Downloads/song.mp3");
			OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
			InputStream inputStream = ftpClient.retrieveFileStream(remoteFile2);
			byte[] bytesArray = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(bytesArray)) != -1) {
				outputStream2.write(bytesArray, 0, bytesRead);
			}

			success = ftpClient.completePendingCommand();
			if (success) {
				System.out.println("File #2 has been downloaded successfully.");
			}
			
			outputStream2.close();
			inputStream.close();
			*/

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
