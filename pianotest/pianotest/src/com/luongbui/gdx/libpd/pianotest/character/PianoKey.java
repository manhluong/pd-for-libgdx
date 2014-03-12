package com.luongbui.gdx.libpd.pianotest.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PianoKey extends Actor {
	
	private ShapeRenderer renderer;
	
	public PianoKey(float x,
					float y,
					float width,
					float height) {
		renderer = new ShapeRenderer();
		setBounds(x, y, width, height);
		}
	
	@Override
	public void act(float delta) {
		//TODO
		}
	
	@Override
	public void draw(Batch batch, float delta) {
		
		batch.end();

	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.setTransformMatrix(batch.getTransformMatrix());
	    renderer.translate(getX(), getY(), 0);

	    renderer.begin(ShapeType.Line);
	    renderer.setColor(Color.BLACK);
	    renderer.rect(0, 0, getWidth(), getHeight());
	    renderer.end();

	    batch.begin();
		
		}
	}
