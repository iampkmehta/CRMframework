package testscript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ExecuteLeadTest 
{
	static Keywords keyword;
	
	@Rule
	public ErrorCollector errCol = new ErrorCollector();
	
	@Test
	public void leadTest() throws IOException 
	{
		keyword = new Keywords();
		ArrayList data = new ArrayList();
		FileInputStream file = new FileInputStream("F:\\Project\\java project\\CRMframework\\src\\testcase\\LeadSuites.xlsx");
		XSSFWorkbook wbk = new XSSFWorkbook(file);
		Sheet sheet = wbk.getSheet("TestSteps");
		Iterator itr = sheet.iterator();
		while(itr.hasNext())
		{
			Row rowitr = (Row)itr.next();
			Iterator cellitr = rowitr.cellIterator();
			while(cellitr.hasNext())
			{
				Cell celldata = (Cell)cellitr.next();
				switch(celldata.getCellType())
				{
				case STRING:
					data.add(celldata.getStringCellValue());
					break;
				case NUMERIC:
					data.add(celldata.getNumericCellValue());
				case BOOLEAN:
					data.add(celldata.getBooleanCellValue());
				}
			}
		}
		
		//filtring excel data from ArrayList data
		for(int i = 0;i<data.size(); i++)
		{
			if(data.get(i).equals("openbrowser"))
			{
				if(data.get(i+3).equals("yes"))
				{
					keyword.openbrowser();
				}
			}
			//navigating url
			if(data.get(i).equals("navigate"))
			{
				if(data.get(i+3).equals("yes"))
				{
					keyword.navigate((String)data.get(i+1));
				}
			}
			
			if(data.get(i).equals("input"))
			{
				if(data.get(i+3).equals("yes"))
				{
					String testData = (String)data.get(i+1);
					String objectName = (String)data.get(i+2);
					keyword.input(testData, objectName);
				}
			}
			
			if(data.get(i).equals("click"))
			{
				if(data.get(i+3).equals("yes"))
				{
					String objectName = (String)data.get(i+2);
					keyword.click(objectName);
				}
			}
			
			if(data.get(i).equals("verifyTitle"))
			{
				if(data.get(i+3).equals("yes"))
				{
					String expectedValue = (String)data.get(i+1);
					String actualValue = keyword.verifyTitle();
					try{
						Assert.assertEquals(expectedValue, actualValue);
					}catch(Throwable t){
						errCol.addError(t);
					}
				}
			}
		}
	}
}