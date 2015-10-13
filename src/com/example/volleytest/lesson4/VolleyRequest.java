package com.example.volleytest.lesson4;

import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.StringRequest;
import com.example.volleytest.app.MyApplication;

public class VolleyRequest {

	public static StringRequest stringRequest;
	public static Context context;

	@SuppressWarnings("static-access")
	public static void RequestGet(Context context, String url, String tag, VolleyInterface vif) {
		MyApplication.getRequestQueue().cancelAll(tag);
		stringRequest = new StringRequest(Method.GET, url, vif.loadingListener(), vif.errorListener);
		stringRequest.setTag(tag);
		MyApplication.getRequestQueue().add(stringRequest);
		MyApplication.getRequestQueue().start();
	}

	@SuppressWarnings("static-access")
	public static void RequestPost(Context context, String url, String tag, final Map<String, String> params,
			VolleyInterface vif) {
		MyApplication.getRequestQueue().cancelAll(tag);
		stringRequest = new StringRequest(Method.POST, url, vif.loadingListener(), vif.errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}
		};
		stringRequest.setTag(tag);
		MyApplication.getRequestQueue().add(stringRequest);
		MyApplication.getRequestQueue().start();
	}
}
