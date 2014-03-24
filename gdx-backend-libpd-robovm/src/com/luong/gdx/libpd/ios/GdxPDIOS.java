package com.luong.gdx.libpd.ios;

import org.robovm.cocoatouch.foundation.NSBundle;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.rt.bro.ptr.VoidPtr;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.ios.bridge.PdAudioController;
import com.luong.gdx.libpd.ios.bridge.PdBase;
import com.luong.gdx.libpd.ios.bridge.PdDispatcher;

public class GdxPDiOS implements GdxPD {
	
	protected static PdAudioController audioController;
	
	protected static PdDispatcher dispatcher;

	protected VoidPtr patchPtr;
	
	public GdxPDiOS() {
		
		}

	@Override
	public void init() {
		//_audioController = [[PdAudioController alloc] init];
		//if ([self.audioController configureAmbientWithSampleRate:44100
		//     numberChannels:2 mixingEnabled:YES] != PdAudioOK) {
		//	NSLog(@"failed to initialize audio components");
		//	}
		//dispatcher = [[PdDispatcher alloc] init];
		//[PdBase setDelegate:dispatcher];
		audioController = new PdAudioController();
		audioController.configureAmbientWithSampleRate(44100, 2, true);
		dispatcher = new PdDispatcher();
		PdBase.setDelegate(dispatcher);
		}
	
	@Override
	public void loadPatch(String patchName) {
		//patch = [PdBase openFile:@"tuner.pd"
		//path:[[NSBundle mainBundle] resourcePath]];
		//if (!patch) {
		//	NSLog(@"Failed to open patch!");
		//	}
		patchPtr = PdBase.openFile(new NSString(patchName),
									new NSString(NSBundle.getMainBundle().getResourcePath()));
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
	public void sendBang(String bang) {
		//[PdBase sendBangToReceiver:@"trigger"];
		PdBase.sendBangToReceiver(new NSString(bang));
		}

	public void sendFloat(String label, float number) {
		//[PdBase sendFloat:n toReceiver:@"midinote"];
		PdBase.sendFloat(number, new NSString(label));
		}
	}
