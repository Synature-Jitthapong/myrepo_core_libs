package com.j1tth4.mobile.util;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import com.syn.mobile.core.R;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

public abstract class DotNetWebServiceTask extends AsyncTask<String, String, String> {

	private final String nameSpace = "http://tempuri.org/";
	protected SoapObject soapRequest;
	protected SoapSerializationEnvelope envelope;
	protected HttpTransportSE androidHttpTransport;
	protected int timeout = 30 * 1000;
	protected String webMethod;
	protected Context context;
	protected PropertyInfo property;
	
	public DotNetWebServiceTask(Context c, String method){
		context = c;
		webMethod = method;

		soapRequest = new SoapObject(nameSpace, webMethod);
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(soapRequest);
	}
	
	@Override
	protected String doInBackground(String... uri) {
		String result = "";
		String url = uri[0];
		
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			
			System.setProperty("http.keepAlive", "false");
			
			androidHttpTransport = new HttpTransportSE(url, timeout);
			//androidHttpTransport.debug = true;
			String soapAction = nameSpace + webMethod;
			try {
				androidHttpTransport.call(soapAction, envelope);
				try {
					result = envelope.getResponse().toString();
				} catch (SoapFault e) {
					result = e.getMessage();
					e.printStackTrace();
				}
			} catch (IOException e) {
				result = e.getMessage();
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				result = e.getMessage();
				e.printStackTrace();
			}
			
			if(result == null || result.equals("")){
				result = context.getString(R.string.cannot_connect);
			}
		}else{
			result = context.getString(R.string.cannot_connect);
		}
		return result;
	}
}
