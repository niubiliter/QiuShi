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
	private boolean isButtom;// ��ָ�Ƿ񻬵�ListView�ĵײ�����������
	private LinearLayout mLinearLayout;
	private boolean loadOk = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_qiushi, container, false);
		// 1. ��ʼ��activity����ͼ
		initView(view);

		// 2. ��fragment��ȡ��������
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
		// list.add("���²��Ե�����...");
		// }
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, list);
		// listView.setAdapter(adapter);

		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				loadOk = true;

			}
		}).execute(Constant.NEW_URL + currentPage);
		// 2.��������������
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView��������
		listView.setAdapter(adapter);

		// 5.����listview����������
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
					if (isButtom) { // Ϊtrue˵����ָ����ListView�ײ� ��������
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
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
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
		// list.add("�������Ե�����...");
		// }
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(getActivity(),
		// android.R.layout.simple_list_item_1, list);
		// listView.setAdapter(adapter);
		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				loadOk = true;

			}
		}).execute(Constant.ESSENCE_URL + currentPage);
		// 2.��������������
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView��������
		listView.setAdapter(adapter);

		// 5.����listview����������
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
					if (isButtom) { // Ϊtrue˵����ָ����ListView�ײ� ��������
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
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
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
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				loadOk = true;

			}
		}).execute(Constant.PICTURE_URL + currentPage);
		// 2.��������������
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView��������
		listView.setAdapter(adapter);

		// 5.����listview����������
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
					if (isButtom) { // Ϊtrue˵����ָ����ListView�ײ� ��������
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
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
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
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				loadOk = true;

			}
		}).execute(Constant.TEXT_URL + currentPage);
		// 2.��������������
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView��������
		listView.setAdapter(adapter);

		// 5.����listview����������
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
					if (isButtom) { // Ϊtrue˵����ָ����ListView�ײ� ��������
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
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
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
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				loadOk = true;

			}
		}).execute(Constant.VIDEO_URL + currentPage);
		// 2.��������������
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView��������
		listView.setAdapter(adapter);

		// 5.����listview����������
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
					if (isButtom) { // Ϊtrue˵����ָ����ListView�ײ� ��������
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
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
			 */
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				isButtom = (firstVisibleItem + visibleItemCount == totalItemCount);

			}
		});

	}

	// ����ʵ�� ר��ҳ����������ķ���
	private void downLoadSpecial() {

		Log.e(TAG, "currentPage:" + currentPage);

		new DownLoadTask(getActivity(), new CallBackListener() {
			@Override
			public void onResponse(String jsonString) {
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				loadOk = true;

			}
		}).execute(Constant.SPEICAL_URL + currentPage);
		// 2.��������������
		adapter = new SpecialBaseAdapter(getActivity(), specialInfoList);
		// 4. ListView��������
		listView.setAdapter(adapter);

		// 5.����listview����������
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
					if (isButtom) { // Ϊtrue˵����ָ����ListView�ײ� ��������
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
			 * firstVisibleItem ��һ���ɼ���item��������
			 * visibleItemCount �ɼ���item������
			 *  totalItemCount ���е�item������
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
		mLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);// ���ص���ͼ����
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
		// ����������һҳ���ݵ��첽����
		new DownLoadTask(getActivity(), new CallBackListener() {

			@Override
			public void onResponse(String jsonString) {
				// json����
				// 3.��ȡ����Դ
				List<SpecialInfo> list = JsonUtils.josnStringToList(jsonString);
				// Log.e(TAG, "һ����õ�item������:"+list.size());
				specialInfoList.addAll(list);// list������Ԫ�طŵ�specialInfoList������
				adapter.setList(specialInfoList);
				adapter.notifyDataSetChanged();// ����UI
				getActivity().setTitle("��ǰ�Ѽ���" + currentPage + "ҳ");
				mLinearLayout.setVisibility(View.GONE);
				loadOk = true;
			}
		}).execute(Constant.SPEICAL_URL + currentPage);
	}
}
