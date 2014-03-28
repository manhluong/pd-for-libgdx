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
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.ptr.FloatPtr;
import org.robovm.rt.bro.ptr.VoidPtr;


/**
 * @ interface PdBase {
 * // Not meant to be instantiated. No member variables.
 * }
 */
@Library(Library.INTERNAL)
@NativeClass
public class PdBase extends NSObject {

	static {
		ObjCRuntime.bind(PdBase.class);
		}
	
	public PdBase() {
		super((SkipInit)null);
		initialize();
		}
	
	/**
	 * + (void)initialize;
	 */
	@Method(selector = "initialize")
	public static native void initialize();
	
	/**
	 * PdBase retains the delegate: call setDelegate with nil in order to release delegate.<br>
	 * + (void)setDelegate:(NSObject<PdReceiverDelegate> *)newDelegate;
	 */
	@Method(selector = "setDelegate:")
	public static native void setDelegate(PdReceiverDelegate newDelegate);
	
	/**
	 * + (void *)openFile:(NSString *)baseName
	 * 				 path:(NSString *)pathName;
	 */
	@Method(selector = "openFile:path:")
	public static native VoidPtr openFile(NSString baseName, NSString pathName);
	
	/**
	 * + (void)closeFile:(void *)x;
	 */
	@Method(selector = "closeFile:")
	public static native void closeFile(VoidPtr x);
	
	/**
	 * + (int)sendFloat:(float)value
	 * 	     toReceiver:(NSString *)receiverName;
	 */
	@Method(selector = "sendFloat:toReceiver:")
	public static native int sendFloat(float value, NSString receiverName);
	
	/**
	 * + (int)sendBangToReceiver:(NSString *)receiverName;
	 */
	@Method(selector = "sendBangToReceiver:")
	public static native int sendBangToReceiver(NSString receiverName);
	
	/**
	 * + (int)sendSymbol:(NSString *)symbol toReceiver:(NSString *)receiverName;
	 */
	@Method(selector = "sendSymbol:toReceiver:")
	public static native int sendSymbol(NSString symbol, NSString receiverName);
	
	/**
	 * // list may be nil<br>
	 * + (int)sendList:(NSArray *)list toReceiver:(NSString *)receiverName;
	 */
	@Method(selector = "sendList:toReceiver:")
	public static native int sendList(NSArray<?> list, NSString receiverName);
	
	/**
	 * // list may be nil<br>
	 * + (int)sendMessage:(NSString *)message withArguments:(NSArray *)list toReceiver:(NSString *)receiverName;
	 */
	@Method(selector = "sendMessage:withArguments:toReceiver:")
	public static native int sendMessage(NSString message, NSArray<?> list, NSString receiverName);
	
	/**
	 * + (int)arraySizeForArrayNamed:(NSString *)arrayName;
	 */
	@Method(selector = "arraySizeForArrayNamed:")
	public static native int arraySizeForArrayNamed(NSString arrayName);
	
	/**
	 * + (int)copyArrayNamed:(NSString *)arrayName withOffset:(int)offset toArray:(float *)destinationArray count:(int)n;
	 */
	@Method(selector = "copyArrayNamed:withOffset:toArray:count:")
	public static native int copyArrayNamed(NSString arrayName, int offset, FloatPtr destinationArray, int n);

	/**
	 * + (int)copyArray:(float *)sourceArray toArrayNamed:(NSString *)arrayName withOffset:(int)offset count:(int)n;
	 */
	@Method(selector = "copyArray:toArrayNamed:withOffset:count:")
	public static native int copyArray(FloatPtr sourceArray, NSString arrayName, int offset, int n);
	}
