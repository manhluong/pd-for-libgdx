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

package com.luong.gdx.libpd.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.puredata.android.io.AudioParameters;
import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;

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
		PdAudio.initAudio(sampleRate, inChannels, outChannels, ticksPerBuffer, restart);
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
	 * Ensure that the framework created a Gdx object when called.<br>
	 * 
	 * TODO Copy Assets somewhere!
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
	
	/**
	 * From here: http://stackoverflow.com/questions/4447477/android-how-to-copy-files-in-assets-to-sdcard
	 */
	private void copyAssets() {
		android.content.res.AssetManager assetManager = context.getAssets();
		String[] files = null;
		try {
			files = assetManager.list("");
			}
		catch (IOException e) {
			android.util.Log.e("copyAssets()", "Failed to get asset file list.", e);
			}
		for(String filename : files) {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = assetManager.open(filename);
				File outFile = new File(context.getFilesDir(), filename);
				out = new FileOutputStream(outFile);
				copyFile(in, out);
				in.close();
				in = null;
				out.flush();
				out.close();
				out = null;
				}
			catch(IOException e) {
			    android.util.Log.e("copyAssets()", "Failed to copy asset file: " + filename, e);
				}       
			}
	    }
	
    private void copyFile(InputStream in, OutputStream out) throws IOException {
       byte[] buffer = new byte[1024];
       int read;
       while((read = in.read(buffer)) != -1) {
          out.write(buffer, 0, read);
          }
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
		android.util.Log.d("sendBang()", bang);
		return PdBase.sendBang(bang);
		}
	
	@Override
	public int sendFloat(String label, float number) {
		android.util.Log.d("sendFloat()", label + ": " + number);
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

	@Override
   public int arraySize(String name) {
	   return PdBase.arraySize(name);
   	}

	@Override
   public int readArray(float[] destination,
   							int destOffset,
   							String source,
   							int srcOffset,
   							int n) {
	   return PdBase.readArray(destination, destOffset, source, srcOffset, n);
		}

	@Override
   public int writeArray(String destination,
   								int destOffset,
   								float[] source,
   								int srcOffset,
   								int n) {
	   return PdBase.writeArray(destination, destOffset, source, srcOffset, n);
   	}
	}
