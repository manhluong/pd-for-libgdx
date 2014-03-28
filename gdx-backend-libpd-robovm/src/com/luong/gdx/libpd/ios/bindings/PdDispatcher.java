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

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

/**
 * @ interface PdDispatcher : NSObject<PdReceiverDelegate>
 */
@Library(Library.INTERNAL)
@NativeClass
public class PdDispatcher extends NSObject implements PdReceiverDelegate {
	
	static {
		ObjCRuntime.bind(PdDispatcher.class);
		}
	
	public PdDispatcher() {
		super((SkipInit)null);
		initObject(init());
		}
	
	@Method(selector = "init")
	public native @Pointer long init();
	
	/**
	 * // Adds a listener for the given source (i.e., send symbol) in Pd.  If this is the first<br>
	 * // listener for this source, a subscription for this symbol will automatically be registered<br>
	 * // with PdBase.<br>
	 * <br>
	 * - (int)addListener:(NSObject<PdListener> *)listener forSource:(NSString *)source;
	 */
	@Method(selector = "addListener:forSource:")
	public native int addListener(PdListener listener, NSString source);
	
	/**
	 * // Removes a listener for a source symbol and unsubscribes from messages to this symbol if<br>
	 * // the listener was the last listener for this symbol.<br>
	 * <br>
	 * - (int)removeListener:(NSObject<PdListener> *)listener forSource:(NSString *)source;<br>
	 */
	@Method(selector = "removeListener:forSource:")
	public native int removeListener(PdListener listener, NSString source);

	@Override
	public void receivePrint(NSString message) {
		receivePrintNative(message);
		}
	
	/**
	 * // Override this method in subclasses if you want different printing behavior.<br>
	 * // No need to synchronize here.<br>
	 * - (void)receivePrint:(NSString *)message<br>
	 * <br>
	 * <b>You should override the non-native one.</b><br>
	 * @param message
	 */
	@Method(selector = "receivePrint:")
	public native void receivePrintNative(NSString message);
	}
