package com.huangliusong.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

public class SDCardHelper {
	private Context context; 
	public SDCardHelper(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	

	public boolean saveByteToSDCardCacheDir(String fileName, byte[] data) {
		FileOutputStream fos=null;
		if(isExternStorageMounted()){
//			String sdcard_rootPath=getExternalStoragePath();
			File cache_dir=context.getExternalCacheDir();
			File file=new File(cache_dir,fileName);
			try {
				fos=new FileOutputStream(file);
				fos.write(data);
				fos.flush();
				return true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}
		return false;
	}

	private String getExternalStoragePath() {
		if(isExternStorageMounted()){
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return null;
	}

	private boolean isExternStorageMounted() {
		// ºÏ≤Èsdø® «∑Òπ“‘ÿ
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public byte[] readFromSdCard(String path) {
		FileInputStream fis=null;
		ByteArrayOutputStream bos=null;
		File file=new File(path);
		
		try {
			fis=new FileInputStream(file);
			bos=new ByteArrayOutputStream();
			byte[] buffer=new byte[1024];
			int num=-1;
			while((num=fis.read(buffer))!=-1){
				bos.write(buffer,0,num);
				bos.flush();
			}
			return bos.toByteArray();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
