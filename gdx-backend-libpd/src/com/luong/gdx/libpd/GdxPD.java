package com.luong.gdx.libpd;

import java.io.IOException;

public interface GdxPD {
	
	/**
	 * Need a separate initialization method because some initialization params are platform-dependent.<br>
	 * <br>
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
	
	/**
	 * The listener <b>most implement the platform-specific PdListener too</b>.<br>
	 * The platform-specific implementation simply re-cast the listener to the platform one.<br>
	 * <br>
	 * Although is here, it is probably more elegant to add / remove listeners in the platform-specific projects and not in the common one.<br>
	 * So if you don't like to implement multiple interfaces, just call the platform-specific add / remove directly.<br>
	 * <br>
	 * @param symbol
	 * @param listener Most implements PdCommonListener <b>and</b> the platform-specific PdListener. Otherwise a ClassCastException is thrown.
	 */
	public void addListener(String symbol, PdCommonListener listener);
	
	/**
	 * The listener <b>most implement the platform-specific PdListener too</b>.<br>
	 * The platform-specific implementation simply re-cast the listener to the platform one.<br>
	 * <br>
	 * Although is here, it is probably more elegant to add / remove listeners in the platform-specific projects and not in the common one.<br>
	 * So if you don't like to implement multiple interfaces, just call the platform-specific add / remove directly.<br>
	 * <br>
	 * @param symbol
	 * @param listener Most implements PdCommonListener <b>and</b> the platform-specific PdListener. Otherwise a ClassCastException is thrown.
	 */
	public void removeListener(String symbol, PdCommonListener listener);
	
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
