package com.example.volleytest.lesson2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.example.volleytest.R;
import com.example.volleytest.app.MyApplication;

/**
 * Android Volley完全解析(二)，使用Volley加载网络图片
 * 
 */
public class Test2Acivity extends Activity implements OnClickListener {
	private static final String TAG = "Test2Acivity";

	private Button btn_imageRequest, btn_imageLoader, btn_networkImageView;
	private ImageView img_imgeView;
	private NetworkImageView net_image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2_activity);
		btn_imageRequest = (Button) findViewById(R.id.btn_imageRequest);
		btn_imageLoader = (Button) findViewById(R.id.btn_imageLoader);
		btn_networkImageView = (Button) findViewById(R.id.btn_networkImageView);

		img_imgeView = (ImageView) findViewById(R.id.img_imgeView);
		net_image = (NetworkImageView) findViewById(R.id.net_image);
		net_image.setDefaultImageResId(R.drawable.ic_launcher);

		btn_imageRequest.setOnClickListener(this);
		btn_imageLoader.setOnClickListener(this);
		btn_networkImageView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_imageRequest:
			imageRequest();
			break;
		case R.id.btn_imageLoader:
			imageLoader();
			break;
		case R.id.btn_networkImageView:
			networkImageView();
			break;
		default:
			break;
		}
	}

	private void imageRequest() {
		String url = "http://image.photophoto.cn/nm-6/018/030/0180300244.jpg";
		ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {

				img_imgeView.setImageBitmap(response);
			}
		}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, error.getMessage());
			}
		});
		MyApplication.getRequestQueue().add(imageRequest);
	}

	private void imageLoader() {
		String url = "http://www.xxjxsj.cn/article/UploadPic/2009-10/2009101018545196251.jpg";

		ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BitMapCache());

		@SuppressWarnings("static-access")
		ImageListener imageListener = imageLoader.getImageListener(img_imgeView, R.drawable.ic_launcher,
				R.drawable.ic_launcher);

		imageLoader.get(url, imageListener);
	}

	private void networkImageView() {
		String url = "http://pic.nipic.com/2007-11-09/2007119122519868_2.jpg";

		ImageLoader imageLoader = new ImageLoader(MyApplication.getRequestQueue(), new BitMapCache());

		net_image.setImageUrl(url, imageLoader);
	}
}
