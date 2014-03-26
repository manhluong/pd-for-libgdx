package com.luong.gdx.libpd.desktop;

import java.io.IOException;

import com.luong.gdx.libpd.GdxPD;

public class GdxPDDesktop implements GdxPD {

	@Override
	public void init() throws IOException {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void loadPatch(String patchName) throws IOException {
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
	public int sendBang(String bang) {
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public int sendFloat(String label, float number) {
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public int sendSymbol(String recv, String sym) {
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public int sendList(String recv, Object... args) {
		// TODO Auto-generated method stub
		return 0;
		}

	@Override
	public int sendMessage(String recv, String msg, Object... args) {
		// TODO Auto-generated method stub
		return 0;
		}

	}
