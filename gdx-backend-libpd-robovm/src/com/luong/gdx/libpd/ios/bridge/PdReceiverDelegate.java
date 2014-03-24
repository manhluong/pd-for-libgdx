package com.luong.gdx.libpd.ios.bridge;

import org.robovm.rt.bro.annotation.Library;

/**
 * // Receiver interface for printing and receiving messages from Pd
 * @ protocol PdReceiverDelegate<PdListener>
 * 
 * @author luong
 */
@Library(Library.INTERNAL)
public interface PdReceiverDelegate extends PdListener {
	/**
	 * - (void)receivePrint:(NSString *)message;
	 */
	}
