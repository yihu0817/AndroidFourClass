package com.scxh.android1502.storage.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class MyFileActivity extends Activity {
	private static final String PCITRUE_FILE_NAME = "myPicture.png";
	private ImageView mIconImg,mShowImg;
	private TextView mDirTxt;
	private Button mWriterBtn,mReaderBtn,mSearchFileBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file2_layout);
		mIconImg = (ImageView) findViewById(R.id.file_imageView);
		mShowImg = (ImageView) findViewById(R.id.file_reader_imageView);
		mDirTxt = (TextView) findViewById(R.id.file_dir);
		mWriterBtn = (Button) findViewById(R.id.file_write_pic_btn);
		mReaderBtn = (Button) findViewById(R.id.file_reader_pic_btn);
		mSearchFileBtn = (Button) findViewById(R.id.file_search_btn);
		
		
		Drawable drawable = getResources().getDrawable(R.drawable.m3);
		mIconImg.setImageDrawable(drawable);

		final String fileDir = Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES).getPath();
		mDirTxt.setText(fileDir);

		mWriterBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isExternalStorageWritable()) {
					Toast.makeText(MyFileActivity.this, "外部存储不可用", Toast.LENGTH_SHORT).show();
					return;
				}
		
				File file = createExternalStoragePublicFile(Environment.DIRECTORY_PICTURES,PCITRUE_FILE_NAME);
			
				FileOutputStream os;
				try {
					os = new FileOutputStream(file);

					Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.m3);

					bitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
					
					os.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		mReaderBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!isExternalStorageWritable()) {
					Toast.makeText(MyFileActivity.this, "外部存储不可用", Toast.LENGTH_SHORT).show();
					return;
				}
				
				File file = new File(fileDir, PCITRUE_FILE_NAME);
				mDirTxt.setText(file.getPath());
				
				Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
				
				mShowImg.setImageBitmap(bitmap);
			}
		});

		
		mSearchFileBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Logs.v("sdcard root " + Environment.getExternalStorageDirectory()); // /mnt/sdcard/

				File rootFile = Environment.getExternalStorageDirectory();
				
				toSearchFile(rootFile);
			}
		});
	}
	/**
	 * 根据外部存储公共区域类型和文件名创建文件
	 * @param directoryType
	 * @param fileName
	 * @return
	 */
	public File createExternalStoragePublicFile(String directoryType,String fileName){
		File folder = Environment.getExternalStoragePublicDirectory(directoryType);
		if(!folder.exists()){
			folder.mkdir();
		}
		
		File file = new File(folder,fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return file;
	}
	
	
	private void toSearchFile(File rootFile) {
		File[] listFile = rootFile.listFiles();
		
		if(listFile != null){
			for (File file : listFile) {
				if (file.isDirectory()) {//目录
					File files = file.getAbsoluteFile();
				
					toSearchFile(files);
				} else { //文件
					// 过虑文件显示路径
					if(file.getPath().endsWith(".png")){
						Logs.v(""+file.getPath());
					}
					
				}
			}
		}
	}

	
	
	/**
	 * 写数据到内部存储区域
	 */
	public void writeInternalStorage() {
		Logs.v("应用程序内部存储文件路径 :" + getFilesDir());// /data/data/com.scxh1502.android/files/
		Logs.v("应用程序内部存储缓存路径 :" + getCacheDir());// /data/data/com.scxh1502.android/cache/

		String string = "Hello world Android 你好Android !";

		File file = new File(getFilesDir(), "myfile1");

		FileOutputStream os;
		try {
			os = new FileOutputStream(file);
			os.write(string.getBytes());
			os.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 检查外部存储是否可用
	 * @return
	 */
	private boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 写图片到外部存储公共区域
	 */
	public void writePictureToExternalStorage() {

	}
}
