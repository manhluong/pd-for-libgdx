package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSObjectProtocol;
import org.robovm.rt.bro.annotation.Library;

/**
 * @ protocol PdListener
 */
@Library(Library.INTERNAL)
public interface PdListener extends NSObjectProtocol {
	/**
	 * - (void)receiveBangFromSource:(NSString *)source;
	 */
	
	/**
	 * - (void)receiveFloat:(float)received
	 * 			 fromSource:(NSString *)source;
	 */
	
	/**
	 * - (void)receiveSymbol:(NSString *)symbol
	 * 			  fromSource:(NSString *)source;
	 */
	
	/**
	 * - (void)receiveList:(NSArray *)list
	 * 	        fromSource:(NSString *)source;
	 */
	
	/**
	 * - (void)receiveMessage:(NSString *)message
	 * 	        withArguments:(NSArray *)arguments
	 * 			   fromSource:(NSString *)source;
	 */
	}
