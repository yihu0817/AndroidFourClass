package com.scxh.android1502.storage.file.browse;

import java.io.File;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class FileExplorerActivity extends ListActivity implements
		OnClickListener {
	// 分别记录根目录、最近的上一级目录和当前目录项
	private File rootFolder = Environment.getExternalStorageDirectory();
	private File lastFolder = rootFolder;
	private String currentPath = lastFolder.getAbsolutePath();

	private Button rootFolderBtn;
	private Button parentFolderBtn;
	private TextView currentPathTxt;

	private FileListAdapter adapter;
	private static final int DIALOG_CANCEL_APPLICATION = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_explorer);

		rootFolderBtn = (Button) findViewById(R.id.root_folder_btn);
		parentFolderBtn = (Button) findViewById(R.id.parent_folder_btn);
		currentPathTxt = (TextView) findViewById(R.id.current_path_txt);

		rootFolderBtn.setOnClickListener(this);
		parentFolderBtn.setOnClickListener(this);

		currentPathTxt.setText(currentPath);

		adapter = new FileListAdapter(this);
		setListAdapter(adapter);

		showFolderItem(lastFolder);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		FileListAdapter adpter = (FileListAdapter) l.getAdapter();
		File filePath = (File) adpter.getItem(position);
		if (filePath.isDirectory()) {
			showFolderItem(filePath);
		} else {
			if(filePath.getAbsolutePath().endsWith(".mp3")){
				String musicFile = "file://"+filePath.getAbsolutePath();
				Uri musicUri = Uri.parse(musicFile);
				
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(musicUri, "audio/mp3");
				startActivity(intent);
			}
			
			
			Toast.makeText(this, filePath.getAbsolutePath(), Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void showFolderItem(File folder) {
		// 保证返回到SD卡根目录时，不能再向上返回
		if (rootFolder.equals(folder)) {
			lastFolder = folder;
		} else {
			lastFolder = folder.getParentFile();
		}

		ArrayList<File> fileLists = new ArrayList<File>();// 初始化数据源

		File[] files = folder.listFiles();// 得到当前路径返回的文件和文件夹列表

		if (files != null && files.length > 0) {
			for (File file : files) {
				if(file!=null){
					fileLists.add(file);
				}
			}
		} else {
			fileLists.add(new File(""));// 当文件夹为空时显示空图片
		}
		// 刷新适配器数据
		adapter.setFileList(fileLists);
		setListAdapter(adapter);
		// 显示当前路径
		currentPath = folder.getAbsolutePath();
		currentPathTxt.setText(currentPath);

		// 设置向上返回按钮状态
		if (rootFolder.getAbsolutePath().equals(currentPath)) {
			parentFolderBtn.setEnabled(false);
		} else {
			parentFolderBtn.setEnabled(true);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.root_folder_btn:
			if (rootFolder.getAbsolutePath().equals(currentPath)) {
				Toast.makeText(this, "已经在主目录下了", Toast.LENGTH_SHORT).show();
			} else {
				showFolderItem(rootFolder);
			}
			break;
		case R.id.parent_folder_btn:
			showFolderItem(lastFolder);
			break;
		}
	}

	/**
	 * 自定义Adapter继承自BaseAdapter，实现特定的界面
	 */
	private class FileListAdapter extends BaseAdapter {
		private ArrayList<File> mFileList = new ArrayList<File>();

		private Context mContext;

		public FileListAdapter(Context context) {
			this.mContext = context;
		}

		public void setFileList(ArrayList<File> fileList) {
			mFileList = fileList;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mFileList.size();
		}

		@Override
		public Object getItem(int position) {
			return mFileList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 优化ListView
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.file_list_item, null);
			}

			ImageView iconImgView = (ImageView) convertView.findViewById(R.id.file_icon);
			TextView fileNameTxtview = (TextView) convertView.findViewById(R.id.file_name);

			// 得到相应的文件对象
			File file = (File) getItem(position);

			// 根据文件和文件夹类型不同载入不同图标
			if (file.isDirectory()) {
				iconImgView.setImageResource(R.drawable.folder);
			} else if (file.isFile()) {
				iconImgView.setImageResource(R.drawable.file);
			} else {
				ImageView iconView = new ImageView(mContext);
				iconView.setImageResource(R.drawable.empty_icon);
				return iconView;
			}

			// 设置要显示的文件名
			fileNameTxtview.setText(file.getName());
			
			return convertView;
		}
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			FileListItemViewCache itemCache;
//			// 优化ListView
//			if (convertView == null) {
//				convertView = LayoutInflater.from(mContext).inflate(
//						R.layout.file_list_item, null);
//
//				itemCache = new FileListItemViewCache();
//				itemCache.iconImgView = (ImageView) convertView.findViewById(R.id.file_icon);
//				itemCache.fileNameTxtview = (TextView) convertView.findViewById(R.id.file_name);
//
//				convertView.setTag(itemCache);
//			} else {
//				itemCache = (FileListItemViewCache) convertView.getTag();
//			}
//
//			if (itemCache == null) {
//				itemCache = new FileListItemViewCache();
//				itemCache.iconImgView = (ImageView) convertView.findViewById(R.id.file_icon);
//				itemCache.fileNameTxtview = (TextView) convertView.findViewById(R.id.file_name);
//
//				Logs.v("itemCache.iconImgView >> :" + itemCache.iconImgView);
//			}
//
//			// 得到相应的文件对象
//			File file = (File) getItem(position);
//
//			// 根据文件和文件夹类型不同载入不同图标
//			if (file.isDirectory()) {
//				itemCache.iconImgView.setImageResource(R.drawable.folder);
//			} else if (file.isFile()) {
//				itemCache.iconImgView.setImageResource(R.drawable.file);
//			} else {
//				ImageView iconView = new ImageView(mContext);
//				iconView.setImageResource(R.drawable.empty_icon);
//				return iconView;
//			}
//
//			// 设置要显示的文件名
//			itemCache.fileNameTxtview.setText(file.getName());
//			return convertView;
//		}

	}

	/**
	 * 用于优化ListView的视图类
	 */
	static class FileListItemViewCache {
		public ImageView iconImgView;
		public TextView fileNameTxtview;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 是否触发按键为back键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 弹出 退出确认框
			showDialog(DIALOG_CANCEL_APPLICATION);
			/*
			 * 意思是系统不会继续执行该事件，因为已经被消费掉了
			 */
			return true;
		} else {
			// 如果不是back键正常响应
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_CANCEL_APPLICATION:
			return new AlertDialog.Builder(FileExplorerActivity.this)
					.setMessage("是否退出文件浏览管理器")
					.setTitle("提示")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									finish();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).create();
		}
		return null;
	}

}
