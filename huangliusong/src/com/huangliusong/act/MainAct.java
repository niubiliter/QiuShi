package com.huangliusong.act;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.huangliusong.adapter.SpecialBaseAdapter;
import com.huangliusong.asynctask.DownLoadTask;
import com.huangliusong.asynctask.DownLoadTask.CallBackListener;
import com.huangliusong.bean.SpecialInfo;
import com.huangliusong.constant.Constant;
import com.huangliusong.utils.JsonUtils;
import com.robin.myapp.R;

public class MainAct extends Activity{
	
	private static final String TAG = "robin debug";
	private Button btn;
	private ImageView img;
	ProgressDialog dialog;
	private int currentPage=1;
	private ListView listView;
	private SpecialBaseAdapter adapter;
	private List<SpecialInfo> specialInfoList=new ArrayList<SpecialInfo>();
	private boolean isButtom;//��ָ�Ƿ񻬵�ListView�ĵײ�����������
	private LinearLayout mLinearLayout;
	private boolean loadOk=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//1. ��ʼ��activity����ͼ
		initView();
		
		
		//����һ���첽������ ʵ���������json����
		//2.��������������
		adapter=new SpecialBaseAdapter(this, specialInfoList);
		Log.e(TAG, "currentPage:"+currentPage);
		new DownLoadTask(this, new CallBackListener() {
			
			@Override
			public void onResponse(String jsonString) {
				//json����
				//3.��ȡ����Դ
				List<SpecialInfo> list=JsonUtils.josnStringToList(jsonString);
//				Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);//list������Ԫ�طŵ�specialInfoList������		
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				setTitle("��ǰ�Ѽ���"+currentPage+"ҳ");
				loadOk=true;
				
			}
		}).execute(Constant.SPEICAL_URL+currentPage);
		
		//4. ListView��������
		listView.setAdapter(adapter);
		
		//5.����listview����������
		listView.setOnScrollListener(new OnScrollListener() {
			
			
			
			
			@Override
			/**
			 * scrollState ����״̬
			 * SCROLL_STATE_FLING: listview���ڹ���,����ָ�Ѿ�������Ļ
			 * SCROLL_STATE_IDLE: listview������״̬ 
			 * SCROLL_STATE_TOUCH_SCROLL: ��ָ�����϶�listview ��ָû��������Ļ
			 */
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case SCROLL_STATE_IDLE:		
					if(isButtom){ //Ϊtrue˵����ָ����ListView�ײ� ��������
						mLinearLayout.setVisibility(View.VISIBLE);
					}
					break;
				case  SCROLL_STATE_TOUCH_SCROLL:
					break;
				case SCROLL_STATE_FLING:
					break;
				default:
					break;
				}
				
			}
			
			@Override
			/**
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
			 */
			
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				isButtom=(firstVisibleItem+visibleItemCount==totalItemCount);
				
			}
		});
		
		
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		listView=(ListView)findViewById(R.id.listView1);
		mLinearLayout=(LinearLayout)findViewById(R.id.linearLayout);//���ص���ͼ����
		currentPage=1;
		
		mLinearLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Log.e(TAG, "currentPage:"+currentPage);
				if(loadOk==true){
					loadNextPage();
					
				}
				
			}

			
		});
	}
	
	private void loadNextPage() {
		// TODO Auto-generated method stub
		currentPage++;
		loadOk=false;
		//����������һҳ���ݵ��첽����	
		new DownLoadTask(this, new CallBackListener() {
			
			@Override
			public void onResponse(String jsonString) {
				//json����
				//3.��ȡ����Դ
				List<SpecialInfo> list=JsonUtils.josnStringToList(jsonString);
//				Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);//list������Ԫ�طŵ�specialInfoList������		
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();//����UI
				setTitle("��ǰ�Ѽ���"+currentPage+"ҳ");				
				mLinearLayout.setVisibility(View.GONE);
				loadOk=true;
			}
		}).execute(Constant.SPEICAL_URL+currentPage);
		
	}
	
	
	
	
	
	
	
	

}
