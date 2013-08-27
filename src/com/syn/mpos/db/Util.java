package com.syn.mpos.db;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author j1tth4
 *
 */
public abstract class Util {
	protected Calendar getCalendar(){
		Calendar calendar = Calendar.getInstance();
		return calendar = new GregorianCalendar(calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), 
				calendar.get(Calendar.SECOND));
	}
	
	protected float calculateVat(float productPrice, float productAmount, float vat){
		float vatAmount = productPrice * productAmount * toVatPercent(vat);
		return vatAmount;
	}
	
	protected float toVatPercent(float vat){
		return vat / 100;
	}
}
