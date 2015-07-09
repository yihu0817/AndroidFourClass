package com.scxh.android1502.ui.progressbar_seekbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.scxh.android1502.R;
import com.scxh.android1502.util.Logs;

public class SeekBarActivity extends Activity {
	private SeekBar mSeekBar,mSeekBarCustom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_seekbar1_layout);
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBarCustom = (SeekBar) findViewById(R.id.seekBar2);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mSeekBar.incrementProgressBy(10);
				}

			}
		}).start();

		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Logs.v("onStopTrackingTouch");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Logs.v("onStartTrackingTouch");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
				Logs.v("onStartTrackingTouch  progress"+progress);

			}
		});
		
		
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mSeekBarCustom.incrementProgressBy(10);
				}

			}
		}).start();
	}
}
