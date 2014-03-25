package com.luong.gdx.libpd.ios;

import java.io.IOException;

import org.robovm.cocoatouch.foundation.NSBundle;
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
	}
