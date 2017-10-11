
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
    mReactContext = reactContext;
  }

  public void onKeyDownEvent(int keyCode, KeyEvent event) {
    if (mJSModule == null) {
        mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }
    WritableMap args = Arguments.createMap();
    args.putInt("keyCode", keyCode);
    args.putInt("scanCode", event.getScanCode());
    args.putInt("unicodeChar", event.getUnicodeChar());
    args.putString("Number", String.valueOf(event.getNumber()));
    args.putBoolean("isShiftPressed", event.isShiftPressed());
    args.putBoolean("isLongPress", event.isLongPress());
    args.putBoolean("isCapsLockOn", event.isCapsLockOn());
    args.putString("displayLabel", String.valueOf(event.getDisplayLabel()));
    mJSModule.emit("onKeyDown", args);
  };

  public void onKeyUpEvent(int keyCode, KeyEvent event) {
      if (mJSModule == null) {
          mJSModule = mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
      }
      WritableMap args = Arguments.createMap();
      args.putInt("keyCode", keyCode);
      args.putInt("scanCode", event.getScanCode());
      args.putInt("unicodeChar", event.getUnicodeChar());
      args.putString("Number", String.valueOf(event.getNumber()));
      args.putBoolean("isShiftPressed", event.isShiftPressed());
      args.putBoolean("isLongPress", event.isLongPress());
      args.putBoolean("isCapsLockOn", event.isCapsLockOn());
      args.putString("displayLabel", String.valueOf(event.getDisplayLabel()));
      mJSModule.emit("onKeyUp", args);
  };

  @Override
  public String getName() {
    return "KeyEventModule";
  }
}