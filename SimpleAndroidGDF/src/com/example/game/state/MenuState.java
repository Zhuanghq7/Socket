package com.example.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.framework.util.Painter;
import com.example.simpleandroidgdf.Assets;
import com.example.simpleandroidgdf.GameMainActivity;
import com.example.simpleandroidgdf.GameView;
public class MenuState extends State{
	
	public Typeface tf=Typeface.createFromAsset(GameMainActivity.assets, "fonts/MSYH.TTF");//根据路径得到Typeface
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Painter g) {
		//g.drawImage(Assets.welcome,0,0);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
		g.setFont(tf, 50);
		g.setColor(Color.WHITE);
		g.drawString("PLAY", GameMainActivity.GAME_WIDTH/2-50, GameMainActivity.GAME_HEIGHT/3*2);
		g.drawString("EXIT", GameMainActivity.GAME_WIDTH/2-40, GameMainActivity.GAME_HEIGHT/3*2+80);
		g.setColor(Color.RED);
		g.fillRect(GameMainActivity.GAME_WIDTH/2-100, GameMainActivity.GAME_HEIGHT/3*2-36, 30, 30);
		System.out.println("drawImage~");
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		// TODO Auto-generated method stub
		return false;
	}

}
