/*******************************************************************************
 * Copyright 2014 Manh Luong   Bui
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.luongbui.gdx.libpd.pianotest.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.luongbui.gdx.libpd.pianotest.PianoTest;
import com.luongbui.gdx.libpd.pianotest.character.PianoKey;

public class PianoScreen implements Screen {
	
	public static final int WIDTH_VIRTUAL = 1920;
	public static final int HEIGHT_VIRTUAL = 1080;
	
	public static final int KEYS_NUM = 8;
	
	private final PianoTest game;
	
	private Stage stage;
	
	private PianoKey keys[];
	
	private int viewportX;
	private int viewportY;
	private int viewportWidth;
	private int viewportHeight;
	
	public PianoScreen(final PianoTest game) {
		this.game = game;
		stage = new Stage();
		keys = new PianoKey[KEYS_NUM];
		for(int i=0; i<keys.length; i++) {
			keys[i] = new PianoKey(((WIDTH_VIRTUAL/KEYS_NUM)*i)+1,
									1,
									WIDTH_VIRTUAL/KEYS_NUM,
									HEIGHT_VIRTUAL-1,
									(440f + i*60),
									game);
			stage.addActor(keys[i]);
			}
		Gdx.input.setInputProcessor(stage);
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
		Vector2 size = Scaling.fillX.apply(WIDTH_VIRTUAL, HEIGHT_VIRTUAL, width, height);
		viewportX = MathUtils.ceilPositive(width - size.x) / 2;
		viewportY = MathUtils.ceilPositive(height - size.y) / 2;
		viewportWidth = MathUtils.floorPositive(size.x);
		viewportHeight = MathUtils.floorPositive(size.y);
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
