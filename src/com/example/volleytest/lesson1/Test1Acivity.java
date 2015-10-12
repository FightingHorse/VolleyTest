package com.example.volleytest.lesson1;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.volleytest.R;
import com.example.volleytest.app.MyApplication;

/**
 * 
 * Android Volley完全解析(一)，初识Volley的基本用法
 * 详细讲解见 <a>http://blog.csdn.net/guolin_blog/article/details/17482095</a>
 * StringRequest的GET.POST请求 JsonObjectRequest JsonArrayRequest
 * 
 */
public class Test1Acivity extends Activity implements OnClickListener {
	private static final String TAG = "Test1Acivity";

	private static final String STRINGREQUEST = "stringRequest";
	private static final String STRINGREQUESTPOST = "stringRequestPost";
	private static final String JSONOBJECT = "jsonObject";
	private static final String JSONOARRAY = "jsonArray";

	private Button btn_stringRequest, btn_stringRequest_Post, btn_jsonRequest, btn_jsonArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test1_activity);

		btn_stringRequest = (Button) findViewById(R.id.btn_stringRequest);
		btn_stringRequest_Post = (Button) findViewById(R.id.btn_stringRequest_Post);
		btn_jsonRequest = (Button) findViewById(R.id.btn_jsonRequest);
		btn_jsonArray = (Button) findViewById(R.id.btn_jsonArray);

		btn_stringRequest.setOnClickListener(this);
		btn_stringRequest_Post.setOnClickListener(this);
		btn_jsonRequest.setOnClickListener(this);
		btn_jsonArray.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_stringRequest:
			stringRequest();
			break;
		case R.id.btn_stringRequest_Post:
			stringRequestPost();
			break;
		case R.id.btn_jsonRequest:
			jsonObjectRequest();
			break;
		case R.id.btn_jsonArray:
			jsonArrayRequest();
			break;
		default:
			break;
		}
	}

	private void stringRequest() {
		String url = "https://www.baidu.com/?tn=95386311_hao_pg";
		StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i(TAG, response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage());
			}
		});
		stringRequest.setTag(STRINGREQUEST);
		MyApplication.getRequestQueue().add(stringRequest);
	}

	private void stringRequestPost() {
		String url = "https://www.baidu.com/?tn=95386311_hao_pg";
		StringRequest stringRequestPost = new StringRequest(url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.i(TAG, response);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage());
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> postParams = new HashMap<>();
				postParams.put("value1", "value1");
				postParams.put("value2", "value2");
				return postParams;
			}
		};
		stringRequestPost.setTag(STRINGREQUESTPOST);
		MyApplication.getRequestQueue().add(stringRequestPost);
	}

	private void jsonObjectRequest() {
		String url = "http://www.weather.com.cn/adat/cityinfo/101010100.html";
		JsonObjectRequest jsonObjRequest = new JsonObjectRequest(url, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.i(TAG, response.toString());
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage());
			}
		});
		jsonObjRequest.setTag(JSONOBJECT);
		MyApplication.getRequestQueue().add(jsonObjRequest);
	}

	private void jsonArrayRequest() {
		String url = "http://www.weather.com.cn/adat/cityinfo/101010100.html";// 这个第方的地址必须是一个可以返回JsonArray的地址否则报错,我这里不是这样的地址ps:
																				// 没找到公用的这样的接口地址
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				Log.i(TAG, response.toString());
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage());
			}
		});
		jsonArrayRequest.setTag(JSONOARRAY);
		MyApplication.getRequestQueue().add(jsonArrayRequest);
	}

	@Override
	protected void onStop() {
		super.onStop();
		MyApplication.getRequestQueue().cancelAll(STRINGREQUEST);
		MyApplication.getRequestQueue().cancelAll(STRINGREQUESTPOST);
		MyApplication.getRequestQueue().cancelAll(JSONOBJECT);
		MyApplication.getRequestQueue().cancelAll(JSONOARRAY);
	}
}
