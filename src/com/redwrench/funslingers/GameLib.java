package com.redwrench.funslingers;

import java.util.Random;

public class GameLib {
	public static final int TIMER_MINIMUM = 1000;
	public static final int TIMER_MAXIMUM = 10000;
	private boolean gameOver = false;
	private boolean canShoot = false;
	private boolean shotFired = false;
	private boolean gameStarted = false;
	private boolean player1Win = false;
	float tickTime = 0;
	private int maxGameTime = 0;
	
	public GameLib(){
		
	}
	
	public void init(){
		gameOver = false;
		canShoot = false;
		shotFired = false;
		gameStarted = true;
		gameOver = false;
		
		tickTime = 0;
		getNewCountDown();
	}
	//The victor has fired their weapon
	public void Shoot(int shotYPosition){
		
		shotFired = true;
		gameOver = true;
		canShoot = false;
		
		if(shotYPosition < 219)
			player1Win = false;
		else
			player1Win = true;
	}
	
	public boolean didPlayer1Win(){
		return player1Win;
	}
	
	public int getMaxGameTime(){
		
		return maxGameTime;
	}
	public boolean isGameStarted(){
		return gameStarted;
	}
	
	public boolean wasShotFired(){
		return shotFired;
	}
	
	public boolean canUserShoot(){
		return canShoot;
	}
	
	public boolean isGameOver(){
		return gameOver;
	}
	//Randomly determines a time(within the min/max boundaries) to wait before 'fire' is announced
	private void getNewCountDown(){
		Random rand = new Random();
		maxGameTime = rand.nextInt(TIMER_MAXIMUM - TIMER_MINIMUM + 1) - TIMER_MINIMUM;
		tickTime = 0;
	}
	
	public void update(float deltaTime){
		if(gameOver){
			return;
		}
		else{
			if(tickTime >= maxGameTime)
				canShoot = true;
			else
				tickTime += deltaTime;
		}
			
		
		
		
	}
	


}
