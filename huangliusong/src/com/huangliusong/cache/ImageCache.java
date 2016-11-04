package com.huangliusong.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


//单例模式  只能创建一个ImageCache的实例
public class ImageCache {
	//图片缓存用  url:bitmap来做
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
			//计算每张图片占用的空间大小,给系统用的
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				// TODO Auto-generated method stub
				return bitmap.getRowBytes()*bitmap.getHeight();
			}
			
		};
	}
	
	//提供给外部操作LRUcache的方法
	public void putBitmap(String key,Bitmap value){
		mLruCache.put(key, value);
	}
	public Bitmap getBitmap(String key){
		return mLruCache.get(key);
	}
}
