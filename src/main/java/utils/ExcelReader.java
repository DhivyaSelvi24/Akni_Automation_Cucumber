package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private String filePath;
	private Workbook workbook;
	
	public ExcelReader(String filePath) throws IOException {
		this.filePath=filePath;
		
		FileInputStream fis=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fis);
		
	}
	public List<Map<String,String>> getData(String sheetName){

		List<Map<String,String>> sheetData=new ArrayList<>();
		Sheet sheet=workbook.getSheet(sheetName);
		if (sheet==null) throw new RuntimeException("Sheet not found" + sheetName);
		Row headerRow=sheet.getRow(0);
		if(headerRow==null) throw new RuntimeException("No header found" + sheetName);
		
		int totalrows=sheet.getPhysicalNumberOfRows();
		int totalcols=headerRow.getPhysicalNumberOfCells();
	
		 for (int i = 1; i < totalrows; i++) { 
	            Row row = sheet.getRow(i);  
	            if (row == null) continue; 

	            Map<String, String> rowData = new HashMap<>();
	            for (int j = 0; j < totalcols; j++) {
	                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	                String cellValue = getCellValueAsString(cell);
	                String header = headerRow.getCell(j).getStringCellValue().trim();
	                rowData.put(header, cellValue);
	            }
	            sheetData.add(rowData);
	        }
	        return sheetData;
	    }

	    private String getCellValueAsString(Cell cell) {
	        if (cell == null) return "";
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue().trim();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString(); 
	                } else {
	                    double value = cell.getNumericCellValue();
	                    if (value == (long) value) {
	                        return String.valueOf((long) value);
	                    } else {
	                        return String.valueOf(value);
	                    }
	                }
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            case BLANK:
	            case _NONE:
	            case ERROR:
	            default:
	                return "";
	        }
		
	}
}
