package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class getData {

	public static ArrayList<String> getDataExcel(String testSheetName, String testCaseName) throws IOException {
		ArrayList<String> arrayData = new ArrayList<String>();
		// step 1 - Open the excel file, by creating the object of FileInputStream
		String TestDataPath = System.getProperty("user.dir");
		System.out.println(TestDataPath);
		FileInputStream fis = new FileInputStream(TestDataPath + "/testData.xlsx");

		// Step 2 - Create the object of XSSFWorkbook to open the excel file

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// Step 3 - Access the desired sheet
		// Step 3.1 - Fetch the count of sheets available in the excel file

		int countOfSheet = workbook.getNumberOfSheets();

		// Step 3.2 - Fetch the name of sheet and compare against desired sheet name

		for (int i = 0; i < countOfSheet; i++) {
			String sheetName = workbook.getSheetName(i);
			if (sheetName.equalsIgnoreCase(testSheetName)) {
				// excess the sheet and iterate through rows to fetch the column in which test
				// case name column is found
				XSSFSheet Sheet = workbook.getSheetAt(i);
				// Create iterator for rows
				Iterator<Row> Rows = Sheet.iterator();
				Row firstRow = Rows.next();

				// Step no 4.2 Create iterator for cells
				Iterator<Cell> Cells = firstRow.cellIterator();
				int j = 0;
				int tc_column = 0;
				// Step no 4.3 read the cell values of row number 1 to compare against the
				// testcase name

				while (Cells.hasNext()) {
					Cell cellValue = Cells.next();
					if (cellValue.getStringCellValue().equalsIgnoreCase("tc_name")) {
						tc_column = j;
					}
					j++;
				}
				// Step no 5 fetch the data for designated test case
				while (Rows.hasNext()) {
					Row dataRow = Rows.next();
					if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> dataCellValue = dataRow.cellIterator();
						while (dataCellValue.hasNext()) {
							Cell dataOfCell = dataCellValue.next();
							// Method 1 to extract cell value by using try and catch
							try {
								String testData = dataOfCell.getStringCellValue();
								arrayData.add(testData);
							} catch (IllegalStateException e) {
								int intTestData = (int) dataOfCell.getNumericCellValue();
								String stringtestData = Integer.toString(intTestData);
								arrayData.add(stringtestData);
							}
							//Method 2 To extract the cell value by using if and else
//								CellType datatype = dataOfCell.getCellType();
//							
//							 if (datatype.toString() == "NUMERIC")
//							{
//								int intTestData = (int) dataOfCell.getNumericCellValue();
//								String stringtestData = Integer.toString(intTestData);
//								arrayData.add(stringtestData);
//							}
//							else if (datatype.toString() == "STRING")
//							{
//								String testData = dataOfCell.getStringCellValue();
//								arrayData.add(testData);
//							}
//							 // Method 3 Extract the data by converting it into String
//							 
//							 String testData = dataCellValue.next().toString().replaceAll("\\.\\d+$","");
//							 System.out.println(testData);
//							 
//							 // Method 4 Extract the data by using dataFormatter 
//							 
//							 DataFormatter format = new DataFormatter();
//							 String testData = format.formatCellValue(dataCellValue.next());
//							 System.out.println(testData);
						}

					}
				}
			}
		}

		return arrayData;
	}

}
