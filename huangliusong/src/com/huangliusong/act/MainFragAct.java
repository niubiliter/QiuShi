package com.huangliusong.act;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangliusong.frag.FindFragment;
import com.huangliusong.frag.MineFragment;
import com.huangliusong.frag.QSBKFramgment;
import com.huangliusong.frag.QiuShiFragment;
import com.huangliusong.frag.QiuYouFragment;
import com.huangliusong.frag.SmallMesageFragment;
import com.robin.myapp.R;

public class MainFragAct extends FragmentActivity {
	
	//tab中textViewId
	private int[] textViewIds=new int[]{
			R.id.textView_qiushi,R.id.textView_qiuyou,
			R.id.textView_find,R.id.textView_smallpager,
			R.id.textView_mine
	};
	//tab中ImageViewId
	private int[] imageViewIds=new int[]{
			R.id.imageView_qiushi,R.id.imageView_qiuyou,
			R.id.imageView_find,R.id.imageView_smallpager,
			R.id.imageView_mine
	};
	private int currentPosition=0;
	
	private List<Fragment> fragmentList=new ArrayList<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragact_main);
		
		currentPosition=0;
		// 设置选中状态
		((ImageView)findViewById(imageViewIds[currentPosition])).setSelected(true);
		((TextView)findViewById(textViewIds[currentPosition])).setTextColor(Color.rgb(
				255, 160, 65));
		//把要用到的Fragment对象全部创建出来放到列表容器中		
		createFragment();
		
		//默认Fragment放到碎片容器中 我们用的是v4包的Fragment系列组件要统一用v4的API
		
		FragmentTransaction transaction=this.getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.frameLayout_container,fragmentList.get(0));
		transaction.commit();
		
	}

	private void createFragment() {
//		QiuShiFragment qiuShiFragment=new QiuShiFragment();
//		fragmentList.add(qiuShiFragment);
		QSBKFramgment qsbkFramgment=new QSBKFramgment();
		fragmentList.add(qsbkFramgment);
		
		QiuYouFragment qiuYouFragment=new QiuYouFragment();
		fragmentList.add(qiuYouFragment);
		FindFragment findFragment=new FindFragment();
		fragmentList.add(findFragment);
		SmallMesageFragment smallMesageFragment=new SmallMesageFragment();
		fragmentList.add(smallMesageFragment);
		MineFragment mineFragment=new MineFragment();
		fragmentList.add(mineFragment);
	}

	public void clickButton(View view) {
		switch (view.getId()) {
		case R.id.linearLayout1:
			setFragment(0);			
			setTabState(0);//选中tab1
			currentPosition=0;
			break;
		case R.id.linearLayout2:
			setFragment(1);
			setTabState(1);
			currentPosition=1;
			break;
		case R.id.linearLayout3:
			setFragment(2);
			setTabState(2);
			currentPosition=2;
			break;
		case R.id.linearLayout4:
			setFragment(3);
			setTabState(3);
			currentPosition=3;
			break;
		case R.id.linearLayout5:
			setFragment(4);
			setTabState(4);
			currentPosition=4;
			break;

		default:
			break;
		}

	}

	private void setFragment(int index) {
		FragmentManager manager=this.getSupportFragmentManager();
		FragmentTransaction transaction=manager.beginTransaction();
		//判断当前选中的碎片对象是否已经添加到FragmentManager对象中了
		if(!manager.getFragments().contains(fragmentList.get(index))){			
			transaction.add(R.id.frameLayout_container,fragmentList.get(index));			
		}
		//隐藏原来的Fragment对象
		transaction.hide(fragmentList.get(currentPosition));
		//显示选中
		transaction.show(fragmentList.get(index));		
		transaction.commit();
	}

	private void setTabState(int position) {
		// 设置选中状态
		((ImageView)findViewById(imageViewIds[position])).setSelected(true);
		((TextView)findViewById(textViewIds[position])).setTextColor(Color.rgb(
				255, 160, 65));
		//设置原来没选中状态
		((ImageView)findViewById(imageViewIds[currentPosition])).setSelected(false);
		((TextView)findViewById(textViewIds[currentPosition])).setTextColor(Color.rgb(
				0,0,0));
	}

}
