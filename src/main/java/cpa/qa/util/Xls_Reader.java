//package cpa.qa.util;
//
//import java.io.Closeable;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeSet;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.hssf.util.Region;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DateUtil;
//import org.apache.poi.ss.usermodel.Hyperlink;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
//import org.apache.poi.xssf.usermodel.XSSFHyperlink;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;
//
//public class Xls_Reader {
////	public static String filename = System.getProperty("user.dir")+"\\src\\main\\java\\cpa\\qa\\xls\\Login.xlsx";
//	public  String path;
//	public  FileInputStream fis= null;
//	public  FileOutputStream fileOut= null;
//	public XSSFWorkbook workbook= null;
//	public XSSFSheet sheet= null;
//	private XSSFRow row= null;
//	public XSSFWorkbook wbl;
//	private XSSFCell cell= null;
//	public XSSFHyperlink lnk=null;
//	public Xls_Reader(String path) {
//		this.path=path;
//		try {
//
//
//			fis = new FileInputStream(path);
//
//			workbook = new XSSFWorkbook(fis);
//
//
//
//			XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
//
//			sheet = workbook.getSheetAt(0);
//
//
//		}
//
//		catch (XmlValueDisconnectedException e)
//		{
//			System.out.println("XmlValueDisconnectedException Exception occurred.");
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//
//		}
//
//	}
//
//
//
//	// addHyperLink in the sheet
//		public boolean setHyperLink(String sheetName,String testName,String colName,int rowNum,XSSFHyperlink hl){
//			try{
//				fis = new FileInputStream(path);
//				workbook = new XSSFWorkbook(fis);
//
//				if(rowNum<=0)
//					return false;
//
//				int index = workbook.getSheetIndex(sheetName);
//				int colNum=-1;
//				if(index==-1)
//					return false;
//
//				sheet = workbook.getSheetAt(index);
//				row=sheet.getRow(0);
//
//				for(int i=0;i<row.getLastCellNum();i++){
//					//System.out.println(row.getCell(i).getStringCellValue().trim());
//					if(row.getCell(i).getStringCellValue().trim().equals(colName))
//						colNum=i;
//				}
//				if(colNum==-1)
//					return false;
//
//				sheet.autoSizeColumn(colNum);
//				row = sheet.getRow(rowNum-1);
//				if (row == null)
//					row = sheet.createRow(rowNum-1);
//
//				cell = row.getCell(colNum);
//				if (cell == null)
//			        cell = row.createCell(colNum);
//
//			    // cell style
//			    CellStyle cs = workbook.createCellStyle();
//			    cs.setWrapText(true);
//			    cell.setCellStyle(cs);
//			    XSSFCreationHelper helper= workbook.getCreationHelper();
//				hl=	helper.createHyperlink(Hyperlink.LINK_DOCUMENT);
//				hl.setAddress("'"+testName+"'!A1");
//				hl.setTooltip("Click on the sheet name to review the execution steps !");
//				cell.setHyperlink(hl);
////			    cell.setCellValue(data);
//			    fileOut = new FileOutputStream(path);
//				workbook.write(fileOut);
//			//	workbook = new XSSFWorkbook(new FileInputStream("workbook.xlsx"));
//			    fileOut.close();
//
//				}
//				catch(Exception e){
//					e.printStackTrace();
//					return false;
//				}
//				return true;
//				}
//
//
//
//	// returns the row count in a sheet
//	public int getRowCount(String sheetName){
//		int index = workbook.getSheetIndex(sheetName);
//		if(index==-1)
//			return 0;
//		else{
//		sheet = workbook.getSheetAt(index);
//		int number=sheet.getLastRowNum()+1;
//		return number;
//		}
//
//	}
//
//	// returns the data from a cell
//	public String getCellData(String sheetName,String colName,int rowNum){
//		try
//		{
//
//			if(rowNum <=0)
//				return "";
//
//			int index = workbook.getSheetIndex(sheetName);
//			int col_Num=-1;
//			if(index==-1)
//				return "";
//
//			sheet = workbook.getSheetAt(index);
//			row=sheet.getRow(0);
//			for(int i=0;i<row.getLastCellNum();i++)
//			{
//				//System.out.println(row.getCell(i).getStringCellValue().trim());
//				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
//					col_Num=i;
//			}
//			if(col_Num==-1)
//				return "";
//
//			sheet = workbook.getSheetAt(index);
//			row = sheet.getRow(rowNum-1);
//			if(row==null)
//				return "";
//			cell = row.getCell(col_Num);
//
//			if(cell==null)
//				return "";
//			//System.out.println(cell.getCellType());
//
//			String cellText ="";
//
//
//			try
//			{
//				switch(cell.getCellType())
//				{
//						case Cell.CELL_TYPE_STRING:
//							cellText= cell.getStringCellValue();
//							break;
//
//						case Cell.CELL_TYPE_NUMERIC:
//							Date javaDate = DateUtil.getJavaDate((double)cell.getNumericCellValue());
//							cellText = new SimpleDateFormat("dd MMM yyyy").format(javaDate);
//							break;
//
//						case Cell.CELL_TYPE_FORMULA:
//							if(cell.getRawValue().toString().contains("PPR")){
//													cellText=cell.getRawValue().toString();
//										}
//							else if(cell.getRawValue().toString().contains(" "))
//							{
//								cellText=cell.getRawValue();
//							}
//							else if(cell.getRawValue().toString().contains("/"))
//							{
//								cellText=cell.getRawValue();
//							}
//							else 		{
//								cellText=cell.getRawValue();
//
//								//Commented to handle numeric value and reference of other cell having value
//
//Date jDate = DateUtil.getJavaDate((double)cell.getNumericCellValue());
//								cellText = new SimpleDateFormat("dd MMM yyyy").format(jDate);
//
//							}
//							break;
//
//						case Cell.CELL_TYPE_BLANK:
//							break;
//
//				}
//			}
//			catch(Exception e)
//			{
//				System.out.println(cell.getCellType());
//				System.out.println("Exception handled");
//			}
//		return cellText;
//
//		}
//
////		blocked below code to handle dd mmm yyyy format i.e 16 Sep 2016 in above code
//		  String cellText  = String.valueOf(cell.getNumericCellValue());
//			  if (HSSFDateUtil.isCellDateFormatted(cell))
//			{
//		           // format in form of M/D/YY
//				  double d = cell.getNumericCellValue();
//				  Calendar cal =Calendar.getInstance();
//				  cal.setTime(HSSFDateUtil.getJavaDate(d));
//		            cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
//		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH)+1 + "/" + cellText;
//
//		           System.out.println("Check this value ->"+cellText);
//
//		    }
//
//
//
//			  return cellText;
//		  }
//		else
//
//
//
//			if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
//		      return "";
//
//		  else
//			  return String.valueOf(cell.getBooleanCellValue());
//		}
//
//
//		catch(Exception e)
//		{
//
//			e.printStackTrace();
//			return "row "+rowNum+" or column "+colName +" does not exist in xls";
//		}
//	}
//
//	// returns the data from a cell
//	public String getCellData(String sheetName,int colNum,int rowNum){
//		try{
//			if(rowNum <=0)
//				return "";
//
//		int index = workbook.getSheetIndex(sheetName);
//
//		if(index==-1)
//			return "";
//
//
//		sheet = workbook.getSheetAt(index);
//		row = sheet.getRow(rowNum-1);
//		if(row==null)
//			return "";
//		cell = row.getCell(colNum);
//		if(cell==null)
//			return "";
//
//	  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
//		  return cell.getStringCellValue();
//	  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
//
//		  String cellText  = String.valueOf(cell.getNumericCellValue());
//		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
//	           // format in form of M/D/YY
//			  double d = cell.getNumericCellValue();
//
//			  Calendar cal =Calendar.getInstance();
//			  cal.setTime(HSSFDateUtil.getJavaDate(d));
//	            cellText =
//	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
//	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
//	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
//	                      cellText;
//
//	          // System.out.println(cellText);
//
//	         }
//
//
//
//		  return cellText;
//	  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
//	      return "";
//	  else
//		  return String.valueOf(cell.getBooleanCellValue());
//		}
//		catch(Exception e){
//
//			e.printStackTrace();
//			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
//		}
//	}
//
//	// returns true if string data is set successfully else false
//	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
//		try{
//		fis = new FileInputStream(path);
//		workbook = new XSSFWorkbook(fis);
//
//		if(rowNum<=0)
//			return false;
//
//		int index = workbook.getSheetIndex(sheetName);
//		int colNum=-1;
//		if(index==-1)
//			return false;
//
//
//		sheet = workbook.getSheetAt(index);
//
//
//		row=sheet.getRow(0);
//
//		for(int i=0;i<row.getLastCellNum();i++){
//			//System.out.println(row.getCell(i).getStringCellValue().trim());
//			if(row.getCell(i).getStringCellValue().trim().equals(colName))
//				colNum=i;
//		}
//		if(colNum==-1)
//			return false;
//
//		sheet.autoSizeColumn(colNum);
//		row = sheet.getRow(rowNum-1);
//		if (row == null)
//			row = sheet.createRow(rowNum-1);
//
//		cell = row.getCell(colNum);
//		if (cell == null)
//	        cell = row.createCell(colNum);
//
//	    // cell style
//	    CellStyle cs = workbook.createCellStyle();
//	    cs.setWrapText(true);
//	    cell.setCellStyle(cs);
//	    cell.setCellValue(data);
//
//	    fileOut = new FileOutputStream(path);
//
//		workbook.write(fileOut);
//	//	workbook = new XSSFWorkbook(new FileInputStream("workbook.xlsx"));
//
//	    fileOut.close();
//
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//
//
//	// returns true if data is int set successfully else false
//		public boolean setCellData(String sheetName,String colName,int rowNum, int data){
//			try{
//			fis = new FileInputStream(path);
//			workbook = new XSSFWorkbook(fis);
//
//			if(rowNum<=0)
//				return false;
//
//			int index = workbook.getSheetIndex(sheetName);
//			int colNum=-1;
//			if(index==-1)
//				return false;
//
//
//			sheet = workbook.getSheetAt(index);
//
//
//			row=sheet.getRow(0);
//
//			for(int i=0;i<row.getLastCellNum();i++){
//				//System.out.println(row.getCell(i).getStringCellValue().trim());
//				if(row.getCell(i).getStringCellValue().trim().equals(colName))
//					colNum=i;
//			}
//			if(colNum==-1)
//				return false;
//
//			sheet.autoSizeColumn(colNum);
//			row = sheet.getRow(rowNum-1);
//			if (row == null)
//				row = sheet.createRow(rowNum-1);
//
//			cell = row.getCell(colNum);
//			if (cell == null)
//		        cell = row.createCell(colNum);
//
//		    // cell style
//		    CellStyle cs = workbook.createCellStyle();
//		    cs.setWrapText(true);
//		    cell.setCellStyle(cs);
//		    cell.setCellValue(data);
//
//		    fileOut = new FileOutputStream(path);
//
//			workbook.write(fileOut);
//		//	workbook = new XSSFWorkbook(new FileInputStream("workbook.xlsx"));
//
//		    fileOut.close();
//
//			}
//			catch(Exception e){
//				e.printStackTrace();
//				return false;
//			}
//			return true;
//		}
//
//
//
//
//	// returns true if data is set successfully else false
//	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
//		//System.out.println("setCellData setCellData******************");
//		try{
//		fis = new FileInputStream(path);
//		workbook = new XSSFWorkbook(fis);
//
//		if(rowNum<=0)
//			return false;
//
//		int index = workbook.getSheetIndex(sheetName);
//		int colNum=-1;
//		if(index==-1)
//			return false;
//
//
//		sheet = workbook.getSheetAt(index);
//		//System.out.println("A");
//		row=sheet.getRow(0);
//		for(int i=0;i<row.getLastCellNum();i++){
//			//System.out.println(row.getCell(i).getStringCellValue().trim());
//			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
//				colNum=i;
//		}
//
//		if(colNum==-1)
//			return false;
//		sheet.autoSizeColumn(colNum); //ashish
//		row = sheet.getRow(rowNum-1);
//		if (row == null)
//			row = sheet.createRow(rowNum-1);
//
//		cell = row.getCell(colNum);
//		if (cell == null)
//	        cell = row.createCell(colNum);
//
//	    cell.setCellValue(data);
//	    XSSFCreationHelper createHelper = workbook.getCreationHelper();
//
//	    //cell style for hyperlinks
//	    //by default hypelrinks are blue and underlined
//	    CellStyle hlink_style = workbook.createCellStyle();
//	    XSSFFont hlink_font = workbook.createFont();
//	    hlink_font.setUnderline(XSSFFont.U_SINGLE);
//	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
//	    hlink_style.setFont(hlink_font);
//	    //hlink_style.setWrapText(true);
//
//	    XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
//	    link.setAddress(url);
//	    cell.setHyperlink(link);
//	    cell.setCellStyle(hlink_style);
//
//	    fileOut = new FileOutputStream(path);
//		workbook.write(fileOut);
//
//	    fileOut.close();
//
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//
//
//	// returns true if sheet is created successfully else false
//	public boolean addSheet(String sheetname){
//
//
//		try {
//
//			 workbook.createSheet(sheetname);
//
//			 fileOut = new FileOutputStream(path);
//			 workbook.write(fileOut);
//
//			 fileOut.flush();
//		     fileOut.close();
//		     //   workbook = new XSSFWorkbook(new FileInputStream(path));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//
//	// returns true if sheet is renamed successfully else false
//	public boolean RenameSheet(String sheetname,String newname){
//
//			int index = workbook.getSheetIndex(sheetname);
//			if(index==-1)
//				return false;
//
//			FileOutputStream fileOut;
//			try {
//				workbook.setSheetName(workbook.getSheetIndex(sheetname), newname);
//				 System.out.println("name changed");
//				fileOut = new FileOutputStream(path);
//				workbook.write(fileOut);
//			    fileOut.close();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//			return true;
//		}
//
//
//	// returns true if sheet is renamed successfully else false
//		public boolean CopySheet(String from,String to){
//
//				int index = workbook.getSheetIndex(from);
//				if(index==-1)
//					return false;
//
//				FileOutputStream fileOut;
//				try {
//
//					 System.out.println("name changed");
//					fileOut = new FileOutputStream(path);
//					workbook.write(fileOut);
//				    fileOut.close();
//
//				} catch (Exception e) {
//					e.printStackTrace();
//					return false;
//				}
//				return true;
//			}
//
//	// returns true if sheet is created successfully else false
//	public String Clone(String sheetname){
//		String clone;
//		try {
//			 int index= workbook.getSheetIndex(sheetname);
//			  clone=workbook.cloneSheet(index).getSheetName();
//			 int idx= workbook.getSheetIndex(clone);
//			   fileOut = new FileOutputStream(path);
//			   workbook.write(fileOut);
//			 System.out.println("Clone name is" +clone +"and index is "+idx);
//
//		 } catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("COntrol comes here in rename sheet function");
//			return null;
//		}
//		return clone;
//	}
//
//
//	// returns true if sheet is created successfully else false
//		public boolean AddSheetForBrowser(String sheetname,String BrowserName){
//
//
//			try {
//
//				int num= workbook.getSheetIndex(sheetname);
//				 System.out.println("index  is "+num);
//				 workbook.setSheetName(num, sheetname+"_"+BrowserName);
//				 fileOut = new FileOutputStream(path);
//				 workbook.write(fileOut);
//
//				 fileOut.flush();
//			     fileOut.close();
//		//	        workbook = new XSSFWorkbook(new FileInputStream(path));
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//			return true;
//		}
//
//
//	private FileInputStream XSSFWorkbook(Closeable closeable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	// returns true if sheet is removed successfully else false if sheet does not exist
//	public boolean removeSheet(String sheetName){
//		int index = workbook.getSheetIndex(sheetName);
//		if(index==-1)
//			return false;
//
//		FileOutputStream fileOut;
//		try {
//			workbook.removeSheetAt(index);
//			fileOut = new FileOutputStream(path);
//			workbook.write(fileOut);
//		    fileOut.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
//
//	// returns true if column is created successfully
//	public boolean addColumn(String sheetName,String colName){
//		//System.out.println("**************addColumn*********************");
//
//		try{
//			fis = new FileInputStream(path);
//			workbook = new XSSFWorkbook(fis);
//			int index = workbook.getSheetIndex(sheetName);
//			if(index==-1)
//				return false;
//
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//		sheet=workbook.getSheetAt(index);
//
//		row = sheet.getRow(0);
//		if (row == null)
//			row = sheet.createRow(0);
//
//		//cell = row.getCell();
//		//if (cell == null)
//		//System.out.println(row.getLastCellNum());
//		if(row.getLastCellNum() == -1)
//			cell = row.createCell(0);
//		else
//			cell = row.createCell(row.getLastCellNum());
//
//	        cell.setCellValue(colName);
//	        cell.setCellStyle(style);
//
//	        fileOut = new FileOutputStream(path);
//			workbook.write(fileOut);
//		    fileOut.close();
//
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//
//		return true;
//
//
//	}
//	// removes a column and all the contents
//	public boolean removeColumn(String sheetName, int colNum) {
//		try{
//		if(!isSheetExist(sheetName))
//			return false;
//		fis = new FileInputStream(path);
//		workbook = new XSSFWorkbook(fis);
//		sheet=workbook.getSheet(sheetName);
//		XSSFCellStyle style = workbook.createCellStyle();
//		style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//		XSSFCreationHelper createHelper = workbook.getCreationHelper();
//		style.setFillPattern(HSSFCellStyle.NO_FILL);
//
//
//
//		for(int i =0;i<getRowCount(sheetName);i++){
//			row=sheet.getRow(i);
//			if(row!=null){
//				cell=row.getCell(colNum);
//				if(cell!=null){
//					cell.setCellStyle(style);
//					row.removeCell(cell);
//				}
//			}
//		}
//		fileOut = new FileOutputStream(path);
//		workbook.write(fileOut);
//	    fileOut.close();
//
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//
//	}
//  // find whether sheets exists
//	public boolean isSheetExist(String sheetName){
//		int index = workbook.getSheetIndex(sheetName);
//		if(index==-1){
//			index=workbook.getSheetIndex(sheetName.toUpperCase());
//				if(index==-1)
//					return false;
//				else
//					return true;
//		}
//		else
//			return true;
//	}
//
//	// returns number of columns in a sheet
//	public int getColumnCount(String sheetName){
//		// check if sheet exists
//		if(!isSheetExist(sheetName))
//		 return -1;
//
//		sheet = workbook.getSheet(sheetName);
//		row = sheet.getRow(0);
//
//		if(row==null)
//			return -1;
//
//		return row.getLastCellNum();
//
//	}
//
//
//
//	//String sheetName, String testCaseName,String keyword ,String URL,String message
//	public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){
//		//System.out.println("ADDING addHyperLink******************");
//
//		url=url.replace('\\', '/');
//		if(!isSheetExist(sheetName))
//			 return false;
//
//	    sheet = workbook.getSheet(sheetName);
//
//
//	    for(int i=2;i<=getRowCount(sheetName);i++){
//	    	if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
//	    		//System.out.println("**caught "+(i+index));
//	    		setCellData(sheetName, screenShotColName, i+index, message,url);
//	    		break;
//	    	}
//	    }
//
//
//		return true;
//	}
//
//	public int getCellRowNum(String sheetName,String colName,String cellValue){
//
//		for(int i=2;i<=getRowCount(sheetName);i++){
//	    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
//	    		return i;
//	    	}
//	    }
//		return -1;
//	}
//
//
//
//
//
//
//	 public void  CreateExcel(String Filename,String Sheetname) throws IOException{
//	   String	path=System.getProperty("user.dir")+"\\src\\CreateExcel.vbs";
//		System.out.println("User Directory:"+System.getProperty("user.dir"));
//         Runtime.getRuntime().exec("WScript path" + "\"" + Filename + "\"" + " " + "\""  + Sheetname + "\"" );
//                           }
//
//
//
//
//
//	    public static void copySheets(HSSFSheet newSheet, HSSFSheet sheet){
//	        copySheets(newSheet, sheet, true);
//	    }
//	    public static void copySheets(HSSFSheet newSheet, HSSFSheet sheet, boolean copyStyle){
//	        int maxColumnNum = 0;
//	        Map<Integer, HSSFCellStyle> styleMap = (copyStyle)
//	                ? new HashMap<Integer, HSSFCellStyle>() : null;
//
//	        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
//	            HSSFRow srcRow = sheet.getRow(i);
//	            HSSFRow destRow = newSheet.createRow(i);
//	            if (srcRow != null) {
//	                Xls_Reader.copyRow(sheet, newSheet, srcRow, destRow, styleMap);
//	                if (srcRow.getLastCellNum() > maxColumnNum) {
//	                    maxColumnNum = srcRow.getLastCellNum();
//	                }
//	            }
//	        }
//	        for (int i = 0; i <= maxColumnNum; i++) {
//	            newSheet.setColumnWidth(i, sheet.getColumnWidth(i));
//	        }
//	    }
//
//	    public static void copyRow(HSSFSheet srcSheet, HSSFSheet destSheet, HSSFRow srcRow, HSSFRow destRow, Map<Integer, HSSFCellStyle> styleMap) {
//	        TreeSet mergedRegions = new TreeSet();
//	        destRow.setHeight(srcRow.getHeight());
//	        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
//	            HSSFCell oldCell = srcRow.getCell(j);
//	            HSSFCell newCell = destRow.getCell(j);
//	            if (oldCell != null) {
//	                if (newCell == null) {
//	                    newCell = destRow.createCell(j);
//	                }
//	                copyCell(oldCell, newCell, styleMap);
//	                Region mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(), oldCell.getCellNum());
//	                if (mergedRegion != null) {
////	                    Region newMergedRegion = new Region( destRow.getRowNum(), mergedRegion.getColumnFrom(),
////	                            destRow.getRowNum() + mergedRegion.getRowTo() - mergedRegion.getRowFrom(), mergedRegion.getColumnTo() );
//	                    Region newMergedRegion = new Region(mergedRegion.getRowFrom(), mergedRegion.getColumnFrom(),
//	                            mergedRegion.getRowTo(), mergedRegion.getColumnTo());
//	                    if (isNewMergedRegion(newMergedRegion, (Collection) mergedRegions)) {
//	                        ((Collection) mergedRegions).add(newMergedRegion);
//	                        destSheet.addMergedRegion(newMergedRegion);
//	                    }
//	                }
//	            }
//	        }
//
//	    }
//	    public static void copyCell(HSSFCell oldCell, HSSFCell newCell, Map<Integer, HSSFCellStyle> styleMap) {
//	        if(styleMap != null) {
//	            if(oldCell.getSheet().getWorkbook() == newCell.getSheet().getWorkbook()){
//	                newCell.setCellStyle(oldCell.getCellStyle());
//	            } else{
//	                int stHashCode = oldCell.getCellStyle().hashCode();
//	                HSSFCellStyle newCellStyle = styleMap.get(stHashCode);
//	                if(newCellStyle == null){
//	                    newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();
//	                    newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
//	                    styleMap.put(stHashCode, newCellStyle);
//	                }
//	                newCell.setCellStyle(newCellStyle);
//	            }
//	        }
//	        switch(oldCell.getCellType()) {
//	            case HSSFCell.CELL_TYPE_STRING:
//	                newCell.setCellValue(oldCell.getStringCellValue());
//	                break;
//	            case HSSFCell.CELL_TYPE_NUMERIC:
//	                newCell.setCellValue(oldCell.getNumericCellValue());
//	                break;
//	            case HSSFCell.CELL_TYPE_BLANK:
//	                newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
//	                break;
//	            case HSSFCell.CELL_TYPE_BOOLEAN:
//	                newCell.setCellValue(oldCell.getBooleanCellValue());
//	                break;
//	            case HSSFCell.CELL_TYPE_ERROR:
//	                newCell.setCellErrorValue(oldCell.getErrorCellValue());
//	                break;
//	            case HSSFCell.CELL_TYPE_FORMULA:
//	                newCell.setCellFormula(oldCell.getCellFormula());
//	                break;
//	            default:
//	                break;
//	        }
//
//	    }
//	    public static Region getMergedRegion(HSSFSheet sheet, int rowNum, short cellNum) {
//	        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
//	            Region merged = sheet.getMergedRegionAt(i);
//	            if (merged.contains(rowNum, cellNum)) {
//	                return merged;
//	            }
//	        }
//	        return null;
//	    }
//
//	    private static boolean isNewMergedRegion(Region region, Collection mergedRegions) {
//	        return !mergedRegions.contains(region);
//	    }
//
//
//
//	// to run this on stand alone
//	public static void main(String arg[]) throws IOException{
//
//		//System.out.println(filename);
//	//	Xls_Reader datatable = null;
//
//
//		//	 datatable = new Xls_Reader("C:\\Users\\prathore\\workspace\\Discover\\src\\main\\java\\cpa\\qa\\config\\xls\\Test Cases.xlsx");
//			//	for(int col=0 ;col< datatable.getColumnCount("TC2"); col++){
//				//	System.out.println(datatable.getCellData("TC2", col, 1));
//			//	}
//	}
//
//
//}
