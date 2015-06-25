package com.goeuro.test;

import java.io.File;

import com.goeuro.api.GoEuroAPIManager;
import com.goeuro.api.GoEuroPlacesList;

public class GoEuro {

	public static void main(String[] args) {
		
		try
		{
			//get the parameter from the command line
			String param = null;
			try
			{
				param = args[0];
			}
			catch(Exception e){}
			
			if(null == param)//no input from user
			{
				System.out.println("Missing User Argument! exiting.");
				return;
			}
			
			//create the API manager object
			GoEuroAPIManager ge =  new GoEuroAPIManager();
			
			//query API for places
			GoEuroPlacesList list = ge.getPlaces(param);
			if(null != list)//no null == OK
			{
				//get CSV String
				String csv = list.toCSV();
				
				//Write to file (use query parameter as name)
				File f = Utils.writeCSVToFile(param, csv);
				System.out.println("CVS File Created in " + f.getAbsolutePath());
			}
			else//null == ERROR
			{
				System.out.println("Error Occured while querying the API! exiting.");
				return;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
