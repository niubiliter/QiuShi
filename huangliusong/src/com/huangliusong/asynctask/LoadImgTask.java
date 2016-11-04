package com.huangliusong.asynctask;

import com.huangliusong.cache.FileCache;
import com.huangliusong.cache.ImageCache;
import com.huangliusong.utils.HttpUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class LoadImgTask {
	private Context context;
	private ImageCache mImageCache;//内存缓存
	private FileCache mFileCache;//文件缓存

	public LoadImgTask(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mImageCache=ImageCache.getInstance();
		this.mFileCache=FileCache.getFileCache(context);
	}

	//获取图片
	public Bitmap loadImage(ImageView imageView, String imageUrl) {
		// 1.从内存缓存获取
		Bitmap bitmap=mImageCache.getBitmap(imageUrl);
		if(bitmap!=null){
			return bitmap;
		}
		//2.从文件缓存获取
		bitmap=mFileCache.getBitmapFileCache(imageUrl);
		if(bitmap!=null){
			mImageCache.putBitmap(imageUrl, bitmap);
			return bitmap;
		}
		//3.网络下载
		new DownloadImageTask(imageView).execute(imageUrl);
		return null;
	}
	
	//定义一个异步下载图片任务类
	public class DownloadImageTask extends AsyncTask<String, Void, byte[]>{
		private String imageUrl;
		private ImageView  imageView;
		public DownloadImageTask(ImageView imageView) {
			this.imageView=imageView;
		}
		@Override
		protected byte[] doInBackground(String... params) {
			imageUrl=params[0];
			return HttpUtils.loadDataForHttpURLConnection(imageUrl);
		}
		@Override
		protected void onPostExecute(byte[] result) {
			super.onPostExecute(result);
			if(result!=null){
				Bitmap bitmap=BitmapFactory.decodeByteArray(result, 0, result.length);
				if(imageUrl!=null&&bitmap!=null){
					//1.保存到内存缓存中
					mImageCache.putBitmap(imageUrl, bitmap);
					//2.保存到文件缓存中
					mFileCache.putBitmapToFileCache(imageUrl,result);
				}
				if(imageView.getTag()!=null&&imageView.getTag().equals(imageUrl)){
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}

}
