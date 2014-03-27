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
