package com.scxh.android1502.storage.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class FileActivity extends Activity {
	private final static String PIC_FILE_NAME = "testpic1.png";
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_file1_layout);
		mImageView = (ImageView) findViewById(R.id.imageView1);

		Logs.v("sdcard root " + Environment.getExternalStorageDirectory()); // /mnt/sdcard/

		File rootFile = Environment.getExternalStorageDirectory();
		toSearchFile(rootFile);
	}

	private void toSearchFile(File rootFile) {
		File[] listFile = rootFile.listFiles();
		
		for (File file : listFile) {
			if (file.isDirectory()) {//目录
				File files = file.getAbsoluteFile();
				toSearchFile(files);
			} else { //文件操作
				// 过虑文件显示路径
				Logs.v(""+file.getPath());
			}
		}
	}

	/**
	 * 内部存储
	 * 
	 * @param v
	 * @throws IOException
	 */
	public void onWriteInternalFile(View v) throws IOException {
		//==================方法一================
		String string = "Hello world Android 你好Android !";
		File file = new File(getFilesDir(), "myfile1");
		FileOutputStream os = new FileOutputStream(file);
		os.write(string.getBytes());
		os.close();

		Logs.v("应用程序内部存储文件路径 :" + getFilesDir());// /data/data/com.scxh.android/files/
		Logs.v("应用程序内部存储缓存路径 :" + getCacheDir());// /data/data/com.scxh.android/cache/

		// ============方法二 ===========================
		String filename = "myfile2";
		String strings = "Hello world !";
		FileOutputStream outputStream;
		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);// /data/data/com.scxh.android/files/
			outputStream.write(strings.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 外部存储私有文件
	 * 
	 * @param v
	 */
	public void onWriteExternalFile(View v) {
		if (!isExternalStorageWritable()) {
			Toast.makeText(this, "外部存储不存在", Toast.LENGTH_SHORT).show();
			return;
		}

		String string = "Hello world Android 你好Android ! 外部存储";

		File file = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),"mymusicfile");
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
		Logs.v("应用程序外部存储文件路径 :"+ getExternalFilesDir(Environment.DIRECTORY_MUSIC)); // 外部存储私有区域
		Logs.v("应用程序外部存储缓存路径 :" + getExternalCacheDir());

		// 应用程序外部存储文件路径:/mnt/sdcard/Android/data/com.scxh.android/files/xx
		// 应用程序外部存储缓存路径 :/mnt/sdcard/Android/data/com.scxh.android/cache

	}

	/**
	 * 外部存储共公文件
	 * @param v
	 * @throws IOException
	 */
	public void onWriteExternalPublicFile(View v) throws IOException {
		if (!isExternalStorageWritable()) {
			Toast.makeText(this, "外部存储不存在", Toast.LENGTH_SHORT).show();
			return;
		}

		String string = "Hello world Android 你好Android 外存公有!";

		File file = getExternalStoragePublicDirectory("myPublicPic",Environment.DIRECTORY_PICTURES);

		FileOutputStream os = null;
		try {

			os = new FileOutputStream(file);
			os.write(string.getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Logs.v("应用程序外部存储公有文件路径 :"
				+ Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
		Logs.v("应用程序外部存储公有camera路径 :"
				+ Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));

		// 应用程序外部存储文件路径:/mnt/sdcard/Android/data/com.warmtel.android/files/Download
		// 应用程序外部存储缓存路径 :/mnt/sdcard/Android/data/com.warmtel.android/cache

	}

	/**
	 * 写图片文件到公共存储
	 * @param v
	 * @throws IOException
	 */
	public void onWriteExternalPublicPictureFile(View v) throws IOException {
		if (!isExternalStorageWritable()) {
			Toast.makeText(this, "外部存储不存在", Toast.LENGTH_SHORT).show();
			return;
		}

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.m3);
		File f = getExternalStoragePublicDirectory(PIC_FILE_NAME,Environment.DIRECTORY_PICTURES);

		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Logs.v("应用程序外部存储公有文件路径 :"
				+ Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
		Logs.v("应用程序外部存储公有camera路径 :"
				+ Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
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

	public void onShowPictureFile(View v) {
		showImage();
	}

	public void showImage() {
		String fileDirector = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
		String pathName = fileDirector + "/" + PIC_FILE_NAME;
		Bitmap bitmap = BitmapFactory.decodeFile(pathName);

		mImageView.setImageBitmap(bitmap);
	}

	/**
	 * 读图片文件到公共存储
	 * @param fileName
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public File getExternalStoragePublicDirectory(String fileName, String type)
			throws IOException {
		File fileDriector = Environment.getExternalStoragePublicDirectory(type);

		if (!fileDriector.exists()) {
			fileDriector.mkdir();
		}

		File file = new File(fileDriector, fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
}
