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
	private ImageCache mImageCache;//�ڴ滺��
	private FileCache mFileCache;//�ļ�����

	public LoadImgTask(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mImageCache=ImageCache.getInstance();
		this.mFileCache=FileCache.getFileCache(context);
	}

	//��ȡͼƬ
	public Bitmap loadImage(ImageView imageView, String imageUrl) {
		// 1.���ڴ滺���ȡ
		Bitmap bitmap=mImageCache.getBitmap(imageUrl);
		if(bitmap!=null){
			return bitmap;
		}
		//2.���ļ������ȡ
		bitmap=mFileCache.getBitmapFileCache(imageUrl);
		if(bitmap!=null){
			mImageCache.putBitmap(imageUrl, bitmap);
			return bitmap;
		}
		//3.��������
		new DownloadImageTask(imageView).execute(imageUrl);
		return null;
	}
	
	//����һ���첽����ͼƬ������
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
					//1.���浽�ڴ滺����
					mImageCache.putBitmap(imageUrl, bitmap);
					//2.���浽�ļ�������
					mFileCache.putBitmapToFileCache(imageUrl,result);
				}
				if(imageView.getTag()!=null&&imageView.getTag().equals(imageUrl)){
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}

}
