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

package com.luong.gdx.libpd.desktop;

import java.io.IOException;

import com.luong.gdx.libpd.GdxPD;
import com.luong.gdx.libpd.PdCommonListener;

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
	public void loadPatch(String basePureDataAssetsDir, String patchName) throws IOException {
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
	public void addListener(String symbol, PdCommonListener listener) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void removeListener(String symbol, PdCommonListener listener) {
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

	@Override
   public int arraySize(String name) {
	   // TODO Auto-generated method stub
	   return 0;
   }

	@Override
   public int readArray(float[] destination,
   								int destOffset,
   								String source,
   								int srcOffset,
   								int n) {
	   // TODO Auto-generated method stub
	   return 0;
   	}

	@Override
   public int writeArray(String destination,
   								int destOffset,
   								float[] source,
   								int srcOffset,
   								int n) {
	   // TODO Auto-generated method stub
	   return 0;
   	}

	}
