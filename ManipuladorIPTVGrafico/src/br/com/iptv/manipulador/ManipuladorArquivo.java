package br.com.iptv.manipulador;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class ManipuladorArquivo {

    public void Principal(String m3uFilePath, String excelFilePath) {
//        String m3uFilePath = "get.php";
//        String excelFilePath = "getsaida.xlsx";

        try {
            List<Canal> canais = lerArquivoM3U(m3uFilePath);
            criarArquivoExcel(canais, excelFilePath);
             JOptionPane.showMessageDialog(null, "Arquivo Excel criado com sucesso!");
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, e);
        }
    }

    private static List<Canal> lerArquivoM3U(String filePath) throws IOException {
        List<Canal> canais = new ArrayList<>();
        List<String> linhas = Files.readAllLines(Paths.get(filePath));

        for (int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if (linha.startsWith("#EXTINF:")) {               
                int car_ini = linha.indexOf("group-title=\"");
				int car_tot = linha.length();
				String original = linha.substring(car_ini+13,car_tot);
                original = original.replace("\"", "");
                original = original.replace("\"", "");
                String[] infos = original.split(",");
                String tipo = infos.length > 0 ? infos[0] : "Sem Tipo";
                String grupo = infos.length > 1 ? infos[0] : "Sem Grupo";
                String nomeCanal = infos.length > 1 ? infos[1] : "Sem Canal";
                String link = linhas.get(i + 1).trim();
                canais.add(new Canal(grupo, tipo, nomeCanal, link));
            }
        }

        return canais;
    }

    private static void criarArquivoExcel(List<Canal> canais, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Canais");

        // Cabeçalho
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Grupo");
        headerRow.createCell(1).setCellValue("Tipo");
        headerRow.createCell(2).setCellValue("Canal");
        headerRow.createCell(3).setCellValue("Link");

        // Preenchendo os dados
        int rowNum = 1;
        for (Canal canal : canais) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(canal.getGrupo());
            row.createCell(1).setCellValue(canal.getTipo());
            row.createCell(2).setCellValue(canal.getNomeCanal());
            row.createCell(3).setCellValue(canal.getLink());
        }

        // Ajustando o tamanho das colunas
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escrevendo o arquivo
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch(IOException e) {
        	JOptionPane.showMessageDialog(null, e);
        }

        workbook.close();
    }

    static class Canal {
        private String grupo;
        private String tipo;
        private String nomeCanal;
        private String link;

        public Canal(String grupo, String tipo, String nomeCanal, String link) {
            this.grupo = grupo;
            this.tipo = tipo;
            this.nomeCanal = nomeCanal;
            this.link = link;
        }

        public String getGrupo() {
            return grupo;
        }

        public String getTipo() {
            return tipo;
        }

        public String getNomeCanal() {
            return nomeCanal;
        }

        public String getLink() {
            return link;
        }
    }
}