
package net.kangyufei;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import android.view.KeyEvent;

public class KeyEventModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private static KeyEventModule instance = null;
  private ReactContext mReactContext;
  private DeviceEventManagerModule.RCTDeviceEventEmitter mJSModule = null;
  
  public static KeyEventModule initKeyEventModule(ReactApplicationContext reactContext) {
      instance = new KeyEventModule(reactContext);
      return instance;
  }

  public static KeyEventModule getInstance() {
      return instance;
  }

  public KeyEventModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  public void onKeyDownEvent(int keyCode, KeyEvent event) {
    if (mJSModule == null) {
        mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }
    WritableMap args = Arguments.createMap();
    args.pushInt("keyCode", keyCode);
    args.pushInt("scanCode", event.getScanCode());
    args.pushInt("unicodeChar", event.getUnicodeChar());
    args.pushString("Number", String.valueOf(event.getNumber()));
    args.pushBool("isShiftPressed", event.isShiftPressed());
    args.pushBool("isLongPress", event.isLongPress());
    args.pushBool("isCapsLockOn", event.isCapsLockOn());
    args.pushString("displayLabel", String.valueOf(event.getDisplayLabel()));
    mJSModule.emit("onKeyDown", args);
  };

  public void onKeyUpEvent(int keyCode, KeyEvent event) {
      if (mJSModule == null) {
          mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
      }
      WritableMap args = Arguments.createMap();
      args.pushInt("keyCode", keyCode);
      args.pushInt("scanCode", event.getScanCode());
      args.pushInt("unicodeChar", event.getUnicodeChar());
      args.pushString("Number", String.valueOf(event.getNumber()));
      args.pushBool("isShiftPressed", event.isShiftPressed());
      args.pushBool("isLongPress", event.isLongPress());
      args.pushBool("isCapsLockOn", event.isCapsLockOn());
      args.pushString("displayLabel", String.valueOf(event.getDisplayLabel()));
      mJSModule.emit("onKeyUp", args);
  };

  @Override
  public String getName() {
    return "KeyEventModule";
  }
}