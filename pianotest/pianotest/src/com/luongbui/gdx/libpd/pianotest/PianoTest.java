package com.luongbui.gdx.libpd.pianotest;

import com.badlogic.gdx.Game;
import com.luongbui.gdx.libpd.pianotest.screen.PianoScreen;

public class PianoTest extends Game {
	
	private PianoScreen mainScreen;
	
	@Override
	public void create() {
		mainScreen = new PianoScreen(this);
		setScreen(mainScreen);
		}
	
	@Override
	public void render() {		
		super.render();
		}
	}
