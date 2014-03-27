package com.luong.gdx.libpd;

import java.io.IOException;

public interface GdxPD {
	
	/**
	 * Need a separate initialization method because some initialization params are platform-dependent.<br>
	 * For instance, Context concept is present in Android but not in iOS. Also, input channels are set differently.<br>
	 * <br>
	 * Call this method in the common project of libgdx.<br>
	 * @throws IOException
	 */
	public void init() throws IOException;
	
	public void dispose();
	
	public void loadPatch(String patchName) throws IOException;
	
	public void startAudio();
	
	public void stopAudio();
	
	/* SEND METHODS */
	
	public int sendBang(String bang);
	
	public int sendFloat(String label, float number);
	
	public int sendSymbol(String recv, String sym);
	
	/**
	 * 
	 * @param recv
	 * @param args list of arguments of type Integer, Float, or String
	 * @return
	 */
	public int sendList(String recv, Object... args);
	
	/**
	 * 
	 * @param recv
	 * @param msg
	 * @param args list of arguments of type Integer, Float, or String
	 * @return
	 */
	public int sendMessage(String recv, String msg, Object... args);
	}
