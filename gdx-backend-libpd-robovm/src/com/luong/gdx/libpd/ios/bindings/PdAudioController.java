/*******************************************************************************
 * Copyright 2014 Manh Luong   Bui.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
	 * @property (nonatomic, readonly) int sampleRate;
	 */
	@Property(selector = "sampleRate")
	public native int getSampleRate();
	
	/**
	 * @property (nonatomic, readonly) int numberChannels;
	 */
	@Property(selector = "numberChannels")
	public native int getNumberChannels();
	
	/**
	 * @property (nonatomic, readonly) BOOL inputEnabled;
	 */
	@Property(selector = "inputEnabled")
	public native boolean getInputEnabled();
	
	/**
	 * @property (nonatomic, readonly) BOOL mixingEnabled;
	 */
	@Property(selector = "mixingEnabled")
	public native boolean getMixingEnabled();
	
	/**
	 * @property (nonatomic, readonly) int ticksPerBuffer;
	 */
	@Property(selector = "ticksPerBuffer")
	public native int getTicksPerBuffer();
	
	/**
	 * // Check or set the active status of the audio unit<br>
	 * @ property (nonatomic, getter=isActive) BOOL active;
	 */
	@Property(selector = "isActive")
	public native boolean isActive();

	/**
	 * // Check or set the active status of the audio unit<br>
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
	 * // Configure the audio with the specified samplerate, as well as number of output channels (which will also be the number of<br>
	 * // input channels if input is enable).  Note that this method has three possible outcomes: success, failure, or conditional<br>
	 * // success, where parameters had to be adjusted to set up the audio.  In the third case, you can query the sample rate and<br>
	 * // channel properties to determine whether the selected configuration is acceptable.  Specifying mixingEnabled = YES will<br>
	 * // allow the app to continue playing audio along with other apps (such as iPod music player).<br>
	 * <br>
	 * - (PdAudioStatus)configurePlaybackWithSampleRate:(int)sampleRate
	 *                                   numberChannels:(int)numChannels
	 *   								 inputEnabled:(BOOL)inputEnabled
	 *                                   mixingEnabled:(BOOL)mixingEnabled;
	 */
	@Method(selector = "configurePlaybackWithSampleRate:numberChannels:inputEnabled:mixingEnabled:")
	public native PdAudioStatus configurePlaybackWithSampleRate(int sampleRate, int numChannels, boolean inputEnabled, boolean mixingEnabled);
	
	/**
	 * // Configure audio for ambient use, without input channels.  Specifying mixingEnabled = YES will allow the app to continue<br>
	 * // playing audio along with other apps (such as iPod music player).<br>
	 * <br>
	 * - (PdAudioStatus)configureAmbientWithSampleRate:(int)sampleRate
	 * 									numberChannels:(int)numChannels
	 * 									 mixingEnabled:(BOOL)mixingEnabled;
	 */
	@Method(selector = "configureAmbientWithSampleRate:numberChannels:mixingEnabled:")
	public native PdAudioStatus configureAmbientWithSampleRate(int sampleRate, int numChannels, boolean mixingEnabled);
	
	/**
	 * // Configure the ticksPerBuffer parameter, which will change the audio sessions IO buffer size.<br>
	 * // This can be done on the fly, while audio is running.  Note that the audio session only accepts<br>
	 * // values that correspond to a number of frames that are a power of 2 and sometimes this value<br>
	 * // is ignored by the audio unit, which tries to work with whatever number of frames it is provided.<br>
	 * <br>
	 * - (PdAudioStatus)configureTicksPerBuffer:(int)ticksPerBuffer;
	 */
	@Method(selector = "configureTicksPerBuffer:")
	public native PdAudioStatus configureTicksPerBuffer(int ticksPerBuffer);
	
	/**
	 * - (void)print;
	 */
	@Method(selector = "print")
	public native void print();
	}
