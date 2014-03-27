package com.luong.gdx.libpd.android;

import java.io.File;
import java.io.IOException;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.PdListener;

import android.content.Context;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.PdCommonListener;

public class GdxPDAndroid implements GdxPD {
	
	final protected Context context;
	
	protected static PdUiDispatcher dispatcher;
	
	protected static int sampleRate;
	protected static int inChannels;
	protected static int outChannels;
	protected static int ticksPerBuffer;
	protected static boolean restart;
	
	public GdxPDAndroid(final Context ctx) {
		context = ctx;
		sampleRate = AudioParameters.suggestSampleRate();
		inChannels = 0;
		outChannels = 2;
		ticksPerBuffer = 8;
		restart = true;
		dispatcher = new PdUiDispatcher();
		PdBase.setReceiver(dispatcher);
		}
	
	public GdxPDAndroid(final Context ctx,
							int rate,
							int inCh,
							int outCh,
							int ticks,
							boolean rest) {
		this(ctx);
		sampleRate = rate;
		inChannels = inCh;
		outChannels = outCh;
		ticksPerBuffer = ticks;
		restart = rest;
		}
	
	/**
	 * Useful if you don't want to set the parameters at instantiation.
	 * @param rate
	 * @param inCh
	 * @param outCh
	 * @param ticks
	 * @param rest
	 */
	public static void setAudioParams(int rate,
										int inCh,
										int outCh,
										int ticks,
										boolean rest) {
		sampleRate = rate;
		inChannels = inCh;
		outChannels = outCh;
		ticksPerBuffer = ticks;
		restart = rest;
		}
	
	
	public void addAndroidListener(String symbol, org.puredata.core.PdListener listener) {
		dispatcher.addListener(symbol, listener);
		}
	
	public void removeAndroidListener(String symbol, org.puredata.core.PdListener listener) {
		dispatcher.removeListener(symbol, listener);
		}
	
	@Override
	public void addListener(String symbol, PdCommonListener listener) {
		addAndroidListener(symbol, (org.puredata.core.PdListener) listener);
		}
	
	@Override
	public void removeListener(String symbol, PdCommonListener listener) {
		removeAndroidListener(symbol, (org.puredata.core.PdListener) listener);
		}

	@Override
	public void init() throws IOException {
		//Log.d("init()", "Init!");
		PdAudio.initAudio(sampleRate, inChannels, inChannels, ticksPerBuffer, restart);
		}

	@Override
	public void dispose() {
		//Log.d("dispose()", "Dispose!");
		PdBase.setReceiver(null);
		PdAudio.release();
		PdBase.release();
		}

	/**
	 * Call this inside create() method of GDX lifecycle.<br>
	 * Ensure that the framework created a Gdx object when called.
	 */
	@Override
	public void loadPatch(String patchName) throws IOException {
		//Log.d("loadPatch()", "Load!");
		File dir = context.getFilesDir();
		//if(zipName!=null)
		//	IoUtils.extractZipResource(Gdx.files.internal(zipName).read(), dir, true);
		File patchFile = new File(dir, patchName);
		PdBase.openPatch(patchFile.getAbsolutePath());
		}

	@Override
	public void startAudio() {
		//Log.d("startAudio()", "Start!");
		PdAudio.startAudio(context);
		}

	@Override
	public void stopAudio() {
		//Log.d("stopAudio()", "Stop!");
		PdAudio.stopAudio();
		}

	@Override
	public int sendBang(String bang) {
		//Log.d("sendBang()", bang);
		return PdBase.sendBang(bang);
		}
	
	@Override
	public int sendFloat(String label, float number) {
		//Log.d("sendFloat()", label + ": " + number);
		return PdBase.sendFloat(label, number);
		}
	
	@Override
	public int sendSymbol(String recv, String sym) {
		return PdBase.sendSymbol(recv, sym);
		}

	@Override
	public int sendList(String recv, Object... args) {
		return PdBase.sendList(recv, args);
		}

	@Override
	public int sendMessage(String recv, String msg, Object... args) {
		return PdBase.sendMessage(recv, msg, args);
		}
	}
