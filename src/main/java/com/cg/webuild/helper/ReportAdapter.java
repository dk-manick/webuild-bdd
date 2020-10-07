package com.cg.webuild.helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

	public class ReportAdapter extends ExtentCucumberAdapter {
	
	public ReportAdapter(String file) {
		super(file);
	}

	 private static Logger Log = Logger.getLogger(ReportAdapter.class);
	 private static Map<String, Boolean> systemInfoKeyMap = new HashMap();


	 public static void addStepLog(String message) {
		ExtentCucumberAdapter.addTestStepLog(message);
	 }


	 public static void addScreenCaptureFromPath(String imagePath) throws IOException {
		ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(imagePath);
	 }


	 public static void addScreenCaptureFromPath(String imagePath, String title) throws IOException {
		ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(imagePath, title);
	 }
	 
	 private static ExtentReports getExtentReports() {
	        return ExtentService.getInstance();
	 }

     public static void setTestRunnerOutput(List<String> log) {
    	getExtentReports().setTestRunnerOutput(log);
     }

     public static void setTestRunnerOutput(String outputMessage) {
    	getExtentReports().setTestRunnerOutput(outputMessage);
     }
	
	 public static void setSystemInfo(String key, String value) {
        if (systemInfoKeyMap.isEmpty() || !systemInfoKeyMap.containsKey(key)) {
            systemInfoKeyMap.put(key, false);
        }
        if (systemInfoKeyMap.get(key)) {
            return;
        }
        getExtentReports().setSystemInfo(key, value);
        systemInfoKeyMap.put(key, true);
     }
}