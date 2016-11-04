package com.huangliusong.asynctask;

import com.huangliusong.utils.HttpUtils;

import android.content.Context;
import android.os.AsyncTask;

public class DownLoadTask extends AsyncTask<String, Void, byte[]>{
	private Context context;
	private CallBackListener callback;
	public DownLoadTask(Context context,CallBackListener callback) {
		this.context=context;
		this.callback=callback;		
	}
	@Override
	protected byte[] doInBackground(String... params) {
		// TODO Auto-generated method stub
		return HttpUtils.loadDataForHttpURLConnection(params[0]);
	}
	
	@Override
	protected void onPostExecute(byte[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result!=null&&callback!=null){
			callback.onResponse(new String(result));
		}
		
		
		
	}
	
	
	
	//当异步下载完毕通知(回调接口方法)Activity获取json数据
	public interface CallBackListener{
		public void onResponse(String jsonString);
		
	}

}
