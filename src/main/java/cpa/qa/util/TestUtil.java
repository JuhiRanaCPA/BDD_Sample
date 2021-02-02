//package cpa.qa.util;
//
//import java.text.Format;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Hashtable;
//
//import org.apache.poi.xssf.usermodel.XSSFHyperlink;
//import org.testng.annotations.Test;
//
//import java.util.Date;
//import java.util.Formatter;
//
//import com.aventstack.extentreports.Status;
//import com.google.common.base.Joiner;
//
//import cpa.qa.testcases.TestRunner;
//
//
//public class TestUtil {
//
//	public static int ctr=0;
//	public static int RowsCosl=2;
//	public static String sDate;
//	public static String testn;
//	public static XSSFHyperlink l;
//	public static Object[][] getData(String testCase, Xls_Reader xls){
//		int testStartRowNum=1;
//		while(!xls.getCellData("TestData", 0, testStartRowNum).equals(testCase)){
//			testStartRowNum++;
//		}
//		System.out.println();
//
//		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		System.out.println();
//		System.out.println("Test Case "+ testCase+ " starts from row number "+ testStartRowNum + " of TestData Sheet.");
//
//
//		// rows of data
//		int dataStartRowNum=testStartRowNum+2;
//		int rows=0;
//		while(!xls.getCellData("TestData",0,dataStartRowNum+rows).equals("")){
//			rows++;
//		}
//		System.out.println("Total test data rows in test case:  "+testCase+" are "+ rows );
//
//
//		// total cols
//		int colstartRowNum = testStartRowNum+1;
//		int cols=0;
//		while(!xls.getCellData("TestData", cols, colstartRowNum).equals("")){
//			cols++;
//		}
//		System.out.println("Total test data columns in test case: "+testCase+" are "+ cols );
//
//		Object testData[][] = new Object[rows][1];
//		int index=0;
//		Hashtable <String,String> table = null;
//		// extract data
//		System.out.println();
//		System.out.println("************ Test data format - Display Start *******************");
//		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
//			table = new Hashtable<String,String>(); // init for every row
//			System.out.println();
//			for(int cNum=0;cNum<cols;cNum++){
//				String key = xls.getCellData("TestData", cNum, colstartRowNum);
//				String value = xls.getCellData("TestData", cNum, rNum);
//				System.out.print(value +" --- ");
//				//fill the table
//				table.put(key, value);
//			}
//
//			// put table in array
//			System.out.println("");
//			testData[index][0]=table;
//			index++;
//		}
//
//		System.out.println();
//		System.out.println("************Test data format - Display End *******************");
//		System.out.println();
//		return testData;
//	}
//
//	// true  -  Y
//	// false -  N
//	public static Boolean getRunmode(String testName, Xls_Reader xls){
//		int rows = xls.getRowCount("Tests");
//		System.out.println();
//		System.out.println("Total number of test suites to be executed are  -> "+ (rows-1));
//		for(int i=2;i<=rows;i++){
//			String tcid = xls.getCellData("Tests", "TestSuite", i);
//			String runmode=xls.getCellData("Tests", "Runmode", i);
////			testn=tcid;
//			System.out.println("TCID and RunMODE on Tests Page : "+tcid+"\t"+runmode);
//			System.out.println("print i "+i);
//			if(tcid.equals(testName)){
//				if(runmode.equals("Y"))
//				{ System.out.println();
//				System.out.println("Execution started for test suite  -> "+ (i-1));
//				System.out.println();
//				return true; }
//				else
//				{
//					System.out.println();
//					System.out.println("Execution skipped for test suite  -> "+ (i-1));
//					System.out.println();
//					return false; }
//			}
//		}
//		return false;// default
//	}
//
//
//	//Method to generate consolidated test report
//	public static void getReport(String testName, Xls_Reader xls1, String Browser){
//
//		int rows = xls1.getRowCount(testName);
//		System.out.println();
//		System.out.println("Total number of rows -> "+ (rows-1));
//		int Pass=0;
//		int Fail=0;
//		ArrayList<String> Stat = new ArrayList<String>();
//		ArrayList<String> TCName = new ArrayList<String>();
//		ArrayList<String> TcDescription = new ArrayList<String>();     //
//
//		for(int i=2;i<=rows;i++){
//
//			String a = xls1.getCellData(testName, "Status", i);
//			Stat.add(a);
//			String TC = xls1.getCellData(testName, "Test Case", i);
//			String TcDesc = xls1.getCellData(testName, "Description", i);			//
//
//			// System.out.println(" " +Status);
//			if(!TC.equals("") && !TC.equalsIgnoreCase("TCN")) {
//
//					if(i!=2) {
//					}
//
//				TcDescription.add(TcDesc);			//
//				TCName.add(TC);
//				String b = Stat.get(Stat.size() - 1);
//				Stat.remove(Stat.size() - 1);
//				//System.out.println("next" +Stat);
//				if (Stat.contains("Fail") == true ){
//					// System.out.println("Fail");
//					TestRunner.test.log(Status.FAIL,"<b><I>"+TCName.get(TCName.size()-2)+"</b></I> has not passed successfully. ");
//					Fail++ ;
//
//				}
//				else {
//					if (i!=2){
//						TCName.remove(TCName.size() - 2);
//						TcDescription.remove(TcDescription.size() - 2);   //
//					}
//					//  System.out.println("Pass");
//					TestRunner.test.log(Status.PASS,"<b><I>"+ TCName.get(TCName.size()-1)+"</b></I> has passed successfully. ");
//					Pass++;
//
//				}
//
//				Stat.clear();
//				Stat.add(b);
//				if(i==rows) {  System.out.println("Test execution for one iteration finished");	}
//			}
//			if(!TC.equals("") && TC.equalsIgnoreCase("TCN")) {
//				String b = Stat.get(Stat.size() - 1);
//				if (b.equalsIgnoreCase("Pass")){
//					Stat.remove(Stat.size() - 1);
////					Status.add("Fail");
//					Stat.add("Pass");
//				}
//				else {
//					Stat.remove(Stat.size() - 1);
////					Status.add("Pass");
//					Stat.add("Fail");
//				}
//			}
//
//		}
//
//		TCName.remove(TCName.size()-1);
//		System.out.println("Total pass TC's :" +(Pass));
//		System.out.println("Total Fail TC's :" +Fail);
//		System.out.println("Failed TC "+TCName );
//		String Pass1 = Integer.toString((Pass));
//		String Fail1 = Integer.toString(Fail);
//		String FailedTC = Joiner.on(", ").join(TCName);
//
//		// Create Consolidated sheet test Report
//		String Sheetname= "consolidated" ;
//		if (xls1.isSheetExist(Sheetname)){
//			if (ctr==0) {
//
//			/*	xls1.removeColumn(Sheetname, 0);
//				xls1.removeColumn(Sheetname, 1);
//				xls1.removeColumn(Sheetname, 2);
//				xls1.removeColumn(Sheetname, 3);
//				xls1.addColumn(Sheetname, "Component");
//				xls1.addColumn(Sheetname, "Pass count");
//				xls1.addColumn(Sheetname, "Fail count");
//				xls1.addColumn(Sheetname, "Failed Test Cases"); */
//				xls1.setCellData(Sheetname, "Pass count", RowsCosl, Pass1);
//				xls1.setCellData(Sheetname, "Fail count", RowsCosl, Fail1);
//				xls1.setCellData(Sheetname, "Component", RowsCosl, testName+"_"+Browser);
//				xls1.setHyperLink(Sheetname,testName, "Component", RowsCosl,l);
//				xls1.setCellData(Sheetname, "Failed Test Cases", RowsCosl, FailedTC);
//
//				ctr=ctr+1;
//			//	return false;
//				  }
//				else{
//				ctr=ctr+1;
//				 RowsCosl = xls1.getRowCount("consolidated");
//					}
//				}
//
//			else
//			{
//				xls1.addSheet(Sheetname);
//				xls1.addColumn(Sheetname, "Component");
//				xls1.addColumn(Sheetname, "Pass count");
//				xls1.addColumn(Sheetname, "Fail count");
//				xls1.addColumn(Sheetname, "Failed Test Cases");
//				xls1.addColumn(Sheetname, "Execution Date Time Stamp");
//
//				//ctr=ctr+1;
//			//	RowsCosl= RowsCosl-1 ;
//				//  RowsCos2 = xls1.getRowCount("consolidated");
//
//			}
//
//		// int RowsCosl = xls1.getRowCount("consolidated");
//		xls1.setCellData(Sheetname, "Pass count", RowsCosl, Pass1);
//		xls1.setCellData(Sheetname, "Fail count", RowsCosl, Fail1);
//		xls1.setCellData(Sheetname, "Component", RowsCosl, testName+"_"+Browser);
//		xls1.setHyperLink(Sheetname,testName, "Component", RowsCosl,l);
//		xls1.setCellData(Sheetname, "Failed Test Cases", RowsCosl, FailedTC);
//		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date= new Date();
//		sDate = formatter.format(date);
//		xls1.setCellData(Sheetname, "Execution Date Time Stamp", RowsCosl, date.toString());
//		RowsCosl++;
//		ctr=0;
//		//	return false; // default
//
//	}
//}
