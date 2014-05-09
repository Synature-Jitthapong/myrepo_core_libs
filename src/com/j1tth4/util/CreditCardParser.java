package com.j1tth4.util;

public class CreditCardParser {
	
	public static final String END_SENTINEL1 = "\\?";
	
	public static final String END_SENTINEL2 = "\\%";
	
	public static final String FIELD_SEPERATOR = "\\^";
	
	private String mCardNo;
	private String mCardHolderName;
	private String mCardExpDate;
	
	public boolean parser(String content){
		if(content != null && !content.equals("")){
			int len = content.length();
			String endSentinel = content.substring(len - 1);
			String[] track = content.split(endSentinel.equals("?") ? END_SENTINEL1 : END_SENTINEL2);
			if(track.length == 1){
				parserDebit(track[0]);
				return true;
			}else if(track.length >= 2){
				String track1 = track[0].trim();
				parserCredit(track1);
				return true;
			}
		}
		return false;
	}
	
	private void parserDebit(String track){
		//"Track2:0025870064605584=1299=330001234=16824?"
		String dataTrack[] = track.split("=");
		mCardNo = dataTrack[0].substring(7);
		mCardHolderName = "";
		mCardExpDate = "";
	}
	
	private void parserCredit(String track1){
		//"Track1:B4552939410162971^JITTHAPONG ARJSALEE       ^170220100000   876540039600C000?"
		//"Track2:4552939410162971=17022010000039687654?";
		String dataTrack1[] = track1.split(FIELD_SEPERATOR);
		mCardNo = dataTrack1[0].substring(8);
		mCardHolderName = dataTrack1[1].trim();
		mCardExpDate = dataTrack1[2].substring(0, 4);	
	}
	
	public String getExpDate(){
		return mCardExpDate;
	}
	
	public String getCardHolderName(){
		return mCardHolderName;
	}
	
	public String getCardNo(){
		return mCardNo;
	}
}
