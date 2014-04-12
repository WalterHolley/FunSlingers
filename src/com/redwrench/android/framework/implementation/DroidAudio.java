package com.redwrench.android.framework.implementation;
import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.redwrench.android.framework.Audio;
import com.redwrench.android.framework.Music;
import com.redwrench.android.framework.Sound;
import com.redwrench.android.framework.implementation.DroidSound;

public class DroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public DroidAudio(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	public Music newMusic(String fileName){
		try{
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			soundPool.load(assetFileDescriptor, 0);
			return new DroidMusic(assetFileDescriptor);
			
		}
		catch(IOException ex){
			throw new RuntimeException("Couldn't load music file: " + fileName);
		}
		
	}

	@Override
	public Sound newSound(String fileName) {
		try{
			AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
			int soundId = soundPool.load(assetFileDescriptor, 0);
			return new DroidSound(soundId, soundPool);
		}
		catch(IOException e){
			throw new RuntimeException("couldn't load sound " + fileName);
		}
	}
		

}
