package com.j1tth4.mobile.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
	public static final String NAME_SPACE = "http://tempuri.org/";
	protected SoapObject mSoapRequest;
	protected SoapSerializationEnvelope mEnvelope;
	protected HttpTransportSE mAndroidHttpTransport;
	protected int mTimeout = 30 * 1000;
	protected String mWebMethod;
	protected Context mContext;
	protected PropertyInfo mProperty;
	protected String mHttpErrMsg;
	
	public DotNetWebServiceTask(Context c, String method){
		mContext = c;
		mWebMethod = method;

		mSoapRequest = new SoapObject(NAME_SPACE, mWebMethod);
		mEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		mEnvelope.dotNet = true;
		mEnvelope.setOutputSoapObject(mSoapRequest);
	}
	
	@Override
	protected String doInBackground(String... uri) {
		String result = "";
		String url = uri[0];
		
		ConnectivityManager connMgr = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			System.setProperty("http.keepAlive", "false");
			mAndroidHttpTransport = new HttpTransportSE(url, mTimeout);
			//androidHttpTransport.debug = true;
			String soapAction = NAME_SPACE + mWebMethod;
			try {
				mAndroidHttpTransport.call(soapAction, mEnvelope);
				try {
					result = mEnvelope.getResponse().toString();
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
				result = mContext.getString(R.string.cannot_connect);
			}
		}else{
			result = mContext.getString(R.string.cannot_connect);
		}
		return result;
	}
}
