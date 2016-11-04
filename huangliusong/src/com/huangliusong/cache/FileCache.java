package com.huangliusong.cache;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.huangliusong.utils.SDCardHelper;

public class FileCache {
	private static final String TAG = "robin debug";
	public SDCardHelper helper;
	public static FileCache fileCache;
	private File cache_dir;
	
	private FileCache(Context context) {
		// TODO Auto-generated constructor stub
		helper=new SDCardHelper(context);
		cache_dir=context.getExternalCacheDir();//sd���Ļ���Ŀ¼
		//sdcard������ /Android/Ӧ�ð���/cacheĿ¼ 
		Log.i(TAG, cache_dir.getPath());
		
		
	}
	
	//����ģʽ
	public static FileCache getFileCache(Context context){
		if(fileCache==null){
			fileCache=new FileCache(context);
		}
		return fileCache;
		
	}
	
	
	

	public Bitmap getBitmapFileCache(String imageUrl) {
		File file=new File(cache_dir,getImageName(imageUrl));
		if(file.exists()){
			byte[] data=helper.readFromSdCard(file.getPath());
		}
		return null;
	}

	public void putBitmapToFileCache(String imageUrl, byte[] data) {
		helper.saveByteToSDCardCacheDir(getImageName(imageUrl),data);
		
	}
//http://pic.qiushibaike.com/system/pictures/11356/113563076/small/app113563076.jpg
//���app113563076.jpg

	private String getImageName(String imageUrl) {
		int lastIndex=imageUrl.lastIndexOf("/");
		return imageUrl.substring(lastIndex+1);
	}

}
