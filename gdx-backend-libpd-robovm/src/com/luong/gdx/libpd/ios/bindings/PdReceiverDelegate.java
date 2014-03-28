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

import org.robovm.cocoatouch.foundation.NSObjectProtocol;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;

/**
 * // Receiver interface for printing and receiving messages from Pd<br>
 * <br>
 * @ protocol PdReceiverDelegate<PdListener>
 * 
 * I removed the extension to PdListener to easier things.
 */
@Library(Library.INTERNAL)
public interface PdReceiverDelegate extends NSObjectProtocol /*extends PdListener*/ {
	/**
	 * - (void)receivePrint:(NSString *)message;
	 */
	public void receivePrint(NSString message);
	
	static class Callbacks { 
		@Callback
        @BindSelector("receivePrint:")
        public static void receivePrint(PdReceiverDelegate __self__, Selector __cmd__, NSString delegate){
			__self__.receivePrint(delegate);
			}
		}
	}
