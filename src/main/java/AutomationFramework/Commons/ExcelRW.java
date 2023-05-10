package AutomationFramework.Commons;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelRW {
    XSSFWorkbook wb;
    FileInputStream fis;

    public ExcelRW(String xlPath) throws IOException {
// Open the Excel
        fis= new FileInputStream(xlPath);
// // connect the sheet
        wb = new XSSFWorkbook(fis);
    }


    // create a sheet object
    public XSSFSheet getSheet(String sheetName){
        XSSFSheet sheet1 = wb.getSheet(sheetName);
        return sheet1;
    }

    // Rowcount
    public int getRowCount(String sheetName){
// XSSFSheet sheet1 = wb.getSheet(sheetName);
// return sheet1.getLastRowNum();
        return getSheet(sheetName).getLastRowNum();
    }

    // Column count
    public int getColumnCount(String sheetName){
        return getSheet(sheetName).getRow(1).getLastCellNum();
    }

    // Read Operation
    public String readCellValue(String sheetName, int row,int column){
// reading from a cell
        XSSFCell cell = getSheet(sheetName).getRow(row).getCell(column);
        String cellValue="";
        if(cell.getCellType()== CellType.STRING){
            cellValue = cell.getStringCellValue();

        }else if(cell.getCellType()== CellType.NUMERIC){
            cellValue = String.valueOf(cell.getNumericCellValue());
        }else if(cell.getCellType()==CellType.BLANK){
            cellValue="";
        }
        return cellValue;
    }

    // write opertion
    public void writeCellValue(String sheetName, int row,int column,String value){
        getSheet(sheetName).getRow(row).getCell(column).setCellValue(value);
    }

    // Save & close
    public void saveAndCloseWorkbook(String xlPath) throws IOException {
// Save the excel
        FileOutputStream fos = new FileOutputStream(xlPath);
        wb.write(fos);
// Close the excel
        fis.close();
        fos.close();
    }


}

