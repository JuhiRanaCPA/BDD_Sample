//package cpa.qa.util;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.Format;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Hashtable;
//import java.util.Random;
//
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFColor;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//import org.apache.poi.xssf.usermodel.XSSFHyperlink;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//
//public class Reporting{
//	Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	public static XSSFHyperlink l;
//
//
//	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	static Random rnd = new Random();
//
//
//	  FileInputStream fileInputStream = null;
//	  String browser;
//
//		public XSSFWorkbook workbook= null;
//		public  FileInputStream fis= null;
//
//
//	  public void GenerateReport(String filename,String Sheetname,Hashtable<String,String> testData) throws IOException {
//		   //Create blank workbook
//
//	      //Create file system using specific name
//
//		   Xls_Reader rw = new Xls_Reader(filename);
//		   browser = testData.get("Browser");
//
//
//		  if (rw.isSheetExist(Sheetname) ){
//			   // Clear all cells
//				  for ( int Index =0 ; Index <= rw.getColumnCount(Sheetname); Index ++ )
//				  {
//					  {
//
//
//					       rw.removeColumn(Sheetname, Index);}}
//
//						   rw.addColumn(Sheetname, "Test Suite");
//						   rw.addColumn(Sheetname, "Test Case");
//						   rw.addColumn(Sheetname, "Keyword");
//						   rw.addColumn(Sheetname, "Arg1");
//						   rw.addColumn(Sheetname, "Arg2");
//						   rw.addColumn(Sheetname, "Arg3");
//						   rw.addColumn(Sheetname,  "Arg4");
//						   rw.addColumn(Sheetname,  "Arg5");
//						   rw.addColumn(Sheetname,  "Arg6");
//						   rw.addColumn(Sheetname,  "Arg7");
//						   rw.addColumn(Sheetname, "Status");
//						   rw.addColumn(Sheetname, "Execution date and time");
//						   rw.addColumn(Sheetname, "Description");
//						   rw.addColumn(Sheetname, "Back to Consolidated");
//						   rw.setHyperLink(Sheetname,"Consolidated", "Back to Consolidated", 2,l);
//
//
//
//				  }
//
//		   else
//						   {
//						   rw.addSheet(Sheetname);
//						   rw.addColumn(Sheetname, "Test Suite");
//						   rw.addColumn(Sheetname, "Test Case");
//						   rw.addColumn(Sheetname, "Keyword");
//						   rw.addColumn(Sheetname, "Arg1");
//						   rw.addColumn(Sheetname, "Arg2");
//						   rw.addColumn(Sheetname, "Arg3");
//						   rw.addColumn(Sheetname,  "Arg4");
//						   rw.addColumn(Sheetname,  "Arg5");
//						   rw.addColumn(Sheetname,  "Arg6");
//						   rw.addColumn(Sheetname,  "Arg7");
//						   rw.addColumn(Sheetname, "Status");
//						   rw.addColumn(Sheetname, "Execution date and time");
//						   rw.addColumn(Sheetname, "Description");
//						   rw.addColumn(Sheetname, "Back to Consolidated");
//						   rw.setHyperLink(Sheetname,"Consolidated", "Back to Consolidated", 2,l);
//
//						   }
//	}
//
//
//
//
//	public void ReportLine(String filename,String Suite,String tid, String Keyword,int rowid,String Arg1, String Arg2, String Arg3, String Arg4,String Arg5,String Arg6,String Arg7,String Status,String date,String Desc){
//
//
//			   try{
//
//				   FileInputStream fs =new FileInputStream(filename);
//			        XSSFWorkbook wb = new XSSFWorkbook(fs);
//
//
//			        XSSFSheet sheet= wb.getSheet(Suite);
//
//			        XSSFRow rowhead=   sheet.createRow((int)rowid);
//			       //Style for pass and failed test cases.
//			        XSSFCellStyle my_style1 = wb.createCellStyle();
//	                my_style1.setFillPattern(XSSFCellStyle.FINE_DOTS);
//	                my_style1.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
//	                my_style1.setFillBackgroundColor(new HSSFColor.RED().getIndex());
//
//
//	                XSSFCellStyle my_style2 = wb.createCellStyle();
//	                my_style2.setFillPattern(XSSFCellStyle.FINE_DOTS );
//	                my_style2.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
//	                my_style2.setFillBackgroundColor(new HSSFColor.GREEN().getIndex());
//
//
//	                XSSFCellStyle my_style3 = wb.createCellStyle();
//	                XSSFFont hlink_font = wb.createFont();
//	                hlink_font.setUnderline(XSSFFont.U_SINGLE);
//	                hlink_font.setColor(IndexedColors.BLUE.getIndex());
//	                my_style3.setFont(hlink_font);
//	                my_style3.setFillPattern(XSSFCellStyle.FINE_DOTS );
//	                my_style3.setFillForegroundColor(new HSSFColor.WHITE().getIndex());
////	                my_style3.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
//
//
//					rowhead.createCell((short) 0).setCellValue(Suite);
//					rowhead.createCell((short) 1).setCellValue(tid);
//					rowhead.createCell((short) 2).setCellValue(Keyword);
//					rowhead.createCell((short) 3).setCellValue(Arg1);
//					rowhead.createCell((short) 4).setCellValue(Arg2);
//					rowhead.createCell((short) 5).setCellValue(Arg3);
//					rowhead.createCell((short) 6).setCellValue(Arg4);
//					rowhead.createCell((short) 7).setCellValue(Arg5);
//					rowhead.createCell((short) 8).setCellValue(Arg6);
//					rowhead.createCell((short) 9).setCellValue(Arg7);
//					rowhead.createCell((short) 11).setCellValue(date);
//					rowhead.createCell((short) 12).setCellValue(Desc);
//
//					if(rowid ==(short)1){
//						rowhead.createCell((short) 13).setCellValue(" <<===== Back to Consolidated ");
//						rowhead.getCell((short)13).setCellStyle(my_style3);
//					}
//
//					if(Status=="Fail"){
//					rowhead.createCell((short) 10).setCellValue(Status);
//					for(int i=0;i<=12;i++){
//					rowhead.getCell(i).setCellStyle(my_style1);}
//					}
//					else if(Status=="Pass")
//					{
//						rowhead.createCell((short) 10).setCellValue(Status);
//						for(int i=0;i<=12;i++){
//							rowhead.getCell(i).setCellStyle(my_style2);}
//					}
//
//
//					FileOutputStream fileOut =  new FileOutputStream(filename);
//					wb.write(fileOut);
//					fileOut.close();
//			      } catch ( Exception ex ) {
//					  							System.out.println(ex);
//				                           }
//	}
//
//			 			//   System.out.println("row id "+rowid);
//
//		     //  rw.setCellData("Sheet1", "Keyword", 5, "Turbo Charger");
//
//
//
//	String randomString( int len )
//	{
//	   StringBuilder sb = new StringBuilder( len );
//	   for( int ai = 0; ai < len; ai++ )
//	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
//	   return sb.toString();
//	}
//
//
//
//
//
//	 public void  Reporting(String Filename,String Sheetname) throws IOException{
//		 String	path=System.getProperty("user.dir")+"\\src\\CreateExcel.vbs";
//			System.out.println("User Directory:"+System.getProperty("user.dir"));
//	         Runtime.getRuntime().exec("WScript path" + "\"" + Filename + "\"" + " " + "\""  + Sheetname + "\"" );
//                          }
//
//
//
//}
//
//
