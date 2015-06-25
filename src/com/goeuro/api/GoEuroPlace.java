package com.goeuro.api;

/**
 * This Class represents Go Euro Place (only relevant fields)
 * @author Udy
 *
 */
public class GoEuroPlace{
	
	private long _id = -1;
	private String mName = "";
	private String mType = "";
	private GoEuroGeoPosition mGeoPosition;
	
	

	public long getId() {
		return _id;
	}


	public void setId(long pId) {
		_id = pId;
	}


	public String getName() {
		return mName;
	}


	public void setName(String pName) {
		mName = pName;
	}


	public String getType() {
		return mType;
	}


	public void setType(String pType) {
		mType = pType;
	}


	public GoEuroGeoPosition getGeoPosition() {
		return mGeoPosition;
	}


	public void setGeoPosition(GoEuroGeoPosition pGeoPosition) {
		mGeoPosition = pGeoPosition;
	}


	public String toString(){
		String tmp = "";
		tmp += ("_id= " + _id);
		tmp += ("\n name= " + mName);
		tmp += ("\n type= " + mType);
		tmp += ("\n geo_position= " + mGeoPosition.toString());
		
		return tmp;
	}
	
	/**
	 * 
	 * @return String of all the values with separate commas
	 */
	public String toCSV(){
		String tmp = "";
		
		tmp += _id + ",";
		tmp += mName + ",";
		tmp += mType + ",";
		tmp += mGeoPosition.toCSV();
		
		return tmp;
	}
	
}
