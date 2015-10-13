package com.example.volleytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.volleytest.lesson1.Test1Acivity;
import com.example.volleytest.lesson2.Test2Acivity;
import com.example.volleytest.lesson3.Test3Acivity;
import com.example.volleytest.lesson4.Test4Acivity;

public class MainActivity extends Activity implements OnClickListener {

	private Button btn1, btn2, btn3, btn4;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:

			intent = new Intent(MainActivity.this, Test1Acivity.class);

			break;
		case R.id.button2:
			intent = new Intent(MainActivity.this, Test2Acivity.class);
			break;

		case R.id.button3:
			intent = new Intent(MainActivity.this, Test3Acivity.class);
			break;
		case R.id.button4:
			intent = new Intent(MainActivity.this, Test4Acivity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}
}
