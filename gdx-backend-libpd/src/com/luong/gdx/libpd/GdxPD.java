package com.luong.gdx.libpd;

import java.io.IOException;

public interface GdxPD {
	public void init() throws IOException;
	
	public void dispose();
	
	public void loadPatch(String zipName, String patchName) throws IOException;
	
	public void startAudio();
	
	public void stopAudio();
	
	public void sendBang(String bang);
	}
