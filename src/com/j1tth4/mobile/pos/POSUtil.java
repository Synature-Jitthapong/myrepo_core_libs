package com.j1tth4.mobile.pos;

public abstract class POSUtil implements IPOS {
	
	public float calculateVat(float productPrice, float productAmount, float vat){
		float vatAmount = productPrice * productAmount * toVatPercent(vat);
		return vatAmount;
	}
	
	protected float toVatPercent(float vat){
		return vat / 100;
	}

	@Override
	public void payment() {
		
	}

	@Override
	public void discount() {
		
	}
}
