package com.redwrench.funslingers;

import com.redwrench.android.framework.Game;
import com.redwrench.android.framework.Graphics;
import com.redwrench.android.framework.Audio;
import com.redwrench.android.framework.Screen;
import com.redwrench.android.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game){
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		String soundDir = "sounds/";
		Graphics graphics = game.getGraphics();
		Audio audio = game.getAudio();
		Assets.background = graphics.newPixmap("background.png", PixmapFormat.RGB565);
		Assets.mainMenu = graphics.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
		Assets.pauseMenu = graphics.newPixmap("pause.png", PixmapFormat.ARGB4444);
		Assets.readyButton = graphics.newPixmap("ready.png", PixmapFormat.ARGB4444);
		Assets.fire = graphics.newPixmap("fire.png", PixmapFormat.ARGB4444);
		Assets.winResult1 = graphics.newPixmap("result1.png", PixmapFormat.ARGB4444);
		Assets.winResult2 = graphics.newPixmap("result2.png", PixmapFormat.ARGB4444);
		Assets.armed = graphics.newPixmap("armed.png", PixmapFormat.ARGB4444);
		Assets.playAgain1 = graphics.newPixmap("playagain1.png", PixmapFormat.ARGB4444);
		Assets.playAgain2 = graphics.newPixmap("playagain2.png", PixmapFormat.ARGB4444);
		
		Assets.gameOver = audio.newSound(soundDir + "shells_falls.wav");
		Assets.menuSelect = audio.newSound(soundDir + "pop_clip_in.wav");
		Assets.menuLeave = audio.newSound(soundDir + "dry_fire_gun.wav");
		Assets.ready = audio.newSound(soundDir + "gun_cocking.wav");
		Assets.shoot = audio.newSound(soundDir + "gun_shot.wav");
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		
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
	
	
}
