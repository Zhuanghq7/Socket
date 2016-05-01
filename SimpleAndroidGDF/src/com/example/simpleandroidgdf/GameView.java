package com.example.simpleandroidgdf;

import com.example.game.state.State;
import com.example.framework.util.InputHandler;
import com.example.framework.util.Painter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.content.Context;
import android.view.SurfaceView;

import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import com.example.game.state.LoadState;

public class GameView extends SurfaceView implements Runnable {

	private Bitmap gameImage;
	private Rect gameImageSrc;
	private Rect gameImageDst;
	private Canvas gameCanvas;
	private Painter graphics;
	
	private Thread gameThread;
	private volatile boolean running = false;
	private volatile State currentState;
	private static int tag = 0;
	private InputHandler inputHandler;
	
	public GameView(Context context,int gameWidth,int gameHeight){
		
		super(context);
		gameImage = Bitmap.createBitmap(gameWidth,gameHeight,Bitmap.Config.RGB_565);
		gameImageSrc = new Rect(0,0,gameImage.getWidth(),gameImage.getHeight());
		gameImageDst = new Rect();
		gameCanvas = new Canvas(gameImage);
		graphics =  new Painter(gameCanvas);
		
		SurfaceHolder holder = getHolder();
		holder.addCallback(new Callback(){

			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				//Log.d("GameView", "Surface Created");
				initInput();
				if(currentState == null){
					setCurrentState(new LoadState());
				}
				System.out.println("creat surface success");
				initGame();
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				//Log.d("GameView","Surcface Destroyed");
				pauseGame();
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void initGame(){
		running = true;
		gameThread = new Thread(this,"Game Thread");
		gameThread.start();
	}
	
	private void pauseGame(){
		running = false;
		while(gameThread.isAlive()){
			try{
				gameThread.join();
				break;
			}catch(InterruptedException e){
				
			}
		}
	}
	private void initInput(){
		if(inputHandler ==null){
			inputHandler = new InputHandler();
		}
		setOnTouchListener(inputHandler);
	}
	
	public GameView(Context context){
		super(context);
	}
	public void setCurrentState(State newState) {
		tag = 0;
		System.gc();
		newState.init();
		currentState = newState;
		inputHandler.setCurrentState(currentState);
		tag = 1;
		// TODO Auto-generated method stub
		
	}
	
	private void updateAndRender(long delta){
		currentState.update(delta/1000f);
		System.out.println("currentstate update");
		currentState.render(graphics);
		renderGameImage();
	}
	
	private void renderGameImage(){
		Canvas screen = getHolder().lockCanvas();
		if(screen !=null){
			screen.getClipBounds(gameImageDst);
			screen.drawBitmap(gameImage, gameImageSrc, gameImageDst,null);
			getHolder().unlockCanvasAndPost(screen);
		}
	}

	@Override
	public void run() {
		long updateDurationMillis = 0;
		long sleepDurationMillis = 0;
		System.out.println("run");
		while(running){
			if(tag ==1){
				
			long beforeUpdateRender = System.nanoTime();
			long deltaMillis = sleepDurationMillis+updateDurationMillis;
			updateAndRender(deltaMillis);
			System.out.println("updateandrender");
			updateDurationMillis = (System.nanoTime()-beforeUpdateRender)/1000000L;
			sleepDurationMillis = Math.max(2, 17-updateDurationMillis);
			
			try{
				Thread.sleep(sleepDurationMillis);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			}
		}
		// TODO Auto-generated method stub
		
	}
}
