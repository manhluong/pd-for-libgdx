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

package com.luong.gdx.libpd.ios;

import java.io.IOException;

import org.robovm.cocoatouch.foundation.NSArray;
import org.robovm.cocoatouch.foundation.NSBundle;
import org.robovm.cocoatouch.foundation.NSNumber;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.ptr.FloatPtr;
import org.robovm.rt.bro.ptr.VoidPtr;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.PdCommonListener;
import com.luong.gdx.libpd.ios.bindings.PdAudioController;
import com.luong.gdx.libpd.ios.bindings.PdAudioStatus;
import com.luong.gdx.libpd.ios.bindings.PdBase;
import com.luong.gdx.libpd.ios.bindings.PdDispatcher;
import com.luong.gdx.libpd.ios.bindings.PdListener;

public class GdxPDiOS implements GdxPD {
	
	protected static PdAudioController audioController;
	
	protected static PdDispatcher dispatcher;

	protected static VoidPtr patchPtr;
	
	protected static int sampleRate;
	
	protected static boolean useInChannels;
	
	protected static int numChannels;
	
	protected static boolean mixingEnabled;
	
	public GdxPDiOS() {
		sampleRate = 44100;
		useInChannels = false;
		numChannels = 2;
		mixingEnabled = true;
		dispatcher = new PdDispatcher();
		PdBase.setDelegate(dispatcher);
		}
	
	public GdxPDiOS(int rate, boolean inChannels, int outChannels, boolean mixing) {
		this();
		sampleRate = rate;
		useInChannels = inChannels;
		numChannels = outChannels;
		mixingEnabled = mixing;
		}
	
	/**
	 * Useful if you don't want to set the parameters at instantiation.
	 * @param rate Sample rate.
	 * @param
	 * @param outChannels Number of output channels.
	 * @param mixing
	 */
	public static void setAudioParams(int rate,
										boolean inChannels,
										int outChannels,
										boolean mixing) {
		sampleRate = rate;
		useInChannels = inChannels;
		numChannels = outChannels;
		mixingEnabled = mixing;
		}
	
	public void addiOSListener(String symbol, PdListener listener) {
		dispatcher.addListener(listener, new NSString(symbol));
		}
	
	public void removeiOSListener(String symbol, PdListener listener) {
		dispatcher.removeListener(listener, new NSString(symbol));
		}
	
	@Override
	public void addListener(String symbol, PdCommonListener listener) {
		addiOSListener(symbol, (com.luong.gdx.libpd.ios.bindings.PdListener) listener);
		}
	
	@Override
	public void removeListener(String symbol, PdCommonListener listener) {
		removeiOSListener(symbol, (com.luong.gdx.libpd.ios.bindings.PdListener) listener);
		}

	@Override
	public void init() throws IOException {
		audioController = new PdAudioController();
		if(!useInChannels) {
			if(audioController.configureAmbientWithSampleRate(sampleRate,
																numChannels,
																mixingEnabled) != PdAudioStatus.PdAudioOK)
				throw new IOException("Failed to initialize audio components!");
			}
		else {
			if(audioController.configurePlaybackWithSampleRate(sampleRate,
																numChannels,
																useInChannels,
																mixingEnabled) != PdAudioStatus.PdAudioOK)
				throw new IOException("Failed to initialize audio components!");
			}
		}
	
	@Override
	public void loadPatch(String patchName) throws IOException {
		patchPtr = PdBase.openFile(new NSString(patchName),
									new NSString(NSBundle.getMainBundle().getResourcePath()));
		if(patchPtr == null)
			throw new IOException("Failed to open patch!");
		}

	@Override
	public void dispose() {
		//[PdBase closeFile:patch];
		//[PdBase setDelegate:nil];
		PdBase.closeFile(patchPtr);
		PdBase.setDelegate(null);
		audioController.dealloc();
		}

	@Override
	public void startAudio() {
		// self.audioController.active = YES;
		audioController.setActive(true);
		}

	@Override
	public void stopAudio() {
		// self.audioController.active = NO;
		audioController.setActive(false);
		}

	@Override
	public int sendBang(String bang) {
		return PdBase.sendBangToReceiver(new NSString(bang));
		}

	public int sendFloat(String label, float number) {
		return PdBase.sendFloat(number, new NSString(label));
		}

	@Override
	public int sendSymbol(String recv, String sym) {
		return PdBase.sendSymbol(new NSString(sym), new NSString(recv));
		}

	/**
	 * In the Android version of libpd, it is stated that args is a "list of arguments of type Integer, Float, or String".<br>
	 * <br>
	 * In the iOS version of libpd, we can see from this method of PdBase.m:<br>
	 * <br>
	 * 		static void encodeList(NSArray *list)<br>
	 * <br>
	 * That the list can have NSObject of type NSNumber (from which a float is extracted) or an NSString.<br>
	 * <br>
	 * So what I do internally is to check all elements of args and convert them to NSNumber if they are
	 * a Float or an Integer and to NSString if they are a String.<br>
	 * <br>
	 * It seems that in case of errors, no exceptions are thrown nor return values are returned.<br>
	 */
	@Override
	public int sendList(String recv, Object... args) {
		NSArray<NSObject> list = new NSArray<NSObject>();
		for(Object obj : args) {
			if(obj instanceof Integer)
				list.add( NSNumber.valueOf( ((Integer)obj).intValue() ) );
			else if(obj instanceof Float)
				list.add( NSNumber.valueOf( ((Integer)obj).floatValue() ) );
			else if(obj instanceof String)
				list.add(new NSString((String)obj));
			}
		return PdBase.sendList(list, new NSString(recv));
		}

	/**
	 * In the Android version of libpd, it is stated that args is a "list of arguments of type Integer, Float, or String".<br>
	 * <br>
	 * In the iOS version of libpd, we can see from this method of PdBase.m:<br>
	 * <br>
	 * 		static void encodeList(NSArray *list)<br>
	 * <br>
	 * That the list can have NSObject of type NSNumber (from which a float is extracted) or an NSString.<br>
	 * <br>
	 * So what I do internally is to check all elements of args and convert them to NSNumber if they are
	 * a Float or an Integer and to NSString if they are a String.<br>
	 * <br>
	 * It seems that in case of errors, no exceptions are thrown nor return values are returned.<br>
	 */
	@Override
	public int sendMessage(String recv, String msg, Object... args) {
		NSArray<NSObject> list = new NSArray<NSObject>();
		for(Object obj : args) {
			if(obj instanceof Integer)
				list.add( NSNumber.valueOf( ((Integer)obj).intValue() ) );
			else if(obj instanceof Float)
				list.add( NSNumber.valueOf( ((Integer)obj).floatValue() ) );
			else if(obj instanceof String)
				list.add(new NSString((String)obj));
			}
		return PdBase.sendMessage(new NSString(msg), list, new NSString(recv));
		}

	@Override
   public int arraySize(String name) {
	   return PdBase.arraySizeForArrayNamed(new NSString(name));
   	}

	/**
	 * TODO I have to rethink this, as I am allocating another array. Each time.
	 */
	@Override
   public int readArray(float[] destination,
   								int destOffset,
   								String source,
   								int srcOffset,
   								int n) {
		if (destOffset < 0 || destOffset + n > destination.length)
	      return -2;
		FloatPtr destBuf = Struct.allocate(FloatPtr.class, n);
		int res = PdBase.copyArrayNamed(new NSString(source), srcOffset, destBuf, n);
		for(int index = destOffset; index<n; index++) {
			destination[index] = destBuf.get();
			destBuf.next();
			}
	   return res;
   	}

	/**
	 * TODO I have to rethink this, as I am allocating another array. Each time.
	 */
	@Override
   public int writeArray(String destination,
   								int destOffset,
   								float[] source,
   								int srcOffset,
   								int n) {
		if (srcOffset < 0 || srcOffset + n > source.length)
			return -2;
		FloatPtr srcBuf = Struct.allocate(FloatPtr.class, n);
		for(int index = srcOffset; index<n; index++) {
			srcBuf.set(source[index]);
			srcBuf.next();
			}
	   return PdBase.copyArray(srcBuf, new NSString(destination), destOffset, n);
   	}
	}
