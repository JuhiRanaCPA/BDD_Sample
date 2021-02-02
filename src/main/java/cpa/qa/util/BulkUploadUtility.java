package cpa.qa.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;

public class BulkUploadUtility {

	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static String FILE_PATH = System.getProperty("user.dir");
	private static Object[][] data;

	public static void initExcelWorkbook(String IP_type, List<String> column_names) {
		data = new Object[1][column_names.size()];
		FILE_PATH = FILE_PATH + "\\upload\\" + IP_type + ".xlsx";
		for (int i = 0; i < column_names.size(); i++){
			data[0][i] = column_names.get(i);
		}
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet(IP_type);

		int rowNum = 0;

		for (Object[] rowData : data) {
			Row row = sheet.createRow(rowNum);
			int colNum = 0;
			for (Object field : rowData) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue((String) field);
			}
		}

		try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)) {
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				workbook.close();
			} catch (IOException e) {}

		}
	}

	/*public void readFromExcel() {
		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_PATH));
			workbook = new XSSFWorkbook(excelFile);
			sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						System.out.print(currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print(currentCell.getNumericCellValue() + "--");
					}
				}
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				workbook.close();
			} catch (IOException e) {}

		}
	}*/

	public static void writeToExcel(String IPtype, List<String> column_data) {

		try (FileInputStream inputStream = new FileInputStream(FILE_PATH)){
			workbook = new XSSFWorkbook(inputStream);
			sheet = workbook.getSheetAt(0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		int rowNum = sheet.getLastRowNum();

		data = new Object[1][column_data.size()];
		for (int i = 0; i < column_data.size(); i++){
			data[0][i] = column_data.get(i);
		}

		/*XSSFDataFormat fmt = workbook.createDataFormat();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(fmt.getFormat("@"));*/

		for (Object[] rowData : data) {
			Row row = sheet.createRow(++rowNum);
			int colNum = 0;
			for (Object field : rowData) {
				Cell cell = row.createCell(colNum++);
//				cell.setCellStyle(cellStyle);
//				cell.setCellValue((String) field);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				} else if (field instanceof Long) {
					cell.setCellValue((Long) field);
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);
				}
			}
		}

		try (FileOutputStream outputStream = new FileOutputStream(FILE_PATH)){
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				workbook.close();
			} catch (IOException e) {}

		}
	}
}
