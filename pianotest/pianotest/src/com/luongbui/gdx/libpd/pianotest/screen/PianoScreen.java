package com.luongbui.gdx.libpd.pianotest.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.luongbui.gdx.libpd.pianotest.PianoTest;

public class PianoScreen implements Screen {
	
	public static final int WIDTH_VIRTUAL = 2048;
	public static final int HEIGHT_VIRTUAL = 1154;
	
	private final PianoTest game;
	
	private Stage stage;
	
	private int viewportX;
	private int viewportY;
	private int viewportWidth;
	private int viewportHeight;
	
	public PianoScreen(final PianoTest game) {
		this.game = game;
		stage = new Stage();
		this.game.addProcessor(stage);
		}

	@Override
	public void render(float delta) {
		// Must call this again, to keep the new viewport. Otherwise it is stretched in iOS.
		// See libgdx issue #1415 - https://github.com/libgdx/libgdx/issues/1415
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		}

	@Override
	public void resize(int width, int height) {
		Vector2 size = Scaling.fillY.apply(WIDTH_VIRTUAL, HEIGHT_VIRTUAL, width, height);
		viewportX = MathUtils.ceilPositive(width - size.x) / 2;
		viewportY = MathUtils.ceilPositive(height - size.y) / 2;
		viewportWidth = MathUtils.ceilPositive(size.x);
		viewportHeight = MathUtils.ceilPositive(size.y);
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		stage.setViewport(WIDTH_VIRTUAL,
							HEIGHT_VIRTUAL,
							true,
							viewportX,
							viewportY,
							viewportWidth,
							viewportHeight);
		}

	@Override
	public void show() {
		
		}

	@Override
	public void hide() {
		
		}

	@Override
	public void pause() {
		
		}

	@Override
	public void resume() {
		
		}

	@Override
	public void dispose() {
		stage.dispose();
		}

	}
