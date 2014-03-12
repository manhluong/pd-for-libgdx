package com.luongbui.gdx.libpd.pianotest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.luongbui.gdx.libpd.pianotest.screen.PianoScreen;

public class PianoTest extends Game {
	
	private PianoScreen mainScreen;
	
	/**
	 * We keep only one mux per Game.
	 */
	private InputMultiplexer inputMux;
	
	@Override
	public void create() {
		mainScreen = new PianoScreen(this);
		inputMux = new InputMultiplexer();
		setScreen(mainScreen);
		}
	
	@Override
	public void render() {		
		super.render();
		}
	
	/**
	 * Used by the Screens to register thair stages.
	 * @param proc The new input processor.
	 */
	public void addProcessor(InputProcessor proc) {
		inputMux.addProcessor(proc);
		}
	}
