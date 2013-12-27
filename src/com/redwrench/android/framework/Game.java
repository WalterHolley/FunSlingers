package com.redwrench.android.framework;

public interface Game {
	public Input getInput();
	
	public FileOps getFileIO();
	
	public Graphics getGraphics();
	
	public Audio getAudio();
	
	public void setScreen(Screen screen);
	
	public void onResume();
	
	public void onPause();
	
	public Screen getStartScreen();
	

}
