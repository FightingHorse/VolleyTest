package com.example.volleytest.lesson4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.volleytest.R;

public class Test4Acivity extends Activity implements OnClickListener {
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test4_activity);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String url = "https://www.baidu.com/?tn=95386311_hao_pg";
		VolleyRequest.RequestGet(getApplicationContext(), url, "requestGet", new VolleyInterface(this,
				VolleyInterface.listener, VolleyInterface.errorListener) {

			@Override
			public void onSucess(String result) {
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onError(VolleyError error) {
				Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
			}
		});
	}
}
