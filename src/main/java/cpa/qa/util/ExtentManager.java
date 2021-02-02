package cpa.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

       private static ExtentReports extent;
       private static ExtentTest test;
       private static ExtentHtmlReporter htmlReporter;
       private static String filePath = System.getProperty("user.dir") + "\\extentreport.html";
       
       public static ExtentReports GetExtent(){
             if(extent != null)
                    return extent;
                    extent = new ExtentReports();
             
             extent.attachReporter(getHtmlReporter());
             return extent;
             
       }
       
       public static ExtentHtmlReporter getHtmlReporter(){
             
             htmlReporter = new ExtentHtmlReporter(filePath);
             htmlReporter.config().setChartVisibilityOnOpen(true);
             htmlReporter.config().setDocumentTitle("CPA Global");
             htmlReporter.config().setReportName("FILE Automation Report");
             
             return htmlReporter;
       }
       
       public static ExtentTest createTest(String testName, String description){
             test = extent.createTest(testName, description);
             return test;
       }
       
}
