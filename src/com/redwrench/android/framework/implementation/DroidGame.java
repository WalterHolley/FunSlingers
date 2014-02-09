package com.redwrench.android.framework.implementation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;


import com.redwrench.android.framework.FileOps;
import com.redwrench.android.framework.Audio;
import com.redwrench.android.framework.Graphics;
import com.redwrench.android.framework.Input;
import com.redwrench.android.framework.Screen;
import com.redwrench.android.framework.Game;

public abstract class DroidGame extends Activity implements Game {
	FastRenderView renderView;
	Graphics graphics;
	Input input;
	FileOps fileOps;
	Screen screen;
	WakeLock wakeLock;
	Audio audio;
	public int ScreenX;
	public int ScreenY;
	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandScape = getResources().getConfiguration().orientation == 
				Configuration.ORIENTATION_LANDSCAPE;
		
		int frameBufferWidth = isLandScape ? 480 : 320;
		int frameBufferHeight = isLandScape ? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, 
				Config.RGB_565);
		
		float scaleX;
		float scaleY;
		
		//getDefaultDisplay().getWidth has been deprecated.  will need to account for compatibility
		int appSDK = android.os.Build.VERSION.SDK_INT;
		if(appSDK >= android.os.Build.VERSION_CODES.FROYO && appSDK <= android.os.Build.VERSION_CODES.HONEYCOMB_MR1){
			ScreenX = getWindowManager().getDefaultDisplay()
					.getWidth();
			ScreenY = getWindowManager().getDefaultDisplay()
					.getHeight();
			
			
			
		}
		else{
			Point outSize = new Point();
			getWindowManager().getDefaultDisplay().getSize(outSize);
			ScreenX = outSize.x;
			ScreenY = outSize.y;
			
		}
			
		scaleX = (float) frameBufferWidth / ScreenX;
		
		scaleY = (float) frameBufferHeight / ScreenY;
		
		renderView = new FastRenderView(this, frameBuffer);
		graphics = new DroidGraphics(getAssets(), frameBuffer);
		fileOps = new DroidFileOps(getAssets());
		audio = new DroidAudio(this);
		input = new DroidInput(this, renderView,scaleX, scaleY);
		
		//TODO Implement getStartScreen()
		screen = getStartScreen();
		
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Funslingers");
		
		
	}

	@Override
	public Input getInput() {
		return input;
	}


	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(Screen screen) {
		if(screen == null)
			throw new IllegalArgumentException("Screen is null");
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	
	@Override 
	public void onResume(){
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		wakeLock.release();
		screen.pause();
		renderView.pause();
		
		if(isFinishing())
			screen.dispose();
		
	}
	
	public Screen getCurrentScreen(){
		return screen;
	}

}
