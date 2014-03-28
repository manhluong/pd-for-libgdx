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

package com.luong.gdx.libpd.ios.bindings;

import org.robovm.cocoatouch.foundation.NSArray;
import org.robovm.cocoatouch.foundation.NSObjectProtocol;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;

import com.luong.gdx.libpd.PdCommonListener;

/**
 * @ protocol PdListener
 */
@Library(Library.INTERNAL)
public interface PdListener extends NSObjectProtocol, PdCommonListener {
	/**
	 * - (void)receiveBangFromSource:(NSString *)source;
	 */
	public void receiveBangFromSource(NSString source);
	
	/**
	 * - (void)receiveFloat:(float)received
	 * 			 fromSource:(NSString *)source;
	 */
	public void receiveFloat(float received, NSString source);
	
	/**
	 * - (void)receiveSymbol:(NSString *)symbol
	 * 			  fromSource:(NSString *)source;
	 */
	public void receiveSymbol(NSString symbol, NSString source);
	
	/**
	 * - (void)receiveList:(NSArray *)list
	 * 	        fromSource:(NSString *)source;
	 */
	public void receiveList(NSArray<?> list, NSString source);
	
	/**
	 * - (void)receiveMessage:(NSString *)message
	 * 	        withArguments:(NSArray *)arguments
	 * 			   fromSource:(NSString *)source;
	 */
	public void receiveMessage(NSString message, NSArray<?> arguments, NSString source);
	
	static class Callbacks {
		
		@Callback
        @BindSelector("receiveBangFromSource:")
        public static void receiveBangFromSource(PdListener __self__, Selector __cmd__, NSString source){
			__self__.receiveBangFromSource(source);
			}
		
		@Callback
        @BindSelector("receiveFloat:fromSource:")
        public static void receiveFloat(PdListener __self__, Selector __cmd__, float received, NSString source){
			__self__.receiveFloat(received, source);
			}
		
		@Callback
        @BindSelector("receiveSymbol:fromSource:")
        public static void receiveSymbol(PdListener __self__, Selector __cmd__, NSString symbol, NSString source){
			__self__.receiveSymbol(symbol, source);
			}
		
		@Callback
        @BindSelector("receiveList:fromSource:")
        public static void receiveList(PdListener __self__, Selector __cmd__, NSArray<?> list, NSString source){
			__self__.receiveList(list, source);
			}
		
		@Callback
        @BindSelector("receiveMessage:withArguments:fromSource:")
        public static void receiveMessage(PdListener __self__, Selector __cmd__, NSString message, NSArray<?> arguments, NSString source){
			__self__.receiveMessage(message, arguments, source);
			}
		
		}
	}
