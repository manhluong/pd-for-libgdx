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

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.android.GdxPDAndroid;

public class MainActivity extends AndroidApplication {
	
	private GdxPD audio;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        audio = new GdxPDAndroid(this,
        							AudioParameters.suggestSampleRate(),
        							0,
        							2,
        							8,
        							true);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        initialize(new PianoTest(audio), cfg);
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
	}