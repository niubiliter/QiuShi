package com.huangliusong.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.integer;
import android.util.Log;

public class HttpUtils {
	
	private static final String TAG = "robin debug";

	/**
	 * 用HttpURLConnection 实现网络数据的下载
	 */
	public static byte[] loadDataForHttpURLConnection(String dataUrl){
		HttpURLConnection connection=null;
		try {
			//1.创建URL
			URL url=new URL(dataUrl);
			
			//2.创建HttpURLConnection
			connection=(HttpURLConnection) url.openConnection();
			
			//3.设置属性
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			
			//4.开始连接
			connection.connect();//阻塞
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			if(connection.getResponseCode()==200){
				InputStream inputStream=connection.getInputStream();
				byte[] buffer=new byte[1024];
				int num=-1;
				while((num=inputStream.read(buffer))!=-1){
					bos.write(buffer,0,num);
					bos.flush();
				}
				//bos转换为byte数组
				byte[] result= bos.toByteArray();
				Log.e(TAG,"result:"+result);
				
				
				return result;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		return null;
	}
	

}
