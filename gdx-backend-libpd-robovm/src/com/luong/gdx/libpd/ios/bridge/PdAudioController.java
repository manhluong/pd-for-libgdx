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
	 * @property active getter
	 */
	private static final Selector isActive = Selector.register("isActive");
	
	@Bridge
	private native static boolean objc_isActive (PdAudioController __self__, Selector __cmd__);
	
	public boolean isActive () {
	     return objc_isActive(this, isActive);
	     }
	
	/**
	 * @property active setter
	 */
	private static final Selector setActive = Selector.register("setActive:");

	@Bridge
	private native static void objc_setActive (PdAudioController __self__, Selector __cmd__, boolean activeVal);

	public void setActive (boolean activeVal) {
		objc_setActive(this, setActive, activeVal);
		}
	
	protected PdAudioController (SkipInit skipInit) {
	     super(skipInit);
	     }
	
	/**
	 * @method
	 */
	private static final Selector init = Selector.register("init");
	
	@Bridge
	private native static @Pointer long objc_init (PdAudioController __self__, Selector __cmd__);
	
	public PdAudioController() {
		initObject(objc_init(this, init));
		}
	
	/**
	 * @method
	 * 
	 * // Configure audio for ambient use, without input channels.  Specifying mixingEnabled = YES will allow the app to continue
	 * // playing audio along with other apps (such as iPod music player).
	 * - (PdAudioStatus)configureAmbientWithSampleRate:(int)sampleRate
	 * 									numberChannels:(int)numChannels
	 * 									 mixingEnabled:(BOOL)mixingEnabled;
	 */
	private final Selector configureAmbientWithSampleRate = Selector.register("configureAmbientWithSampleRate:numberChannels:mixingEnabled:");
	
	@Bridge
	private native PdAudioStatus objc_configureAmbientWithSampleRate(PdAudioController __self__,
																		Selector __cmd__,
																		int sampleRate,
																		int numChannels,
																		boolean mixingEnabled);
	
	public PdAudioStatus configureAmbientWithSampleRate(int sampleRate, int numChannels, boolean mixingEnabled) {
		return objc_configureAmbientWithSampleRate(this,
													configureAmbientWithSampleRate,
													sampleRate,
													numChannels,
													mixingEnabled);
		}
	
	
	}
