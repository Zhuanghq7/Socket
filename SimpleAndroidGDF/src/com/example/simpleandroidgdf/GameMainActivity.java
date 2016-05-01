package com.example.simpleandroidgdf;

import android.app.Activity;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.graphics.Typeface;

public class GameMainActivity extends Activity{
	
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	public static GameView sGame;
	public static AssetManager assets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		assets = getAssets();
		sGame = new GameView(this,GAME_WIDTH,GAME_HEIGHT);
		System.out.println("23333");
		
		setContentView(sGame);
	}
}
