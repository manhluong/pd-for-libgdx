package com.luong.gdx.libpd.ios;

import java.io.IOException;

import org.robovm.cocoatouch.foundation.NSArray;
import org.robovm.cocoatouch.foundation.NSBundle;
import org.robovm.cocoatouch.foundation.NSNumber;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.rt.bro.ptr.VoidPtr;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.ios.bindings.PdAudioController;
import com.luong.gdx.libpd.ios.bindings.PdAudioStatus;
import com.luong.gdx.libpd.ios.bindings.PdBase;
import com.luong.gdx.libpd.ios.bindings.PdDispatcher;

public class GdxPDiOS implements GdxPD {
	
	protected static PdAudioController audioController;
	
	protected static PdDispatcher dispatcher;

	protected VoidPtr patchPtr;
	
	protected int sampleRate;
	
	protected int numChannels;
	
	protected boolean mixingEnabled;
	
	public GdxPDiOS() {
		sampleRate = 44100;
		numChannels = 2;
		mixingEnabled = true;
		}
	
	public GdxPDiOS(int rate, int channels, boolean mixing) {
		sampleRate = rate;
		numChannels = channels;
		mixingEnabled = mixing;
		}
	
	/**
	 * Useful if you don't want to set the parameters at instantiation.
	 * @param rate Sample rate.
	 * @param channels Number of channels.
	 * @param mixing
	 */
	public void setAudioParams(int rate, int channels, boolean mixing) {
		sampleRate = rate;
		numChannels = channels;
		mixingEnabled = mixing;
		}

	@Override
	public void init() throws IOException {
		audioController = new PdAudioController();
		if(audioController.configureAmbientWithSampleRate(sampleRate, numChannels, mixingEnabled) != PdAudioStatus.PdAudioOK) {
			throw new IOException("Failed to initialize audio components!");
			}
		else {
			dispatcher = new PdDispatcher();
			PdBase.setDelegate(dispatcher);
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
	 * That the list can have NSObject of type NSNumber (from wich a float is extracted) or an NSString.<br>
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
	}
