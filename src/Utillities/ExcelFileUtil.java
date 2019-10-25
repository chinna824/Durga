
package Utillities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	//to read excel path
	public ExcelFileUtil()throws Throwable
	{
		FileInputStream fis=new FileInputStream("D:\\mrng930batch\\ERP_Stock\\TestInput\\supplier.xlsx");
		wb=WorkbookFactory.create(fis);
	}
   //count no of rows in sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
		
	}
	//count no of columns in row
	public int colCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
		
	}
	//get cell data from sheet
	public String getData(String sheetname,int row,int column)
	{
		String data="";
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			//converting celldata numeric column into string
			data=String.valueOf(celldata);
		}
		else{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	//to write into workbook
	public void setCellData(String sheetname,int row,int column,String status)throws Throwable
	{
		//get sheet from wb
		Sheet ws=wb.getSheet(sheetname);
		//get row from sheet
		Row rownum=ws.getRow(row);
		//create cell in row
		Cell cell=rownum.createCell(column);
		//write status into cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			//create a cell style
			CellStyle style=wb.createCellStyle();
			//create a font
			Font font=wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.GREEN.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			//create a cell style
			CellStyle style=wb.createCellStyle();
			//create a font
			Font font=wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.RED.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
			
		}
		else if(status.equalsIgnoreCase("Not Executed"))
		{
			//create a cell style
			CellStyle style=wb.createCellStyle();
			//create a font
			Font font=wb.createFont();
			//apply colour to the text
			font.setColor(IndexedColors.BLUE.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);
			
		}
		FileOutputStream fos=new FileOutputStream("D:\\mrng930batch\\ERP_Stock\\TestInput\\supplier.xlsx");
		wb.write(fos);
		wb.close();
		fos.close();
		
		
	}
}
