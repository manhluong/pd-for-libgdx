package com.luong.gdx.libpd.ios.bindings;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

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

	@Override
	public void receivePrint(NSString message) {
		receivePrintNative(message);
		}
	
	/**
	 * // Override this method in subclasses if you want different printing behavior.<br>
	 * // No need to synchronize here.<br>
	 * - (void)receivePrint:(NSString *)message<br>
	 * <br>
	 * You should override the non-native one.<br>
	 * <br>
	 * @param message
	 */
	@Method(selector = "receivePrint:")
	public native void receivePrintNative(NSString message);
	}
