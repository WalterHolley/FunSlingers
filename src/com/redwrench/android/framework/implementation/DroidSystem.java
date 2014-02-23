package com.redwrench.android.framework.implementation;

import android.graphics.Point;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Context;

public class DroidSystem {
	
	
	
	public static int getScreenWidth(){
		Context cxt = null;
		WindowManager wm = (WindowManager) cxt.getSystemService(Context.WINDOW_SERVICE);
		int appSDK = android.os.Build.VERSION.SDK_INT;
		if(appSDK >= android.os.Build.VERSION_CODES.FROYO && appSDK <= android.os.Build.VERSION_CODES.HONEYCOMB_MR1){
			return wm.getDefaultDisplay()
					.getWidth();
			
			
		}
		else{
			Point outSize = new Point();
			wm.getDefaultDisplay().getSize(outSize);
			return outSize.x;
			
		}
	}
	
	public static int getScreenHeight(){
		
		Context cxt = null;
		WindowManager wm = (WindowManager) cxt.getSystemService(Context.WINDOW_SERVICE);
		
		int appSDK = android.os.Build.VERSION.SDK_INT;
		if(appSDK >= android.os.Build.VERSION_CODES.FROYO && appSDK <= android.os.Build.VERSION_CODES.HONEYCOMB_MR1){
			
			return wm.getDefaultDisplay()
					.getHeight();
			
			
			
		}
		else{
			Point outSize = new Point();
			wm.getDefaultDisplay().getSize(outSize);
			return outSize.y;
			
		}
	}

}
