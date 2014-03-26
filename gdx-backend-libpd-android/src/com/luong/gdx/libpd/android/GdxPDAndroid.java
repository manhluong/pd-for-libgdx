package com.luong.gdx.libpd.android;

import java.io.File;
import java.io.IOException;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;

import android.content.Context;

import com.luong.gdx.libpd.GdxPD;

public class GdxPDAndroid implements GdxPD {
	
	final protected Context context;
	
	protected static PdUiDispatcher dispatcher;
	
	public GdxPDAndroid(final Context ctx) {
		context = ctx;
		}

	/**
	 * Call this in the constructor of the object that holds the implementation of the custom code interface.
	 */
	@Override
	public void init() throws IOException {
		//Log.d("init()", "Init!");
		int sampleRate = AudioParameters.suggestSampleRate();
		PdAudio.initAudio(sampleRate, 0, 2, 8, true);
		dispatcher = new PdUiDispatcher();
		PdBase.setReceiver(dispatcher);
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
