package com.huangliusong.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.huangliusong.utils.HttpUtils;

public class LoadImageTask extends AsyncTask<String, Void, byte[]>{
	private static final String TAG = "robin debug";
	private Context context;
	private CallBackListener callback;
	private String imageUrl;
	
	public LoadImageTask(Context context,CallBackListener callback) {
		this.context=context;
		this.callback=callback;
	}
	
	

	@Override //后台执行耗时任务  异步任务
	protected byte[] doInBackground(String... param) {
		Log.e(TAG, "backgroud thread:"+Thread.currentThread().getName());
		imageUrl=param[0];
		byte[] result=HttpUtils.loadDataForHttpURLConnection(param[0]);
		
		return result;
	}
	
	@Override
	protected void onPostExecute(byte[] result) {
		Log.e(TAG, "post thread:"+Thread.currentThread().getName());
		super.onPostExecute(result);
		if(result!=null){
			Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0,result.length);
			
			callback.onResponse(bitmap,imageUrl);
		}

	}
	
	public interface CallBackListener{
		public void onResponse(Bitmap bitmap,String url);
		
		
	}
	
}