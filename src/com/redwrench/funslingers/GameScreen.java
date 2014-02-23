package com.redwrench.funslingers;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.app.Activity;



import com.redwrench.android.framework.Game;
import com.redwrench.android.framework.Graphics;
import com.redwrench.android.framework.Input.TouchEvent;
import com.redwrench.android.framework.Screen;
import com.redwrench.android.framework.implementation.DroidGame;


public class GameScreen extends Screen{
	enum GameState{
		Ready, 
		Running, 
		Paused, 
		GameOver;
				
	}
	
	GameLib gameLib;
	GameState gameState = GameState.Ready;
	int screenHeight = 0;
	int screenWidth = 0;
	
	
	public GameScreen(Game game){
		super(game);
		gameLib = new GameLib();
		
		screenHeight = ((DroidGame)game).ScreenY;
		screenWidth = ((DroidGame)game).ScreenX;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List<TouchEvent> events = game.getInput().getTouchEvents();
		
		//update game state
		if(gameState == GameState.Paused)
			updatePaused(events);
		if(gameState == GameState.Ready)
			updateReady(events);
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.background, 0, 0);
		
		if(gameState == GameState.Paused)
			drawPausedUI();
		if(gameState == GameState.Ready)
			drawReadyUI();
		if(gameState == GameState.Running)
			drawRunningUI();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private void updatePaused(List<TouchEvent> events){
		int eventCount = events.size();
		for(int i = 0; i < eventCount; i++){
			TouchEvent event = events.get(i);
			if(event.type == TouchEvent.TOUCH_UP){
				//determine if resume button was pressed
				
				//determine if quit button was pressed
			}
			
		}
		
	}
	
	
	private void updateRunning(){
		
	}
	
	private void updateReady(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.x > 0 && event.x <= 320){
				if(event.y > 0 && event.y <= 480){
					gameState = GameState.Running;
					return;
				}
			}
		}
	}
	
	private void drawPausedUI(){
		Graphics g = game.getGraphics();
		
		g.drawPixmap(Assets.pauseMenu, 80, 100);
	}
	
	private void drawReadyUI(){
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.readyButton, 43, 140);
		
	}
	
	private void drawRunningUI(){
		Graphics g = game.getGraphics();
		g.drawLine(0, 240, 320, 240, Color.BLACK);
	}

}
