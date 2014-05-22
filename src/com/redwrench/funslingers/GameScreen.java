package com.redwrench.funslingers;

import java.util.List;
import android.graphics.Color;
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
		if(gameState == GameState.Running)
			updateRunning(events);
		if(gameState == GameState.GameOver)
			updateGameOver(events);
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
		if(gameState == GameState.GameOver)
			drawGameOverUI();
	}

	@Override
	public void pause() {
		// The Game screen cannot be paused
		
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
	
	
	private void updateRunning(List<TouchEvent> events){
		if(gameLib.isGameStarted()){
			int eventCount = events.size();
			for(int i = 0; i < eventCount; i++ ){
				TouchEvent event = events.get(i);
			
				if(event.type == TouchEvent.TOUCH_UP){
					//perform no actions if the game is over
					if(gameLib.isGameOver())
						return;
					//play sound
					if(gameLib.canUserShoot()){
						//play gun sound
						if(!gameLib.wasShotFired()){
							Assets.shoot.play(1);
							gameLib.Shoot(event.y);
							
							gameState = GameState.GameOver;
						}
						
					}
				
				}
			
			}
		}
		else{
			gameLib.init();
			
		}
		gameLib.update(10);
		
	}
	
	private void updateGameOver(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			//determine yes/no button touch
			//Buttons for player 2 side
			if(gameLib.didPlayer1Win()){
				if(event.x <= 80){
					if(event.y >= 100 && event.y <= 180){
						//No was selected, return to menu
						Assets.menuSelect.play(1);
						game.setScreen(new MainMenuScreen(game));
					}
				}
				if(event.x <= 320 && event.x >= 240){
					if(event.y >= 100 && event.y <=180){
						//Yes was selected.  restart game
						Assets.menuSelect.play(1);
						game.setScreen(new GameScreen(game));
					}
				}
			}
			else{
				//player one side
				if(event.x <= 80){
					if(event.y >= 300 && event.y <= 380){
						//Yes was selected
						Assets.menuSelect.play(1);
						game.setScreen(new GameScreen(game));
					}
				}
				if(event.x <= 320 && event.x >= 240){
					if(event.y >= 300 && event.y <= 380){
						//No was selected
						Assets.menuSelect.play(1);
						game.setScreen(new MainMenuScreen(game));
					}
				}
			}
			
		}
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
		if(gameLib.canUserShoot()){
			g.drawPixmap(Assets.fire, 0, 200);
		}
		else{
			g.drawPixmap(Assets.armed, 0, 160);
		}
	}
	
	private void drawGameOverUI(){
		Graphics g = game.getGraphics();
		if(gameLib.didPlayer1Win()){
			g.drawPixmap(Assets.winResult1, 0, 200);
			g.drawPixmap(Assets.playAgain2, 0, 100);
		}
		else{
			g.drawPixmap(Assets.winResult2, 0, 200);
			g.drawPixmap(Assets.playAgain1, 0, 300);
		}
	}

}
