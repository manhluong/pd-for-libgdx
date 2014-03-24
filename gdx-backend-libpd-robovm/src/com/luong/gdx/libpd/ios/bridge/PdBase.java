package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;

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
	
	/**
	 * + (void *)openFile:(NSString *)baseName
	 * 				 path:(NSString *)pathName;
	 */
	
	/**
	 * + (void)closeFile:(void *)x;
	 */
	
	/**
	 * + (int)sendFloat:(float)value
	 * 	     toReceiver:(NSString *)receiverName;
	 */
	
	/**
	 * + (int)sendBangToReceiver:(NSString *)receiverName;
	 */
	}
