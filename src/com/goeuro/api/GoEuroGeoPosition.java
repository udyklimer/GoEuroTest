package com.goeuro.api;

/**
 * This Class represents GeoPosition for place
 * @author Udy
 *
 */
public class GoEuroGeoPosition {

	private double mLatitude = 0.0;
	private double mLongitude = 0.0;
	
	
	
	public double getLatitude() {
		return mLatitude;
	}


	public void setLatitude(double pLatitude) {
		mLatitude = pLatitude;
	}


	public double getLongitude() {
		return mLongitude;
	}


	public void setLongitude(double pLongitude) {
		mLongitude = pLongitude;
	}


	/**
	 * 
	 */
	public String toString(){
		String tmp = "";
		tmp += ("{latitude= " + mLatitude + ", longitude=" +  mLongitude + "}");
		return tmp;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toCSV(){
		String tmp = "";
		
		tmp += mLatitude + ",";
		tmp += mLongitude;
		
		return tmp;
	}
}
