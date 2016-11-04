package com.huangliusong.act;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.robin.myapp.R;


public class LogoAct extends Activity{
	
	
	protected static final String TAG = "robin debug";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		Thread thread=new Thread(runnable);
		thread.start();
	}
	
	Handler handler=new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			if(msg.what==1)
			{
				SharedPreferences sharedPreferences=LogoAct.this.getSharedPreferences("config",Context.MODE_PRIVATE);
				boolean isFirst=sharedPreferences.getBoolean("isFirst", false);
				Log.i(TAG,"isFirst:"+isFirst);
				if(!isFirst){
					LogoAct.this.startActivity(new Intent(LogoAct.this,WelcomeAct.class));
					
				}else{
					LogoAct.this.startActivity(new Intent(LogoAct.this,MainFragAct.class));
					
				}
				LogoAct.this.finish();
			}
		}
	};
	
	

	Runnable runnable=new Runnable() {
		@Override
		public void run() {	
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Message message=  handler.obtainMessage();
			 message.what=1;
			 message.sendToTarget();
		}
	};
	
	
	
}
