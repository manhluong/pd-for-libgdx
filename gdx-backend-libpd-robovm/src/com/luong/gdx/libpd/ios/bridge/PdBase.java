package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

@Library(Library.INTERNAL)
@NativeClass
public class PdBase extends NSObject {

	static {
		ObjCRuntime.bind(PdBase.class);
		}
	
	/**
	 * @method
	 * 
	 * + (void)initialize;
	 */
	private static final Selector initialize = Selector.register("initialize");
	
	@Bridge
	private native static void objc_initialize (PdBase __self__, Selector __cmd__);
	
	public PdBase() {
		objc_initialize(this, initialize);
		}
	}
