package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;


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
	 * PdBase retains the delegate: call setDelegate with nil in order to release delegate.
	 * + (void)setDelegate:(NSObject<PdReceiverDelegate> *)newDelegate;
	 */
	@Method(selector = "setDelegate:")
	public static native void setDelegate(PdReceiverDelegate newDelegate);
	
	/**
	 * + (void *)openFile:(NSString *)baseName
	 * 				 path:(NSString *)pathName;
	 */
	@Method(selector = "openFile:path:")
	public static native void openFile(NSString baseName, NSString pathName);
	
	/**
	 * + (void)closeFile:(void *)x;
	 */
	@Method(selector = "closeFile:")
	public static native void closeFile(@Pointer long x);
	
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
	}
