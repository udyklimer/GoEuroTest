package com.goeuro.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Utils {

	/**
	 * This function gets File Name and String content and creates File .CVS 
	 * with that name and content
	 * @param pFileName
	 * @param pContent
	 * @return java.io.File
	 */
	public static File writeCSVToFile(String pFileName, String pContent)
	{
		File f = null;
		try 
		{
			//create File object
			f = new File(pFileName + ".csv");
			
			//Write UTF-8 encoded File
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
			out.write(pContent);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
	
}
