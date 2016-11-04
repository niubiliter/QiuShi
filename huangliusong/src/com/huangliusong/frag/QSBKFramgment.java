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
	int tabCount;//����������ǩ����
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
		
		//��ͼ��ʼ��
		initView(view);
		
		//tab��ʼ��
		initTab();
		
		//viewpager��ʼ��
		initViewPager();
		
		
		return view;
	}

	private void initViewPager() {
		//1.��������Դ,һ��6��
		for(int i=0;i<tabCount;i++){  
			QiuShiFragment fragment=new QiuShiFragment();
			
			//������ֲ�ͬ��tab����?
			Bundle bundle=new Bundle();
			bundle.putString("url",urls[i]);
			fragment.setArguments(bundle);//Ϊfragment�������ò���������
			fragmentList.add(fragment);
		}
		//2.����������
		
		adapter=new QiuShiFragmentAdapter(getFragmentManager(),fragmentList);
		
		//3.��������
		mViewPager.setAdapter(adapter);
		
		//4.���û����¼�����
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
			tabTitles[i].setTag(new Integer(i));//����tab��ǩ������ֵ
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
