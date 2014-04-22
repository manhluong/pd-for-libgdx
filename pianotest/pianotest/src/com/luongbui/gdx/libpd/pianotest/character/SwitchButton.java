package com.luongbui.gdx.libpd.pianotest.character;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.luongbui.gdx.libpd.pianotest.PianoTest;

public class SwitchButton extends Actor {
   
   private final PianoTest game;
   
   private ShapeRenderer renderer;
   
   private boolean isLight;
   
   public SwitchButton(float x,
                        float y,
                        float width,
                        float height,
                        final PianoTest game) {

      renderer = new ShapeRenderer();
      isLight = true;
      this.game = game;

      setBounds(x, y, width, height);
      addListener(new InputListener() {
         @Override
         public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
            isLight = !isLight;
            game.getAudioEngine().sendBang("switch");
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

      if(isLight)
         renderer.begin(ShapeType.Line);
      else
         renderer.begin(ShapeType.Filled);
      renderer.setColor(Color.BLACK);
      renderer.rect(0, 0, getWidth(), getHeight());
      renderer.end();

      batch.begin();
      }
   }
