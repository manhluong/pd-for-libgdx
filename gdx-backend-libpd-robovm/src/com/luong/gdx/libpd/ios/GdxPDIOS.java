package com.luong.gdx.libpd.ios;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.ios.bridge.PdAudioController;
import com.luong.gdx.libpd.ios.bridge.PdBase;
import com.luong.gdx.libpd.ios.bridge.PdDispatcher;

public class GdxPDiOS implements GdxPD {
	
	protected static PdAudioController audioController;
	
	protected static PdDispatcher dispatcher;
	
	public GdxPDiOS() {
		
		}

	@Override
	public void init() {
		// TODO
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
	public void dispose() {
		// TODO Auto-generated method stub
		//[PdBase closeFile:patch];
		//[PdBase setDelegate:nil];
		}

	@Override
	public void loadPatch(String patchName) {
		// TODO Auto-generated method stub
		//patch = [PdBase openFile:@"tuner.pd"
		//path:[[NSBundle mainBundle] resourcePath]];
		//if (!patch) {
		//	NSLog(@"Failed to open patch!");
		//	}
		}

	@Override
	public void startAudio() {
		// TODO Auto-generated method stub
		// self.audioController.active = YES;
		}

	@Override
	public void stopAudio() {
		// TODO Auto-generated method stub
		// self.audioController.active = NO;
		}

	@Override
	public void sendBang(String bang) {
		// TODO Auto-generated method stub
		//[PdBase sendBangToReceiver:@"trigger"];
		}

	public void sendFloat(String label, float number) {
		// TODO
		//[PdBase sendFloat:n toReceiver:@"midinote"];
		}
	}
