package com.luong.gdx.libpd.android;

import java.io.File;
import java.io.IOException;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.utils.IoUtils;

import android.content.Context;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.luong.gdx.libpd.GdxPD;

public class GdxPDAndroid implements GdxPD {
	
	private Context context;
	
	private PdUiDispatcher dispatcher;
	
	public GdxPDAndroid(Context ctx) {
		context = ctx;
		}

	@Override
	public void init() throws IOException {
		int sampleRate = AudioParameters.suggestSampleRate();
		PdAudio.initAudio(sampleRate, 0, 2, 8, true);
		dispatcher = new PdUiDispatcher();
		PdBase.setReceiver(dispatcher);
		}

	@Override
	public void dispose() {
		PdAudio.release();
		PdBase.release();
		}

	@Override
	public void loadPatch(String zipName, String patchName) throws IOException {
		File dir = context.getFilesDir();
		IoUtils.extractZipResource(Gdx.files.internal(zipName).read(), dir, true);
		File patchFile = new File(dir, patchName);
		PdBase.openPatch(patchFile.getAbsolutePath());
		}

	@Override
	public void startAudio() {
		PdAudio.startAudio(context);
		}

	@Override
	public void stopAudio() {
		PdAudio.stopAudio();
		}

	@Override
	public void sendBang(String bang) {
		Log.d("sendBang", bang);
		PdBase.sendBang(bang);
		}
	
	@Override
	public void sendFloat(String label, float number) {
		Log.d("sendFloat", label + ": " + number);
		PdBase.sendFloat(label, number);
		}
	}
