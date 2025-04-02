package com.selenium.automation;

import java.util.*;

import org.testng.TestNG;

public class App 
{
	
		  public static void main(String[] args) {
				TestNG runner=new TestNG();
				List<String> suitefiles=new ArrayList<String>();
							suitefiles.add("xml/testng.xml");
				runner.setTestSuites(suitefiles);
				runner.run();
				//report(GetterSetter.getAppName());
		    }
}
