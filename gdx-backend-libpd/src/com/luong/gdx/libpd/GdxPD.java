package com.luong.gdx.libpd;

public interface GdxPD {
	public void init();
	
	public void dispose();
	
	public void loadPatch();
	
	public void startAudio();
	
	public void stopAudio();
	
	public void sendBang(String bang);
	}
