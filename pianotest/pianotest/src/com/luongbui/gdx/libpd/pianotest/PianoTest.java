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

package com.luongbui.gdx.libpd.pianotest;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.luong.gdx.libpd.GdxPD;
import com.luongbui.gdx.libpd.pianotest.screen.PianoScreen;

public class PianoTest extends Game {
	
	private PianoScreen mainScreen;
	
	private GdxPD audio;
	
	public PianoTest() {
		audio = null;
		}
	
	public PianoTest(GdxPD pd) {
		audio = pd;
		try {
			audio.init();
			}
		catch (IOException e) {
			e.printStackTrace();
			}
		}
	
	@Override
	public void create() {
		mainScreen = new PianoScreen(this);
		try {
			audio.loadPatch("pure_data", "piano_test.pd");
			}
		catch (IOException e) {
			e.printStackTrace();
			}
		setScreen(mainScreen);
		}
	
	@Override
	public void render() {		
		super.render();
		}
	
	public GdxPD getAudioEngine() {
		return audio;
		}
	
	public void switchTheme() {
	   mainScreen.switchTheme();
	   }
	}
