package com.luong.gdx.libpd.ios.bridge;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.Method;
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
	 * // Check or set the active status of the audio unit
	 * @ property (nonatomic, getter=isActive) BOOL active;
	 */
	private static final Selector isActive = Selector.register("isActive");
	
	@Bridge
	private native static boolean objc_isActive (PdAudioController __self__, Selector __cmd__);
	
	public boolean isActive () {
	     return objc_isActive(this, isActive);
	     }
	
	/**
	 * // Check or set the active status of the audio unit
	 * @ property (nonatomic, getter=isActive) BOOL active;
	 */
	private static final Selector setActive = Selector.register("setActive:");

	@Bridge
	private native static void objc_setActive (PdAudioController __self__, Selector __cmd__, boolean activeVal);

	public void setActive (boolean activeVal) {
		objc_setActive(this, setActive, activeVal);
		}
	
	public PdAudioController() {
		super((SkipInit)null);
		initObject(init());
		}
	
	@Method(selector = "init")
	public native @Pointer long init();
	
	/**
	 * // Configure audio for ambient use, without input channels.  Specifying mixingEnabled = YES will allow the app to continue
	 * // playing audio along with other apps (such as iPod music player).
	 * - (PdAudioStatus)configureAmbientWithSampleRate:(int)sampleRate
	 * 									numberChannels:(int)numChannels
	 * 									 mixingEnabled:(BOOL)mixingEnabled;
	 */
	@Method(selector = "configureAmbientWithSampleRate:numberChannels:mixingEnabled:")
	public native PdAudioStatus configureAmbientWithSampleRate(int sampleRate, int numChannels, boolean mixingEnabled);
	
	
	}
