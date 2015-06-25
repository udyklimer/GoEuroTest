package com.goeuro.api;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class represents List of the places
 * @author Udy
 *
 */
public class GoEuroPlacesList{
	public List<GoEuroPlace> places = new ArrayList<GoEuroPlace>();

	
	/**
	 * 
	 */
	public String toString()
	{
		String tmp = "";
		if(places != null)
		{
			for(int i=0; i<places.size(); i++)
			{
				GoEuroPlace goEuroPlace = places.get(i);
				tmp += goEuroPlace.toString() + "\n";
			}
			
		}
		return tmp;
	}
	
	
	/**
	 * 
	 * @return String of all the places with separate commas (each in new line)
	 */
	public String toCSV()
	{
		String tmp = "";
		if(places != null)
		{
			for(int i=0; i<places.size(); i++)
			{
				GoEuroPlace goEuroPlace = places.get(i);
				tmp += goEuroPlace.toCSV() + "\n";
			}
			
		}
		return tmp;
	}
	
}
