package br.com.iptv.ordenaexcel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

public class OrdenaExcel {

    public static void ordenarPlanilha(String filePath) {
        //String filePath = "planilha.xlsx"; // Caminho do arquivo Excel
        int sheetIndex = 0; // Índice da planilha (0 para a primeira planilha)
        int[] columnsToSort = {0, 1, 2}; // Colunas pelas quais você deseja ordenar (primeiro pela coluna 0, depois pela coluna 1)

        try {
            // Carregar o arquivo Excel
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // Extrair dados da planilha
            List<RowData> rowDataList = new ArrayList<>();
            for (Row row : sheet) {
                if (row != null) {
                    rowDataList.add(new RowData(row, columnsToSort));
                }
            }

            // Ordenar os dados com base nas colunas especificadas
            rowDataList.sort((row1, row2) -> {
                for (int columnIndex : columnsToSort) {
                    int comparison = row1.getCellValue(columnIndex).compareTo(row2.getCellValue(columnIndex));
                    if (comparison != 0) {
                        return comparison;
                    }
                }
                return 0; // Se todas as colunas forem iguais
            });

            // Reescrever os dados ordenados na planilha
            int rowIndex = 0;
            for (RowData rowData : rowDataList) {
                Row newRow = sheet.getRow(rowIndex++);
                if (newRow == null) {
                    newRow = sheet.createRow(rowIndex - 1);
                }
                copyRow(rowData.getRow(), newRow);
            }

            // Salvar o arquivo Excel
            FileOutputStream outFile = new FileOutputStream("getsaida_ordenada.xlsx");
            workbook.write(outFile);
            outFile.close();
            workbook.close();
            file.close();

            System.out.println("Planilha ordenada e salva com sucesso!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Método para copiar uma linha para outra
    private static void copyRow(Row sourceRow, Row targetRow) {
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            Cell sourceCell = sourceRow.getCell(i);
            if (sourceCell != null) {
                Cell targetCell = targetRow.createCell(i);
                targetCell.setCellValue(getCellValueAsString(sourceCell));
            }
        }
    }

    // Método para obter o valor da célula como String
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // Classe auxiliar para armazenar dados da linha e células
    private static class RowData {
        private final Row row;
        private final Map<Integer, String> cellValues;

        public RowData(Row row, int[] columnsToSort) {
            this.row = row;
            this.cellValues = new HashMap<>();
            for (int columnIndex : columnsToSort) {
                Cell cell = row.getCell(columnIndex);
                cellValues.put(columnIndex, getCellValueAsString(cell));
            }
        }

        public Row getRow() {
            return row;
        }

        public String getCellValue(int columnIndex) {
            return cellValues.getOrDefault(columnIndex, "");
        }
    }
}