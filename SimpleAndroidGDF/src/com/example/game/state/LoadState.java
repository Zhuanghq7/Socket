package com.example.game.state;

import android.view.MotionEvent;

import com.example.framework.util.Painter;
import com.example.simpleandroidgdf.Assets;

public class LoadState extends State{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Assets.load();
		
	}

	@Override
	public void update(float delta) {
		setCurrentState(new MenuState());
		System.out.println("menustate seted~");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Painter g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		// TODO Auto-generated method stub
		return false;
	}

}
