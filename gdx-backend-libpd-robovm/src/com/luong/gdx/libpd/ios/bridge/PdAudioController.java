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
public class PdAudioController extends NSObject {

	static {
		ObjCRuntime.bind(PdAudioController.class);
		}
	
	/**
	 * @property
	 */
	private static final Selector isActive = Selector.register("isActive");
	
	/**
	 * @method
	 */
	private static final Selector init = Selector.register("init");
	
	/**
	 * @method
	 */
	private final Selector configureAmbientWithSampleRate = Selector.register("configureAmbientWithSampleRate:numberChannels:mixingEnabled:");
	
	public PdAudioController() {
		initObject(objc_init(this, init));
		}
	
	protected PdAudioController (SkipInit skipInit) {
	     super(skipInit);
	     }
	
	public PdAudioStatus configureAmbientWithSampleRate(int sampleRate, int numChannels, boolean mixingEnabled) {
		return objc_configureAmbientWithSampleRate(this,
													configureAmbientWithSampleRate,
													sampleRate,
													numChannels,
													mixingEnabled);
		}
	
	public boolean isActive () {
	     return objc_isActive(this, isActive);
	     }
	
	@Bridge
	private native static boolean objc_isActive (PdAudioController __self__, Selector __cmd__);
	
	@Bridge
	private native static @Pointer long objc_init (PdAudioController __self__, Selector __cmd__);
	
	@Bridge
	private native PdAudioStatus objc_configureAmbientWithSampleRate(PdAudioController __self__,
																		Selector __cmd__,
																		int sampleRate,
																		int numChannels,
																		boolean mixingEnabled);
	}
