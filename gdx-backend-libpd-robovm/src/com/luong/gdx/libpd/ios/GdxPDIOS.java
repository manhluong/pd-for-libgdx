package com.luong.gdx.libpd.ios;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.ios.bridge.PdAudioController;

public class GdxPDiOS implements GdxPD {
	
	protected static PdAudioController controller;
	
	public GdxPDiOS() {
		controller = new PdAudioController();
		}

	@Override
	public void init() {
		// TODO
		//_audioController = [[PdAudioController alloc] init];
		//if ([self.audioController configureAmbientWithSampleRate:44100
		//     numberChannels:2 mixingEnabled:YES] != PdAudioOK) {
		//	NSLog(@"failed to initialize audio components");
		//	}
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
		//dispatcher = [[PdDispatcher alloc] init];
		//[PdBase setDelegate:dispatcher];
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
