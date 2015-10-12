package com.example.volleytest.lesson3;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.volleytest.R;
import com.example.volleytest.app.MyApplication;

/**
 * Android Volley完全解析(三)，定制自己的Request
 * 
 * @author admin
 * 
 */
public class Test3Acivity extends Activity implements OnClickListener {
	private static final String TAG = "Test3Activity";
	private Button btn_xmlRequest, btn_gsonRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test3_activity);
		btn_xmlRequest = (Button) findViewById(R.id.btn_xmlRequest);
		btn_gsonRequest = (Button) findViewById(R.id.btn_gsonRequest);

		btn_xmlRequest.setOnClickListener(this);
		btn_gsonRequest.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_xmlRequest:
			XMLRequest();
			break;
		case R.id.btn_gsonRequest:
			gsonRequest();
		default:
			break;
		}
	}

	private void XMLRequest() {
		String url = "http://flash.weather.com.cn/wmaps/xml/china.xml";
		XMLRequest xmlRequest = new XMLRequest(url, new Response.Listener<XmlPullParser>() {

			@Override
			public void onResponse(XmlPullParser response) {
				try {
					int eventType = response.getEventType();
					while (eventType != XmlPullParser.END_DOCUMENT) {
						switch (eventType) {
						case XmlPullParser.START_TAG:
							String nodeName = response.getName();
							if ("city".equals(nodeName)) {
								String pName = response.getAttributeValue(0);
								Log.i(TAG, "pName is " + pName);
							}
							break;
						}
						eventType = response.next();
					}
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		});
		MyApplication.getRequestQueue().add(xmlRequest);
	}

	private <T> void gsonRequest() {
		String url = "http://www.weather.com.cn/adat/sk/101010100.html";
		GsonRequest<Weather> gsonRequest = new GsonRequest<>(url, Weather.class, new Response.Listener<Weather>() {

			@Override
			public void onResponse(Weather weather) {
				WeatherInfo weatherInfo = weather.getWeatherinfo();
				Log.i(TAG, "city is " + weatherInfo.getCity());
				Log.i(TAG, "temp is " + weatherInfo.getTemp());
				Log.i(TAG, "time is " + weatherInfo.getTime());
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e("TAG", error.getMessage(), error);
			}
		});
		MyApplication.getRequestQueue().add(gsonRequest);
	}
}
