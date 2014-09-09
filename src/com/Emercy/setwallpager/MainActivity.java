package com.Emercy.setwallpager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{

	static WallpaperManager mWallManager;
	static File file;
	static boolean flag = true;
	Button start_bt, stop_bt;
	Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.d("MC", "create");
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		start_bt = (Button)findViewById(R.id.start_bt);
		stop_bt = (Button)findViewById(R.id.stop_bt);
		
		mWallManager = WallpaperManager.getInstance(this);
		file = new File(Environment.getExternalStorageDirectory() + "/Download/Pictures/");
	}

	public void onClick_start(View view)
	{
		thread = new Thread(new SetWallpager());
		thread.start();
		Toast.makeText(this, "动态壁纸开启，再次点击关闭", Toast.LENGTH_SHORT).show();
		start_bt.setEnabled(false);
		stop_bt.setEnabled(true);
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

	public void onClick_stop(View view)
	{
		flag = false;
		finish();
		System.exit(0);
	}

}
