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

package com.luongbui.gdx.libpd.pianotest.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.luongbui.gdx.libpd.pianotest.PianoTest;

public class PianoKey extends Actor {
	
	private final PianoTest game;
	
	private ShapeRenderer renderer;
	
	private boolean isPressed;
	
	private float hertz;
	
	public PianoKey(float x,
					float y,
					float width,
					float height,
					float hertz,
					final PianoTest game) {
		
		renderer = new ShapeRenderer();
		isPressed = false;
		this.hertz = hertz;
		this.game = game;
		
		final PianoKey thisPtr = this;
		
		setBounds(x, y, width, height);
		addListener(new InputListener() {
			@Override
		    public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				isPressed = true;
				game.getAudioEngine().sendFloat("freq", thisPtr.getHertz());
				game.getAudioEngine().sendBang("trig");
		    	}
			
			@Override
		    public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor) {
				isPressed = false;
		    	}
			});
		
		}
	
	@Override
	public void act(float delta) {
		
		}
	
	@Override
	public void draw(Batch batch, float delta) {
		
		batch.end();

	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.setTransformMatrix(batch.getTransformMatrix());
	    renderer.translate(getX(), getY(), 0);

	    if(!isPressed)
	    	renderer.begin(ShapeType.Line);
	    else
	    	renderer.begin(ShapeType.Filled);
	    renderer.setColor(Color.BLACK);
	    renderer.rect(0, 0, getWidth(), getHeight());
	    renderer.end();

	    batch.begin();
		
		}
	
	public float getHertz() {
		return hertz;
		}
	}
