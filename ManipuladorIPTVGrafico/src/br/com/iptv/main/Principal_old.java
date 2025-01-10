package br.com.iptv.main;
import java.io.IOException;
import java.util.Scanner;

public class Principal_old {

	public static void main(String args[]) throws IOException {

		String arq_ori = "";
		String arq_des = "";
		
		Scanner in_ori = new Scanner(System.in);
		System.out.println("Escreva o caminho completo do arquivo de origem: ");
		arq_ori = in_ori.nextLine();
		
		Scanner in_des = new Scanner(System.in);
		System.out.println("Escreva o caminho completo do arquivo de destino: ");
		arq_des = in_des.nextLine();
		
		//ManipuladorArquivo.manipulador(arq_ori,arq_des);
		
		in_ori.close();
		in_des.close();
				
	}

}