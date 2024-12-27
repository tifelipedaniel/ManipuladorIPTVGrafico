import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class ManipuladorArquivo {
	
	public static void manipulador (String arq_ori, String arq_des) throws IOException {
		
		BufferedReader buffRead = new BufferedReader(new FileReader(arq_ori));
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(arq_des));
		buffWrite.append("TIPO | GRUPO | CANAL" + "\n");
		
		String linha = "";
		while (true) {
			if (linha == null)
					break;
			
			linha = buffRead.readLine();
			if ( (linha != null) && (linha.length() > 45) && (linha.charAt(0) == '#') ) {
				
				String[] campos = new String[3];
				
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
				
				String linhafinal = campos[0] + " | " + campos[1] + " | " + campos[2];
				System.out.println(linhafinal);
				
				//escreve no arquivo
				buffWrite.append(linhafinal + "\n");
			}

			
		}
		
		buffWrite.close();
		buffRead.close();
		
	}

}