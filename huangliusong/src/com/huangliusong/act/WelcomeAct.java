package com.huangliusong.act;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.robin.myapp.R;

public class WelcomeAct extends Activity{
	
	ViewPager vp;
	ArrayList<View> views=new ArrayList<View>();
	PagerAdapter adapter=new PagerAdapter() {
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}
		
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}
		
	};
	Button startBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		vp=(ViewPager)findViewById(R.id.vp);
		views.add(LayoutInflater.from(this).inflate(R.layout.welcome1, null));
		views.add(LayoutInflater.from(this).inflate(R.layout.welcome2, null));
		View view3=LayoutInflater.from(this).inflate(R.layout.welcome3, null);
		startBtn=(Button) view3.findViewById(R.id.startBtn);
		startBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				start();
			}
		});
		
		views.add(view3);
		vp.setAdapter(adapter);
		
	}
	private void start() {
		writeSp();
		startActivity(new Intent(this,MainFragAct.class));
		finish();
	}
	private void writeSp() {
		SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putBoolean("isFirst", true);
		editor.commit();
		
	}
}
