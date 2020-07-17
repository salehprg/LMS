package Helper;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.UserModel;  
public class ExcelFile  
{  
    public static ArrayList<UserModel> ReadStudentData(String fileUrl) throws IOException  
    {  
        try
        {
            ArrayList<UserModel> users = new ArrayList<>();
            //obtaining input bytes from a file  
            FileInputStream fis=new FileInputStream(new File(fileUrl));  
            //creating workbook instance that refers to .xls file  
            XSSFWorkbook wb=new XSSFWorkbook(fis);   
            //creating a Sheet object to retrieve the object  
            XSSFSheet sheet=wb.getSheetAt(0);  
            
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
            itr.next();

            while (itr.hasNext())                 
            {  
                Row row = itr.next();  
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
                UserModel userModel = new UserModel();

                while (cellIterator.hasNext())   
                {  
                    Cell cell = cellIterator.next();  
                    switch(cell.getColumnIndex())
                    {
                        case 0:
                            userModel.IdNumber = String.valueOf(cell.getNumericCellValue());
                            break;

                        case 1:
                            userModel.FirstName = cell.getStringCellValue();
                            break;

                        case 2:
                            userModel.LastName = cell.getStringCellValue();
                            break;
                    }
    
                }  

                users.add(userModel);
                System.out.println("");  
            }  

            return users;
        } 
        catch(Exception e)  
        {  
            e.printStackTrace();  
            return null;
        }  
    }  
}  