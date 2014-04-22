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

import org.robovm.cocoatouch.foundation.NSArray;
import org.robovm.cocoatouch.foundation.NSAutoreleasePool;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.cocoatouch.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.ios.GdxPDiOS;
import com.luong.gdx.libpd.ios.bindings.PdListener;

public class RobovmLauncher extends IOSApplication.Delegate implements PdListener {
	
	private GdxPD audio;
	
	private PianoTest game;
	
	@Override
	protected IOSApplication createApplication() {
		
		audio = new GdxPDiOS(44100, false, 2, true);
		((GdxPDiOS)audio).addiOSListener("light", this);
      ((GdxPDiOS)audio).addiOSListener("dark", this);
      game = new PianoTest(audio);
		
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		config.orientationLandscape = true;
		config.orientationPortrait = false;
		return new IOSApplication(game, config);
		}
	
	@Override
	public void didBecomeActive(UIApplication application) {
		super.didBecomeActive(application);
		audio.startAudio();
		}
	
	@Override
	public void willResignActive(UIApplication application) {
		audio.stopAudio();
		super.willResignActive(application);
		}
	
	@Override
	public void willTerminate(UIApplication application) {
		audio.dispose();
		super.willTerminate(application);
		}

	public static void main(String[] argv) {
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(argv, null, RobovmLauncher.class);
		pool.drain();
		}

   @Override
   public void receiveBangFromSource(NSString source) {
      game.switchTheme();
      }

   @Override
   public void receiveFloat(float received, NSString source) {
      
      }

   @Override
   public void receiveSymbol(NSString symbol, NSString source) {
      
      }

   @Override
   public void receiveList(NSArray<?> list, NSString source) {
      
      }

   @Override
   public void receiveMessage(NSString message,
                              NSArray<?> arguments,
                              NSString source) {
      
      }
	}
