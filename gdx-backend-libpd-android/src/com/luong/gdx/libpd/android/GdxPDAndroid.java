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
	
	/**
	 * Base sub folder of Assets folder containing all Pure Data resource files (patches, audio files, ecc.).<br>
	 * This folder will be copied in Context.getFilesDir() in load phase.<br>
	 */
	protected static String basePureDataAssetsDir;
	
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
							boolean rest,
							String pureDataDir) {
		this(ctx);
		sampleRate = rate;
		inChannels = inCh;
		outChannels = outCh;
		ticksPerBuffer = ticks;
		restart = rest;
		basePureDataAssetsDir = pureDataDir;
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
	 * <br>
	 * Before opening the patch, it copies all assets at the given basePureDataAssetsDir, through copyAssets().
	 */
	@Override
	public void loadPatch(String patchName) throws IOException {
		//Log.d("loadPatch()", "Load!");
		//if(zipName!=null)
		//	IoUtils.extractZipResource(Gdx.files.internal(zipName).read(), dir, true);
	   copyAssets(basePureDataAssetsDir);
	   File dir = context.getFilesDir();
      dir = new File(dir.getAbsoluteFile() + File.separator + basePureDataAssetsDir);
      dir.mkdir();
		File patchFile = new File(dir, patchName);
		PdBase.openPatch(patchFile.getAbsolutePath());
		}
	
	/**
	 * From here: http://stackoverflow.com/questions/4447477/android-how-to-copy-files-in-assets-to-sdcard
	 * 
	 * @param subPath A relative path within the assets. Can be a directory: "pure_data".
	 * @throws IOException
	 */
	protected void copyAssets(String subPath) throws IOException {
		android.content.res.AssetManager assetManager = context.getAssets();
		String[] files = assetManager.list(subPath);
		if (files.length == 0) // It's a file.
		   copyFileToDataDir(assetManager, subPath);
		else {
		   // Create the new output directory to store the copied files.
		   File outDir = new File(context.getFilesDir(), subPath);
		   if(!outDir.exists())
		      outDir.mkdir();
		   // Copy each asset.
		   // From Android doc, each element of files[] is relative to "subPath".
		   for(String fileName : files) {
		      copyAssets(subPath + File.separator + fileName);
			   }
		   }
		}
	
	/**
	 * From here: http://stackoverflow.com/questions/4447477/android-how-to-copy-files-in-assets-to-sdcard
	 * 
	 * @param assetManager
	 * @param fileName
	 * @throws IOException
	 */
	protected void copyFileToDataDir(android.content.res.AssetManager assetManager,
	                                    String fileName) throws IOException {
	   InputStream in = assetManager.open(fileName);
      File outFile = new File(context.getFilesDir(), fileName);
      OutputStream out = new FileOutputStream(outFile);
      streamCopy(in, out);
      in.close();
      in = null;
      out.flush();
      out.close();
      out = null;
	   }

	/**
	 * From here: http://stackoverflow.com/questions/4447477/android-how-to-copy-files-in-assets-to-sdcard
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	protected void streamCopy(InputStream in, OutputStream out) throws IOException {
	   byte[] buffer = new byte[1024];
	   int read;
	   for(;(read = in.read(buffer)) != -1;)
	      out.write(buffer, 0, read);
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
