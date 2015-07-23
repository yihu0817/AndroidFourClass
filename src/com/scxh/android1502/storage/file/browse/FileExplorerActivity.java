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
	// �ֱ��¼��Ŀ¼���������һ��Ŀ¼�͵�ǰĿ¼��
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
		// ��֤���ص�SD����Ŀ¼ʱ�����������Ϸ���
		if (rootFolder.equals(folder)) {
			lastFolder = folder;
		} else {
			lastFolder = folder.getParentFile();
		}

		ArrayList<File> fileLists = new ArrayList<File>();// ��ʼ������Դ

		File[] files = folder.listFiles();// �õ���ǰ·�����ص��ļ����ļ����б�

		if (files != null && files.length > 0) {
			for (File file : files) {
				if(file!=null){
					fileLists.add(file);
				}
			}
		} else {
			fileLists.add(new File(""));// ���ļ���Ϊ��ʱ��ʾ��ͼƬ
		}
		// ˢ������������
		adapter.setFileList(fileLists);
		setListAdapter(adapter);
		// ��ʾ��ǰ·��
		currentPath = folder.getAbsolutePath();
		currentPathTxt.setText(currentPath);

		// �������Ϸ��ذ�ť״̬
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
				Toast.makeText(this, "�Ѿ�����Ŀ¼����", Toast.LENGTH_SHORT).show();
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
	 * �Զ���Adapter�̳���BaseAdapter��ʵ���ض��Ľ���
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
			// �Ż�ListView
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(R.layout.file_list_item, null);
			}

			ImageView iconImgView = (ImageView) convertView.findViewById(R.id.file_icon);
			TextView fileNameTxtview = (TextView) convertView.findViewById(R.id.file_name);

			// �õ���Ӧ���ļ�����
			File file = (File) getItem(position);

			// �����ļ����ļ������Ͳ�ͬ���벻ͬͼ��
			if (file.isDirectory()) {
				iconImgView.setImageResource(R.drawable.folder);
			} else if (file.isFile()) {
				iconImgView.setImageResource(R.drawable.file);
			} else {
				ImageView iconView = new ImageView(mContext);
				iconView.setImageResource(R.drawable.empty_icon);
				return iconView;
			}

			// ����Ҫ��ʾ���ļ���
			fileNameTxtview.setText(file.getName());
			
			return convertView;
		}
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			FileListItemViewCache itemCache;
//			// �Ż�ListView
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
//			// �õ���Ӧ���ļ�����
//			File file = (File) getItem(position);
//
//			// �����ļ����ļ������Ͳ�ͬ���벻ͬͼ��
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
//			// ����Ҫ��ʾ���ļ���
//			itemCache.fileNameTxtview.setText(file.getName());
//			return convertView;
//		}

	}

	/**
	 * �����Ż�ListView����ͼ��
	 */
	static class FileListItemViewCache {
		public ImageView iconImgView;
		public TextView fileNameTxtview;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// �Ƿ񴥷�����Ϊback��
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// ���� �˳�ȷ�Ͽ�
			showDialog(DIALOG_CANCEL_APPLICATION);
			/*
			 * ��˼��ϵͳ�������ִ�и��¼�����Ϊ�Ѿ������ѵ���
			 */
			return true;
		} else {
			// �������back��������Ӧ
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_CANCEL_APPLICATION:
			return new AlertDialog.Builder(FileExplorerActivity.this)
					.setMessage("�Ƿ��˳��ļ����������")
					.setTitle("��ʾ")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									finish();
								}
							})
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).create();
		}
		return null;
	}

}
