package com.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class ReadDataFromXcel {
	@DataProvider(name="testdata")
	public String[][]  readAllData() throws IOException
	{
		
		String path=System.getProperty("user.dir")+"//testdata//apidata.xlsx";
		int nrows=XLUtils.getRowCount(path, "sheet1");
		int ncols=XLUtils.getCellCount(path, "sheet1", 0);
		
		String arr[][]= new String[nrows][ncols];
		for(int i=1; i<=nrows;i++)
		{
			for(int j=0; j<ncols;j++)
			{
				arr[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j);
				
			}
		}
		return arr;
		
	}
	@DataProvider(name="userdata")
	public String[] singleUserName() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata//apidata.xlsx";
		int nrows=XLUtils.getRowCount(path, "sheet1");
		
		String ar[]=new String[nrows];
		for(int i=1; i<=nrows; i++)
		{
			ar[i-1]=XLUtils.getSingleUser(path, "sheet1", i, 1);
		}
		return ar;
		
	}

}
