package com.luong.gdx.libpd.ios.bindings;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

@Library(Library.INTERNAL)
@NativeClass
public class PdAudioController extends NSObject {

	static {
		ObjCRuntime.bind(PdAudioController.class);
		}
	
	public PdAudioController() {
		super((SkipInit)null);
		initObject(init());
		}
	
	/**
	 * // Check or set the active status of the audio unit
	 * @ property (nonatomic, getter=isActive) BOOL active;
	 */
	@Property(selector = "isActive")
	public native boolean isActive();

	/**
	 * // Check or set the active status of the audio unit
	 * @ property (nonatomic, getter=isActive) BOOL active;
	 */
	@Property(selector = "setActive:")
	public native void setActive (boolean activeVal);
	
	@Method(selector = "init")
	public native @Pointer long init();
	
	/**
	 * - (void)dealloc
	 */
	@Method(selector = "dealloc")
	public native void dealloc();
	
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
