package com.huangliusong.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


//����ģʽ  ֻ�ܴ���һ��ImageCache��ʵ��
public class ImageCache {
	//ͼƬ������  url:bitmap����
	private LruCache<String, Bitmap> mLruCache;
	private static ImageCache mImageCache;
	
	public static ImageCache getInstance(){
		if(mImageCache==null){
			mImageCache=new ImageCache();
		}
		return mImageCache;
		
	}
	private ImageCache() {
		int memorySize=(int)Runtime.getRuntime().maxMemory();
		int maxSize=memorySize/8;
		mLruCache=new LruCache<String,Bitmap>(maxSize){
			//����ÿ��ͼƬռ�õĿռ��С,��ϵͳ�õ�
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				return bitmap.getRowBytes()*bitmap.getHeight();
			}
			
		};
	}
	
	//�ṩ���ⲿ����LRUcache�ķ���
	public void putBitmap(String key,Bitmap value){
		mLruCache.put(key, value);
	}
	public Bitmap getBitmap(String key){
		return mLruCache.get(key);
	}
}
