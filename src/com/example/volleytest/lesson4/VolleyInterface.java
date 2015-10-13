package com.example.volleytest.lesson4;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.content.Context;

public abstract class VolleyInterface {
	public Context context;
	public static Listener<String> listener;
	public static ErrorListener errorListener;

	@SuppressWarnings("static-access")
	public VolleyInterface(Context context, Listener<String> listener, ErrorListener errorListener) {
		this.context = context;
		this.listener = listener;
		this.errorListener = errorListener;
	}

	public abstract void onSucess(String result);

	public abstract void onError(VolleyError error);

	public Listener<String> loadingListener() {
		listener = new Listener<String>() {

			@Override
			public void onResponse(String response) {
				onSucess(response);
			}
		};
		return listener;
	}

	public ErrorListener errorListener() {
		errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				onError(error);
			}
		};
		return errorListener;
	}
}
