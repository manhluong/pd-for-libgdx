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

import org.robovm.cocoatouch.foundation.NSAutoreleasePool;
import org.robovm.cocoatouch.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.ios.GdxPDIOS;

public class RobovmLauncher extends IOSApplication.Delegate {
	
	private GdxPD audio;
	
	@Override
	protected IOSApplication createApplication() {
		
		audio = new GdxPDIOS();
		
		IOSApplicationConfiguration config = new IOSApplicationConfiguration();
		config.orientationLandscape = true;
		config.orientationPortrait = false;
		return new IOSApplication(new PianoTest(audio), config);
		}

	public static void main(String[] argv) {
		NSAutoreleasePool pool = new NSAutoreleasePool();
		UIApplication.main(argv, null, RobovmLauncher.class);
		pool.drain();
		}
	}
