package com.luong.gdx.libpd.ios.bridge;

import org.robovm.rt.bro.ValuedEnum;

public enum PdAudioStatus implements ValuedEnum {
	PdAudioOK(0),
	PdAudioError(-1),
	PdAudioPropertyChanged(1);

    private final int n;

    private PdAudioStatus(int n) {
    	this.n = n;
    	}
    
    public long value() {
    	return n;
    	}
	}
