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
	private boolean isButtom;//手指是否滑到ListView的底部做上拉操作
	private LinearLayout mLinearLayout;
	private boolean loadOk=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//1. 初始化activity的视图
		initView();
		
		
		//创建一个异步任务类 实现网络加载json数据
		//2.创建适配器对象
		adapter=new SpecialBaseAdapter(this, specialInfoList);
		Log.e(TAG, "currentPage:"+currentPage);
		new DownLoadTask(this, new CallBackListener() {
			
			@Override
			public void onResponse(String jsonString) {
				//json解析
				//3.获取数据源
				List<SpecialInfo> list=JsonUtils.josnStringToList(jsonString);
//				Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);//list容器中元素放到specialInfoList容器中		
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				setTitle("当前已加载"+currentPage+"页");
				loadOk=true;
				
			}
		}).execute(Constant.SPEICAL_URL+currentPage);
		
		//4. ListView绑定适配器
		listView.setAdapter(adapter);
		
		//5.设置listview滑动监听器
		listView.setOnScrollListener(new OnScrollListener() {
			
			
			
			
			@Override
			/**
			 * scrollState 滚动状态
			 * SCROLL_STATE_FLING: listview正在滚动,但手指已经脱离屏幕
			 * SCROLL_STATE_IDLE: listview处空闲状态 
			 * SCROLL_STATE_TOUCH_SCROLL: 手指正在拖动listview 手指没有脱离屏幕
			 */
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				switch (scrollState) {
				case SCROLL_STATE_IDLE:		
					if(isButtom){ //为true说明手指滑到ListView底部 上拉加载
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
			 * firstVisibleItem 第一个可见的item的行索引
			 * visibleItemCount 可见的item的数量
			 *  totalItemCount 所有的item的数量
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
		mLinearLayout=(LinearLayout)findViewById(R.id.linearLayout);//加载的视图对象
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
		//启动加载下一页数据的异步任务	
		new DownLoadTask(this, new CallBackListener() {
			
			@Override
			public void onResponse(String jsonString) {
				//json解析
				//3.获取数据源
				List<SpecialInfo> list=JsonUtils.josnStringToList(jsonString);
//				Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);//list容器中元素放到specialInfoList容器中		
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();//更新UI
				setTitle("当前已加载"+currentPage+"页");				
				mLinearLayout.setVisibility(View.GONE);
				loadOk=true;
			}
		}).execute(Constant.SPEICAL_URL+currentPage);
		
	}
	
	
	
	
	
	
	
	

}
