package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSArray;
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
	public void receiveBangFromSource(NSString source) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void receiveFloat(float received,
								NSString source) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void receiveSymbol(NSString symbol,
								NSString source) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void receiveList(NSArray<?> list,
								NSString source) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void receiveMessage(NSString message,
								NSArray<?> arguments,
								NSString source) {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void receivePrint(NSString message) {
		// TODO Auto-generated method stub
		
		}
	
	}
