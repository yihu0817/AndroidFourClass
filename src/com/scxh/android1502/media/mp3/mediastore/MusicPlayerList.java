package com.scxh.android1502.media.mp3.mediastore;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android1502.R;
import com.scxh.android1502.bean.MusicBean;
import com.scxh.android1502.util.Logs;

public class MusicPlayerList extends ListActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List<Mp3Info> list = getMp3Infos(this);
		for (int i = 0; i < list.size(); i++) {
			Mp3Info mp3 = list.get(i);
			Logs.v(" " + mp3.getTitle() + ", " + mp3.getArtist() + " ,"
					+ mp3.getAlbum() + " ," + mp3.getUrl());
		}
		
		
		MyBaseAdapter adapter = new MyBaseAdapter(this);
		setListAdapter(adapter);
		adapter.setData(list);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		MyBaseAdapter adapter = (MyBaseAdapter) l.getAdapter();
		Mp3Info mp3Info = (Mp3Info) adapter.getItem(position);
		
		String musicPath = "file://" + mp3Info.getUrl();

		Intent intent = new Intent(this, MusicStorePlayerService.class);
		intent.putExtra("MUSIC_PATH", musicPath);
		startService(intent);
		
		
		int[] playerArray = new int[adapter.getCount()];
		for(int i = 0; i<playerArray.length; i++){
			if(i == position){
				playerArray[i] = 1;
			}else{
				playerArray[i] = 0;
			}
		}
		
		adapter.setPlayerArray(playerArray);
		
	}
	
	class MyBaseAdapter extends BaseAdapter {
		private Context mContext;
		private List<Mp3Info> mList = new ArrayList<Mp3Info>();
		private LayoutInflater mInflater;  //把xml布局文件转换成View对象
		private int[] playerArray;

		public MyBaseAdapter(Context context) {
			mContext = context;
			mInflater = LayoutInflater.from(context);
		}
		
		/**
		 * 设置数据源，刷新适配器
		 * 
		 * @param list
		 */
		public void setData(List<Mp3Info> list) {
			mList = list;
			
			playerArray = new int[mList.size()];
			for(int i = 0; i<playerArray.length; i++){
				playerArray[i] = 0;
			}
			notifyDataSetChanged();//通知刷新适配器数据
		}

		public void setPlayerArray(int[] playerArray){
			this.playerArray = playerArray;
			notifyDataSetChanged();//通知刷新适配器数据
		}
		/**
		 * 返回容器中元素个数
		 */
		@Override
		public int getCount() {
			return mList.size();
		}
		/**
		 * 返回容器中指定位置的数据项
		 */
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}
		/**
		 * 返回容器中指定位置的ID
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		/**
		 * 返回表示行的view
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHodler = null;
			if (convertView == null) {
				//将第一个参数指定的布局文件转换成View对象,如果第二参数ViewGroup不为空，则把view对象添加到该ViewGroup中
				convertView = mInflater.inflate(R.layout.item_player_listview_layout, null);
				
				viewHodler = new ViewHolder();
				viewHodler.iconImg = (ImageView) convertView.findViewById(R.id.icon_img);
				viewHodler.titleTxt = (TextView) convertView.findViewById(R.id.title_txt);
				viewHodler.contentTxt = (TextView) convertView.findViewById(R.id.content_txt);
				
				convertView.setTag(viewHodler);
			} else {
				viewHodler = (ViewHolder) convertView.getTag();
			}

			Mp3Info content = (Mp3Info) getItem(position);
			
			if(playerArray[position] == 1){
				viewHodler.iconImg.setVisibility(View.VISIBLE);
			}else{
				viewHodler.iconImg.setVisibility(View.INVISIBLE);
			}
			viewHodler.titleTxt.setText(content.getTitle());
			viewHodler.contentTxt.setText(content.getArtist());

			return convertView;
		}

		class ViewHolder {
			ImageView iconImg;
			TextView titleTxt;
			TextView contentTxt;
		}

	}
	
	
	public List<Mp3Info> getMp3Infos(Context context) {
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		
		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();

		while (cursor.moveToNext()) {
			long id = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
			String title = cursor.getString((cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
			String artist = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
			String album = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // 专辑
			String displayName = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
			long albumId = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
			long duration = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
			long size = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
			String url = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
			int isMusic = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐
			if (isMusic != 0) { // 只把音乐添加到集合当中
				Mp3Info mp3Info = new Mp3Info();
				mp3Info.setId(id);
				mp3Info.setTitle(title);
				mp3Info.setArtist(artist);
				mp3Info.setAlbum(album);
				mp3Info.setDisplayName(displayName);
				mp3Info.setAlbumId(albumId);
				mp3Info.setDuration(duration);
				mp3Info.setSize(size);
				mp3Info.setUrl(url);
				
				mp3Infos.add(mp3Info);
			}
		}
		return mp3Infos;
	}
	
	
	public List<MusicBean> getMusicBeanList(Context context) {
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		
		List<MusicBean> mp3Infos = new ArrayList<MusicBean>();

		while (cursor.moveToNext()) {
			long id = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
			String title = cursor.getString((cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
			String artist = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
			String album = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // 专辑
			String displayName = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
			long albumId = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
			long duration = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
			long size = cursor.getLong(cursor
					.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
			String url = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
			int isMusic = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐
			if (isMusic != 0) { // 只把音乐添加到集合当中
				MusicBean music = new MusicBean();
				music.setMusicName(title);
				music.setMusicPath(url);
				mp3Infos.add(music);
			}
		}
		return mp3Infos;
	}
}
