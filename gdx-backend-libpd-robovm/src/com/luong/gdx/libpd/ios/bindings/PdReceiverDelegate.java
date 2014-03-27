package com.luong.gdx.libpd.ios.bindings;

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
 * @author luong
 */
@Library(Library.INTERNAL)
public interface PdReceiverDelegate extends PdListener {
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
