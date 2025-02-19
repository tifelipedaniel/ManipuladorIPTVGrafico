package br.com.iptv.geraexcel;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class GeraExcel {

	public void writeExcel (List<List<String>> lista, String arquivo) throws IOException, RowsExceededException, WriteException {
		
		// Cria um novo workbook e uma planilha
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados");

        //cabeçalho
        String[] campos = new String[4];
        campos[0] = "TIPO";campos[1] = "GRUPO";campos[2] = "CANAL";campos[3] = "LINK";
        lista.addFirst(Arrays.asList(campos));

        // Itera sobre a lista de dados e escreve na planilha
        int rowNum = 0;
        for (List<String> rowData : lista) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
            }
        }

        // Escreve o arquivo Excel
        try (FileOutputStream outputStream = new FileOutputStream(arquivo)) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "Arquivo Excel criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}
