package com.scxh.android1502.media.mp3;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.MusicBean;

public class MusicListActivity extends Activity implements OnItemClickListener {
	private ListView mListView;
	private ArrayList<File> mFileList = new ArrayList<File>();
	private ArrayList<MusicBean> mMusicBeanList = new ArrayList<MusicBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_music_list_layout);
		mListView = (ListView) findViewById(R.id.music_listview);

		if (!isExternalStorageWritable()) {
			Toast.makeText(this, getString(R.string.external_storage_message),
					Toast.LENGTH_SHORT).show();
			return;
		}

		File rootFile = Environment.getExternalStorageDirectory(); // /mnt/sdcard/

		searchFile(rootFile);
		
		MusicListAdaper adapter = new MusicListAdaper(this);
		mListView.setAdapter(adapter);
		
		mListView.setOnItemClickListener(this);
	}

	
	
	class MusicListAdaper extends BaseAdapter {
		private LayoutInflater mLayoutInflater;

		public MusicListAdaper(Context context) {
			mLayoutInflater = LayoutInflater.from(context);
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
			if(convertView == null){
				convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, null);
			}
			
			File file = (File) getItem(position);
			String fileName = file.getName();
			
			TextView nameTxt = (TextView) convertView;
			nameTxt.setText(fileName);
			
			return convertView;
		}
	}

	private void searchFile(File rootFile) {
		File[] fileList = rootFile.listFiles();
		if (fileList != null) {
			for (File file : fileList) {
				if (file.isDirectory()) {
					searchFile(file);
				} else {
					if (file.getPath().endsWith(".mp3")) {
						mFileList.add(file);
						
						//构造音乐播放列表资源
						MusicBean music = new MusicBean();
						music.setMusicName(file.getName()); 
						music.setMusicPath(file.getPath());
						
						String musicName = music.getMusicName();  // 音乐文件名.mp3
						musicName = musicName.substring(0, musicName.length()-4); // ========字符串处理方法一=======
						//=============字符串处理方法二===========
//						
//						Logs.v("musicName >>>>> :"+musicName);
//						String[] musicArray = musicName.split(".");  //{音乐文件名.mp3}
//						Logs.v("musicArray size :"+musicArray.length);
//						musicName = musicArray[0];
						
						music.setMusicName(musicName);
						
						
						
						mMusicBeanList.add(music);
					}
				}
			}
		}
	}

	/**
	 * 检查外部存储是否可用
	 * 
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Intent intent = new Intent(this,UIMusicPlayerActivity.class);
		intent.putParcelableArrayListExtra("MUSIC_LIST", mMusicBeanList);
		intent.putExtra("CURRENT_POSTION", position);
		startActivity(intent);
		
		
	}
}
