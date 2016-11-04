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
		mImageCache = ImageCache.getInstance();// 获取lrucache的单例对象
		mLoadImgTask=new LoadImgTask(context);//用来简化异步操作并封装图片三级缓存处理
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
	// 获取item项的数量
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	// 根据行索引找到 对象容器中元素SpecialInfo对象
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	// 获取对应item的行索引
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	// 用来绘制每一个item的行视图显示的内容的
	public View getView(int position, View convertView, ViewGroup parent) {
		//Log.e(TAG, "Call getView() at" + position);
		View view;// 代表加载行视图文件映射view对象
		final ViewHolder holder;
		if (convertView == null) { // convertView 缓存当前item的行视图对象
			holder = new ViewHolder();
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.listview_item, null);// 加载布局文件
																			// 耗资源
			holder.imageView_header = (ImageView) convertView
					.findViewById(R.id.imageView1);// findView耗资源
			holder.textView_name = (TextView) convertView
					.findViewById(R.id.textView1);
			holder.textView_content = (TextView) convertView
					.findViewById(R.id.textView2);
			holder.imageView_content = (ImageView) convertView
					.findViewById(R.id.imageView4);
			convertView.setTag(holder);// 将封装ui控件的对象设置到行视图对象的tag容器中
		} else {
			holder = (ViewHolder) convertView.getTag();// 有加载并缓存过
		}

		// 展示数据
		// 1.展示头像:
		final String headImageUrl = list.get(position).getUser_icon();

		// 给每个imageView_header需要加载头像图片到item背包(做个标记)
		holder.imageView_header.setTag(headImageUrl);
		// 设置默认的头像,当加载没有完成时显示用的
		holder.imageView_header
				.setImageResource(R.drawable.default_anonymous_users_avatar);

		if ("".equals(headImageUrl)) {
			// 当时匿名用户使用头像
			holder.imageView_header
					.setImageResource(R.drawable.default_anonymous_users_avatar_night);
		} else {
			// 需要加载头像
//			// 1.判断lrucache缓存中是否有图片,如果有直接从缓存中获取,否则网络下载
//			Bitmap bitmap = mImageCache.getBitmap(headImageUrl);
//			if (bitmap != null) {
//				holder.imageView_header.setImageBitmap(bitmap);
//
//			} else { // lrucache 没用命中
//				new LoadImageTask(context, new CallBackListener() {
//
//					@Override
//					public void onResponse(Bitmap bitmap, String url) {
//						if (bitmap != null
//								&& holder.imageView_header.getTag().equals(url)) {
//							holder.imageView_header.setImageBitmap(bitmap);
//							// 保存到lrucache缓存中
//							mImageCache.putBitmap(headImageUrl, bitmap);
//						}
//
//					}
//				}).execute(headImageUrl);
//
//			}
			//加载头像图片的处理
			Bitmap bitmap=mLoadImgTask.loadImage(holder.imageView_header,headImageUrl);
			if(bitmap!=null){
				holder.imageView_header.setImageBitmap(bitmap);
			}
			

		}

		// 2.展示用户名
		String userName = list.get(position).getUser_name();
		if ("".equals(userName)) {
			holder.textView_name.setText("匿名用户");
		} else {
			holder.textView_name.setText(userName);
		}

		// 3.展示内容信息
		String content = list.get(position).getContent();
		holder.textView_content.setText(content);

		// 4.展示内容图片
		final String contentUrl = list.get(position).getContent_image();
		if (!"".equals(contentUrl)) {
			holder.imageView_content.setVisibility(View.VISIBLE);

			// 加载内容图片到item
			// 1.判断lrucache缓存中是否有图片,如果用直接从缓存中取,否则从网络中下载
//			Bitmap bitmap = mImageCache.getBitmap(contentUrl);
//			if (bitmap != null) {
//				holder.imageView_content.setImageBitmap(bitmap);
//			} else {
//				// 2.没用cache没有命中下载
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
			//加载内容图片处理
			Bitmap bitmap=mLoadImgTask.loadImage(holder.imageView_content, contentUrl);
			if(bitmap!=null){
				holder.imageView_content.setImageBitmap(bitmap);
			}
			

		} else {
			holder.imageView_content.setVisibility(View.GONE);// 隐藏图片控件
		}

		return convertView;
	}

	class ViewHolder { // 封装 行视图中UI控件的 holder对象
		public ImageView imageView_header;
		public TextView textView_name;
		public TextView textView_content;
		public ImageView imageView_content;

	}

}
