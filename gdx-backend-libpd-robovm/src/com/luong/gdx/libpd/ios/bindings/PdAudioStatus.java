/*******************************************************************************
 * Copyright 2014 Manh Luong   Bui.
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

package com.luong.gdx.libpd.ios.bindings;

import org.robovm.rt.bro.ValuedEnum;

public enum PdAudioStatus implements ValuedEnum {
	/**
	 * // success
	 */
	PdAudioOK(0),
	
	/**
	 * // unrecoverable error
	 */
	PdAudioError(-1),
	
	/**
	 * // some properties have changed to run correctly (not fatal)
	 */
	PdAudioPropertyChanged(1);

    private final int n;

    private PdAudioStatus(int n) {
    	this.n = n;
    	}
    
    public long value() {
    	return n;
    	}
	}
