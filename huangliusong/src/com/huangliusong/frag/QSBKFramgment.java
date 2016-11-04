package com.huangliusong.frag;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangliusong.adapter.QiuShiFragmentAdapter;
import com.huangliusong.constant.Constant;
import com.robin.myapp.R;

public class QSBKFramgment extends Fragment{
	ViewPager mViewPager;
	LinearLayout mLinearLayout;
	int tabCount;//顶部导航标签数量
	TextView[] tabTitles;
	List<Fragment> fragmentList=new ArrayList<Fragment>();
	QiuShiFragmentAdapter adapter;
	String[] urls={
			Constant.SPEICAL_URL,Constant.VIDEO_URL,Constant.TEXT_URL,Constant.PICTURE_URL,
			Constant.ESSENCE_URL,Constant.NEW_URL
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_qiushis, container, false);
		
		//视图初始化
		initView(view);
		
		//tab初始化
		initTab();
		
		//viewpager初始化
		initViewPager();
		
		
		return view;
	}

	private void initViewPager() {
		//1.创建数据源,一共6个
		for(int i=0;i<tabCount;i++){  
			QiuShiFragment fragment=new QiuShiFragment();
			
			//如何区分不同的tab内容?
			Bundle bundle=new Bundle();
			bundle.putString("url",urls[i]);
			fragment.setArguments(bundle);//为fragment对象设置参数包对象
			fragmentList.add(fragment);
		}
		//2.创建适配器
		
		adapter=new QiuShiFragmentAdapter(getFragmentManager(),fragmentList);
		
		//3.绑定适配器
		mViewPager.setAdapter(adapter);
		
		//4.设置滑动事件处理
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pageNo) {
				for(int i=0;i<tabCount;i++){
					tabTitles[i].setSelected(false);
					tabTitles[i].setTextColor(Color.rgb(0, 0, 0));
					tabTitles[i].setEnabled(true);
				}
				tabTitles[pageNo].setSelected(true);
				tabTitles[pageNo].setTextColor(Color.rgb(255, 160, 65));
				tabTitles[pageNo].setEnabled(false);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void initTab() {
		tabTitles=new TextView[tabCount];
		for(int i=0;i<tabCount;i++){
			TextView textView=(TextView) mLinearLayout.getChildAt(i);
			tabTitles[i]=textView;
			tabTitles[i].setSelected(false);
			tabTitles[i].setTextColor(Color.rgb(0, 0, 0));
			tabTitles[i].setEnabled(true);
			tabTitles[i].setTag(new Integer(i));//设置tab标签的索引值
			tabTitles[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mViewPager.setCurrentItem((Integer)v.getTag());
				}
			});
			
			tabTitles[0].setSelected(true);
			tabTitles[0].setTextColor(Color.rgb(255, 160, 65));
			tabTitles[0].setEnabled(false);
		}
		
	}

	private void initView(View view) {
		mViewPager=(ViewPager)view.findViewById(R.id.viewPager);
		mLinearLayout=(LinearLayout)view.findViewById(R.id.linearLayout);
		tabCount=mLinearLayout.getChildCount();
	}

	
}
