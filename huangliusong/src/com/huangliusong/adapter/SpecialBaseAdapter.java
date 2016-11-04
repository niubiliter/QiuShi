package com.huangliusong.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huangliusong.asynctask.LoadImageTask;
import com.huangliusong.asynctask.LoadImgTask;
import com.huangliusong.asynctask.LoadImageTask.CallBackListener;
import com.huangliusong.bean.SpecialInfo;
import com.huangliusong.cache.ImageCache;
import com.robin.myapp.R;

public class SpecialBaseAdapter extends BaseAdapter {

	private static final String TAG = "robin debug";
	private Context context;
	private List<SpecialInfo> list = new ArrayList<SpecialInfo>();
	private ImageCache mImageCache;
	private LoadImgTask mLoadImgTask;

	public SpecialBaseAdapter(Context context, List<SpecialInfo> list) {
		this.context = context;
		this.list = list;
		mImageCache = ImageCache.getInstance();// ��ȡlrucache�ĵ�������
		mLoadImgTask=new LoadImgTask(context);//�������첽��������װͼƬ�������洦��
	}

	public SpecialBaseAdapter() {
		// TODO Auto-generated constructor stub
	}

	public void setList(List<SpecialInfo> list) {
		this.list = list;
	}

	public List<SpecialInfo> getList() {
		return list;
	}

	@Override
	// ��ȡitem�������
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	// �����������ҵ� ����������Ԫ��SpecialInfo����
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	// ��ȡ��Ӧitem��������
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	// ��������ÿһ��item������ͼ��ʾ�����ݵ�
	public View getView(int position, View convertView, ViewGroup parent) {
		//Log.e(TAG, "Call getView() at" + position);
		View view;// �����������ͼ�ļ�ӳ��view����
		final ViewHolder holder;
		if (convertView == null) { // convertView ���浱ǰitem������ͼ����
			holder = new ViewHolder();
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.listview_item, null);// ���ز����ļ�
																			// ����Դ
			holder.imageView_header = (ImageView) convertView
					.findViewById(R.id.imageView1);// findView����Դ
			holder.textView_name = (TextView) convertView
					.findViewById(R.id.textView1);
			holder.textView_content = (TextView) convertView
					.findViewById(R.id.textView2);
			holder.imageView_content = (ImageView) convertView
					.findViewById(R.id.imageView4);
			convertView.setTag(holder);// ����װui�ؼ��Ķ������õ�����ͼ�����tag������
		} else {
			holder = (ViewHolder) convertView.getTag();// �м��ز������
		}

		// չʾ����
		// 1.չʾͷ��:
		final String headImageUrl = list.get(position).getUser_icon();

		// ��ÿ��imageView_header��Ҫ����ͷ��ͼƬ��item����(�������)
		holder.imageView_header.setTag(headImageUrl);
		// ����Ĭ�ϵ�ͷ��,������û�����ʱ��ʾ�õ�
		holder.imageView_header
				.setImageResource(R.drawable.default_anonymous_users_avatar);

		if ("".equals(headImageUrl)) {
			// ��ʱ�����û�ʹ��ͷ��
			holder.imageView_header
					.setImageResource(R.drawable.default_anonymous_users_avatar_night);
		} else {
			// ��Ҫ����ͷ��
//			// 1.�ж�lrucache�������Ƿ���ͼƬ,�����ֱ�Ӵӻ����л�ȡ,������������
//			Bitmap bitmap = mImageCache.getBitmap(headImageUrl);
//			if (bitmap != null) {
//				holder.imageView_header.setImageBitmap(bitmap);
//
//			} else { // lrucache û������
//				new LoadImageTask(context, new CallBackListener() {
//
//					@Override
//					public void onResponse(Bitmap bitmap, String url) {
//						if (bitmap != null
//								&& holder.imageView_header.getTag().equals(url)) {
//							holder.imageView_header.setImageBitmap(bitmap);
//							// ���浽lrucache������
//							mImageCache.putBitmap(headImageUrl, bitmap);
//						}
//
//					}
//				}).execute(headImageUrl);
//
//			}
			//����ͷ��ͼƬ�Ĵ���
			Bitmap bitmap=mLoadImgTask.loadImage(holder.imageView_header,headImageUrl);
			if(bitmap!=null){
				holder.imageView_header.setImageBitmap(bitmap);
			}
			

		}

		// 2.չʾ�û���
		String userName = list.get(position).getUser_name();
		if ("".equals(userName)) {
			holder.textView_name.setText("�����û�");
		} else {
			holder.textView_name.setText(userName);
		}

		// 3.չʾ������Ϣ
		String content = list.get(position).getContent();
		holder.textView_content.setText(content);

		// 4.չʾ����ͼƬ
		final String contentUrl = list.get(position).getContent_image();
		if (!"".equals(contentUrl)) {
			holder.imageView_content.setVisibility(View.VISIBLE);

			// ��������ͼƬ��item
			// 1.�ж�lrucache�������Ƿ���ͼƬ,�����ֱ�Ӵӻ�����ȡ,���������������
//			Bitmap bitmap = mImageCache.getBitmap(contentUrl);
//			if (bitmap != null) {
//				holder.imageView_content.setImageBitmap(bitmap);
//			} else {
//				// 2.û��cacheû����������
//				new LoadImageTask(context, new CallBackListener() {
//
//					@Override
//					public void onResponse(Bitmap bitmap, String url) {
//						if (bitmap != null) {
//							holder.imageView_content.setImageBitmap(bitmap);
//							mImageCache.putBitmap(contentUrl, bitmap);
//						}
//
//					}
//				}).execute(contentUrl);
//			}
			//��������ͼƬ����
			Bitmap bitmap=mLoadImgTask.loadImage(holder.imageView_content, contentUrl);
			if(bitmap!=null){
				holder.imageView_content.setImageBitmap(bitmap);
			}
			

		} else {
			holder.imageView_content.setVisibility(View.GONE);// ����ͼƬ�ؼ�
		}

		return convertView;
	}

	class ViewHolder { // ��װ ����ͼ��UI�ؼ��� holder����
		public ImageView imageView_header;
		public TextView textView_name;
		public TextView textView_content;
		public ImageView imageView_content;

	}

}
