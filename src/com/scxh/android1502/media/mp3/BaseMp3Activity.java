package com.scxh.android1502.media.mp3;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class BaseMp3Activity extends Activity implements OnClickListener {
	private Button mPlayerBaseBtn, mPlayerRawBtn, mPlayerFileBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mp3_layout);
		mPlayerBaseBtn = (Button) findViewById(R.id.mp3_player_btn);
		mPlayerBaseBtn.setOnClickListener(this);
		mPlayerRawBtn = (Button) findViewById(R.id.mp3_player_raw_btn);
		mPlayerRawBtn.setOnClickListener(this);
		mPlayerFileBtn = (Button) findViewById(R.id.mp3_player_stream_btn);
		mPlayerFileBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mp3_player_btn:
			playerIntentBaseMp3();
			break;

		case R.id.mp3_player_raw_btn:
			playerRawMp3();
			break;

		case R.id.mp3_player_stream_btn:
			playerFileMp3();
			break;
		}
	}

	private void playerFileMp3() {
		String musicRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath();
		String musicFile = "file://" + musicRoot + "/qianlvyouhun.mp3";

		MediaPlayer mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(musicFile);

			mediaPlayer.prepare();

			mediaPlayer.start();

			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// 音乐播放完成执行
					Toast.makeText(BaseMp3Activity.this, "音乐播放结束",Toast.LENGTH_SHORT).show();
				}
			});

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void playerRawMp3() {

		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.qianlvyouhun);
		mediaPlayer.start();
		Toast.makeText(this, "音乐开始播放", Toast.LENGTH_SHORT).show();
	}

	/**
	 * 隐式调用播放音乐
	 */
	private void playerIntentBaseMp3() {
		String musicRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath();
		String musicFile = "file://" + musicRoot + "/qianlvyouhun.mp3";

		Uri musicUri = Uri.parse(musicFile);

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(musicUri, "audio/mp3");
		startActivity(intent);
	}
}
