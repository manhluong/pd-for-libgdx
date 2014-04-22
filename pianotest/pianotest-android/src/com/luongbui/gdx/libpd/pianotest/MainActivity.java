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

import org.puredata.android.io.AudioParameters;
import org.puredata.core.PdListener;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.android.GdxPDAndroid;

public class MainActivity extends AndroidApplication implements PdListener {
	
	private GdxPD audio;
	
	private PianoTest game;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        
       audio = new GdxPDAndroid(this,
        							AudioParameters.suggestSampleRate(),
        							0,
        							2,
        							8,
        							true);
       ((GdxPDAndroid)audio).addAndroidListener("light", this);
       ((GdxPDAndroid)audio).addAndroidListener("dark", this);
       game = new PianoTest(audio);
        
       AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
       initialize(game, cfg);
       }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	audio.startAudio();
    	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	audio.stopAudio();
    	}
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	audio.dispose();
    	}

   @Override
   public void receiveBang(String source) {
      Log.d("receiveBang", source);
      }

   @Override
   public void receiveFloat(String arg0, float arg1) {
      
      }

   @Override
   public void receiveList(String arg0, Object... arg1) {
      
      }

   @Override
   public void receiveMessage(String arg0, String arg1, Object... arg2) {
      
      }

   @Override
   public void receiveSymbol(String arg0, String arg1) {
      
      }
	}