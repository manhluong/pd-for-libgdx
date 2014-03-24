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
public class PdDispatcher extends NSObject {
	
	static {
		ObjCRuntime.bind(PdDispatcher.class);
		}
	
	protected PdDispatcher (SkipInit skipInit) {
	     super(skipInit);
	     }
	
	/**
	 * @method
	 */
	private static final Selector init = Selector.register("init");
	
	@Bridge
	private native static @Pointer long objc_init (PdDispatcher __self__, Selector __cmd__);
	
	public PdDispatcher() {
		initObject(objc_init(this, init));
		}
	}
