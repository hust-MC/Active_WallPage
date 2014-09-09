package com.Emercy.setwallpager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class SetWallpager implements Runnable
{
	public void run()
	{
		FileInputStream fis;

		while (MainActivity.flag)
		{
			fis = null;

			if (MainActivity.file.isDirectory())
			{
			
				File[] fileArray = MainActivity.file.listFiles();
				if (null != fileArray && 0 != fileArray.length)
				{
					for (File f : fileArray)
					{
						if (!f.getPath().substring(f.getPath().length() - 3)
								.equals("jpg"))
						{
							Log.d("MC", "continue");
							continue;
						}
						Log.d("MC", "1");
						try
						// get The bigmap
						{
							Log.d("MC", "fis");
							fis = new FileInputStream(f);
						} catch (FileNotFoundException e1)
						{
							Log.d("MC", "fis_catch");
							e1.printStackTrace();
						}
						Log.d("MC", "bitmap");
						Bitmap bitmap = BitmapFactory.decodeStream(fis);

						try
						// set the wallpager
						{
							Log.d("MC", "set");
							MainActivity.mWallManager.setBitmap(bitmap);
						} catch (IOException e)
						{
							Log.d("MC", "set_catch");
							e.printStackTrace();
						}

						try
						// delay
						{
							Thread.currentThread();
							Thread.sleep(2500);
						} catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}

		}
	}
}