package com.redwrench.funslingers;

import java.util.List;

import com.redwrench.android.framework.Game;
import com.redwrench.android.framework.Graphics;
import com.redwrench.android.framework.Screen;
import com.redwrench.android.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen{
	public MainMenuScreen(Game game){
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		if(touchEvents != null){
			
		
		int touchEventCount = touchEvents.size();
		
		//Iterate through touch events for main menu
		for(int i = 0; i < touchEventCount; i++){
			TouchEvent event = touchEvents.get(i);
			
			
			if(event.type == TouchEvent.TOUCH_UP){
				if(inBounds(event, 0, g.getWidth() - 64, 64, 64)){
					//play sound for button press
				}
				
				//Play game button pressed
				if(inBounds(event, 64, 220, 192, 42)){
					game.setScreen(new GameScreen(game));
					//play button sound
					return;
				}
				
				//Help screen button pressed
				if(inBounds(event, 64, 220 + 42, 192, 42)){
					game.setScreen(new HelpScreen(game));
					//play button sound
					return;
				}
			}
		}	
			
		}
			
		
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics graphics = game.getGraphics();
		graphics.drawPixmap(Assets.background, 0, 0);
		graphics.drawPixmap(Assets.mainMenu, 64, 220);
		
		//handle sound settings
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
	
	private boolean inBounds(TouchEvent event, int x, int y, int height, int width){
		if(event.x > x && event.x < x + width - 1 &&
				event.y > y && event.y < y + height - 1){
			return true;
			
		}
		return false;
	}

}
