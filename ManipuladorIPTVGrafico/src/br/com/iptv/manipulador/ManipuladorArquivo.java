package br.com.iptv.manipulador;

import br.com.iptv.download.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class ManipuladorArquivo {
	
	public List<List<String>> linhas;
	public String tipo, grupo, canal, link = "";

	public ManipuladorArquivo() {
		this.linhas = new ArrayList<List<String>>();

	}
	
	public void manipulador (String arq_ori, String arq_des, String arq_drive, String link) throws IOException {
		
//		Download download = new Download();
//		download.downloadFile(link, arq_ori);
		
		BufferedReader buffRead = new BufferedReader(new FileReader(arq_ori));
		String linha = "";
		String[] campos = new String[4];
		
		while (true) {
			if (linha == null)
					break;
			
			linha = buffRead.readLine();
			if ( (linha != null) && (linha.length() > 45) && (linha.charAt(0) == '#') ) {
								
				int car_ini = linha.indexOf("group-title=\"");
				int car_tot = linha.length();
				
				String original = "";
				original = linha.substring(car_ini+13,car_tot);
				
				if(original.indexOf("|") == -1) {
					
					String[] originalComSplit = new String[2];
					originalComSplit = original.split("\",");
					campos[2] = originalComSplit[1];
					campos[0] = campos[1] = originalComSplit[0];
					
				} else {
										
					String[] originalComSplit = new String[2];
					originalComSplit = original.split("\",");
					campos[2] = originalComSplit[1];
					
					String[] tempComSplit = new String[2];
					tempComSplit = originalComSplit[0].split(Pattern.quote(" | "));
					campos[0] = tempComSplit[0];
					campos[1] = tempComSplit[1];
					
				}
				
			} else {
				
				campos[3] = linha;
				this.linhas.add(Arrays.asList(campos));
				
			}

		}
		//buffWrite.close();
		buffRead.close();	
	}

}