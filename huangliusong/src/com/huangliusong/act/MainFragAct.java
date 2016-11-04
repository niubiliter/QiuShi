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
	
	//tab��textViewId
	private int[] textViewIds=new int[]{
			R.id.textView_qiushi,R.id.textView_qiuyou,
			R.id.textView_find,R.id.textView_smallpager,
			R.id.textView_mine
	};
	//tab��ImageViewId
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
		// ����ѡ��״̬
		((ImageView)findViewById(imageViewIds[currentPosition])).setSelected(true);
		((TextView)findViewById(textViewIds[currentPosition])).setTextColor(Color.rgb(
				255, 160, 65));
		//��Ҫ�õ���Fragment����ȫ�����������ŵ��б�������		
		createFragment();
		
		//Ĭ��Fragment�ŵ���Ƭ������ �����õ���v4����Fragmentϵ�����Ҫͳһ��v4��API
		
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
			setTabState(0);//ѡ��tab1
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
		//�жϵ�ǰѡ�е���Ƭ�����Ƿ��Ѿ���ӵ�FragmentManager��������
		if(!manager.getFragments().contains(fragmentList.get(index))){			
			transaction.add(R.id.frameLayout_container,fragmentList.get(index));			
		}
		//����ԭ����Fragment����
		transaction.hide(fragmentList.get(currentPosition));
		//��ʾѡ��
		transaction.show(fragmentList.get(index));		
		transaction.commit();
	}

	private void setTabState(int position) {
		// ����ѡ��״̬
		((ImageView)findViewById(imageViewIds[position])).setSelected(true);
		((TextView)findViewById(textViewIds[position])).setTextColor(Color.rgb(
				255, 160, 65));
		//����ԭ��ûѡ��״̬
		((ImageView)findViewById(imageViewIds[currentPosition])).setSelected(false);
		((TextView)findViewById(textViewIds[currentPosition])).setTextColor(Color.rgb(
				0,0,0));
	}

}
