package com.huangliusong.frag;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

import com.huangliusong.adapter.SpecialBaseAdapter;
import com.huangliusong.asynctask.DownLoadTask;
import com.huangliusong.asynctask.DownLoadTask.CallBackListener;
import com.huangliusong.bean.SpecialInfo;
import com.huangliusong.constant.Constant;
import com.huangliusong.utils.JsonUtils;
import com.robin.myapp.R;

public class QiuShiFragment extends Fragment {
	private static final String TAG = "robin debug";
	private Button btn;
	private ImageView img;
	ProgressDialog dialog;
	private int currentPage = 1;
	private ListView listView;
	private SpecialBaseAdapter adapter;
	private List<SpecialInfo> specialInfoList = new ArrayList<SpecialInfo>();
	private boolean isButtom;// 手指是否滑到ListView的底部做上拉操作
	private LinearLayout mLinearLayout;
	private boolean loadOk = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_qiushi, container, false);
		// 1. 初始化activity的视图
		initView(view);

		// 2. 从fragment中取出参数包
		Bundle bundle = getArguments();
		String url = bundle.getString("url");

		switch (url) {
		case Constant.SPEICAL_URL:
			downLoadSpecial();
			break;
		case Constant.VIDEO_URL:
			downLoasVideo();
			break;
		case Constant.TEXT_URL:
			downLoadText();
			break;
		case Constant.PICTURE_URL:
			downLoadPicture();
			break;
		case Constant.ESSENCE_URL:
			downLoadESSENCE();
			break;
		case Constant.NEW_URL:
			downLoadNew();
			break;
		default:
			break;
		}

		return view;
	}

	private void downLoadNew() {
		// ArrayList<String> list = new ArrayList<String>();
		// for (int i = 0; i < 7; i++) {
		// list.add("最新测试的数据...");
		// }
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, list);
		// listView.setAdapter(adapter);

		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("当前已加载" + currentPage + "页");
				loadOk = true;

			}
		}).execute(Constant.NEW_URL + currentPage);
		// 2.创建适配器对象
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView绑定适配器
		listView.setAdapter(adapter);

		// 5.设置listview滑动监听器
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
					if (isButtom) { // 为true说明手指滑到ListView底部 上拉加载
						mLinearLayout.setVisibility(View.VISIBLE);
						loadNextPage();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
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
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});
	}

	private void downLoadESSENCE() {
		// ArrayList<String> list = new ArrayList<String>();
		// for (int i = 0; i < 7; i++) {
		// list.add("精华测试的数据...");
		// }
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, list);
		// listView.setAdapter(adapter);
		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("当前已加载" + currentPage + "页");
				loadOk = true;

			}
		}).execute(Constant.ESSENCE_URL + currentPage);
		// 2.创建适配器对象
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView绑定适配器
		listView.setAdapter(adapter);

		// 5.设置listview滑动监听器
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
					if (isButtom) { // 为true说明手指滑到ListView底部 上拉加载
						mLinearLayout.setVisibility(View.VISIBLE);
						loadNextPage();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
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
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});
	}

	private void downLoadPicture() {
		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("当前已加载" + currentPage + "页");
				loadOk = true;

			}
		}).execute(Constant.PICTURE_URL + currentPage);
		// 2.创建适配器对象
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView绑定适配器
		listView.setAdapter(adapter);

		// 5.设置listview滑动监听器
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
					if (isButtom) { // 为true说明手指滑到ListView底部 上拉加载
						mLinearLayout.setVisibility(View.VISIBLE);
						loadNextPage();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
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
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});
	}

	private void downLoadText() {

		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("当前已加载" + currentPage + "页");
				loadOk = true;

			}
		}).execute(Constant.TEXT_URL + currentPage);
		// 2.创建适配器对象
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView绑定适配器
		listView.setAdapter(adapter);

		// 5.设置listview滑动监听器
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
					if (isButtom) { // 为true说明手指滑到ListView底部 上拉加载
						mLinearLayout.setVisibility(View.VISIBLE);
						loadNextPage();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
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
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});

	}

	private void downLoasVideo() {
		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("当前已加载" + currentPage + "页");
				loadOk = true;

			}
		}).execute(Constant.VIDEO_URL + currentPage);
		// 2.创建适配器对象
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView绑定适配器
		listView.setAdapter(adapter);

		// 5.设置listview滑动监听器
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
					if (isButtom) { // 为true说明手指滑到ListView底部 上拉加载
						mLinearLayout.setVisibility(View.VISIBLE);
						loadNextPage();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
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
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});

	}

	// 定义实现 专享页面下载任务的方法
	private void downLoadSpecial() {

		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("当前已加载" + currentPage + "页");
				loadOk = true;

			}
		}).execute(Constant.SPEICAL_URL + currentPage);
		// 2.创建适配器对象
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView绑定适配器
		listView.setAdapter(adapter);

		// 5.设置listview滑动监听器
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
					if (isButtom) { // 为true说明手指滑到ListView底部 上拉加载
						mLinearLayout.setVisibility(View.VISIBLE);
						loadNextPage();
					}
					break;
				case SCROLL_STATE_TOUCH_SCROLL:
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
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});

	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		listView = (ListView) view.findViewById(R.id.listView1);
		mLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);// 加载的视图对象
		currentPage = 1;

		mLinearLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.e(TAG, "currentPage:" + currentPage);
				if (loadOk == true) {
					loadNextPage();
				}
			}
		});
	}

	protected void loadNextPage() {
		currentPage++;
		loadOk = false;
		// 启动加载下一页数据的异步任务
		new DownLoadTask(getActivity(), new CallBackListener() {

			@Override
			public void onResponse(String jsonString) {
				// json解析
				// 3.获取数据源
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "一共获得的item数据有:"+list.size());
				specialInfoList.addAll(list);// list容器中元素放到specialInfoList容器中
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();// 更新UI
				getActivity().setTitle("当前已加载" + currentPage + "页");
				mLinearLayout.setVisibility(View.GONE);
				loadOk = true;
			}
		}).execute(Constant.SPEICAL_URL + currentPage);
	}
}
