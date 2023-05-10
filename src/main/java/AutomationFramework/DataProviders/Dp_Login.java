package AutomationFramework.DataProviders;

import AutomationFramework.Commons.ExcelRW;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class Dp_Login {
    @DataProvider(name="invalidLogin")
    public static Object[][] getInvalidLoginData() throws IOException {

        return getDataByScript("data","invalid_login");

// 2nd way
// Object[][] arr = new Object[4][4];
// arr[0][0]="GB_01";
// arr[0][1]="invalid_login";
// arr[0][2]="sdfgdsgfsd";
// arr[0][3]="sdfgsgf";
// arr[1][0]="GB_02";
// arr[1][1]="invalid_login";
// arr[1][2]="";
// arr[1][3]="sdfgsgf";
// arr[2][0]="GB_03";
// arr[2][1]="invalid_login";
// arr[2][2]="sdfgdsgfsd";
// arr[2][3]="";
// arr[3][0]="GB_04";
// arr[3][1]="invalid_login";
// arr[3][2]="";
// arr[3][3]="";
// return arr;
//
//3rd way
// return new Object[][]{ { "GB_01","invalid_login","askdkasd","sajhdfsah"},
// {"GB_02","invalid_login","","sajhdfsah"},{"GB_03","invalid_login","",""} };


    }


    @DataProvider(name="validLogin")
    public static Object[][] getValidLoginData() throws IOException {

        return getDataByScript("data","valid_login");
    }


    public static  Object[][] getDataByScript(String Sheetname, String ScriptName) throws IOException {

        ExcelRW excelRW = new ExcelRW(System.getProperty("user.dir") + "\\src\\main\\resources\\Book1.xlsx");
        int irows = excelRW.getRowCount(Sheetname);
        int icols=excelRW.getColumnCount(Sheetname);

        int count=0;
// find the number of rows required
        for(int ireq_row=1;ireq_row<=irows;ireq_row++){
            if (excelRW.readCellValue(Sheetname,ireq_row,1).equals(ScriptName)){
                count++;
            }
        }

        Object[][] objarr = new Object[count][icols];

        int rowindex=0;

// iterate through each row
        for(int irow=1;irow<=irows;irow++){

            if (excelRW.readCellValue(Sheetname,irow,1).equals(ScriptName)){
// iterate through each column
                for(int icol=0;icol<icols;icol++){

                    objarr[rowindex][icol]=excelRW.readCellValue(Sheetname,irow,icol);
                }
                rowindex++;
            }
        }
        return objarr;
    }


}
