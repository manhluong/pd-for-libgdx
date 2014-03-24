package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

@Library(Library.INTERNAL)
@NativeClass
public class PdDispatcher extends NSObject {
	
	static {
		ObjCRuntime.bind(PdDispatcher.class);
		}
	
	public PdDispatcher() {
		super((SkipInit)null);
		initObject(init());
		}
	
	@Method(selector = "init")
	public native @Pointer long init();
	
	}
