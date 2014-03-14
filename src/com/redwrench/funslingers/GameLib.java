package com.redwrench.funslingers;

import java.util.Random;


public class GameLib {
	public static final int TIMER_MINIMUM = 1000;
	public static final int TIMER_MAXIMUM = 10000;
	public boolean gameOver = false;
	public boolean canShoot = false;
	public boolean shotFired = false;
	public boolean gameStarted = false;
	float tickTime = 0;
	int maxGameTime = 0;
	
	public GameLib(){
		
	}
	
	public void Shoot(){
		
		shotFired = true;
		gameOver = true;
	}
	
	//Randomly determines a time(within the min/max boundaries) to wait before 'fire' is announced
	public void GetNewCountDown(){
		Random rand = new Random();
		maxGameTime = rand.nextInt(TIMER_MAXIMUM - TIMER_MINIMUM + 1) - TIMER_MINIMUM;
		tickTime = 0;
	}
	
	public void update(float deltaTime){
		if(gameOver)
			return;
		
		if(tickTime >= maxGameTime)
			canShoot = true;
		else
			tickTime += deltaTime;
		
	}
	


}
