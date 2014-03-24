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
		}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void loadPatch(String patchName) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void startAudio() {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void stopAudio() {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void sendBang(String bang) {
		// TODO Auto-generated method stub
		
		}

	public void sendFloat(String label, float number) {
		// TODO
		}
	}
