
# react-native-keyevent

## Getting started

`$ npm install react-native-key-event --save`

### Mostly automatic installation

`$ react-native link react-native-key-event`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import net.kangyufei.KeyEventPackage;` to the imports at the top of the file
  - Add `new KeyEventPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-key-event'
  	project(':react-native-key-event').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-key-event/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-key-event')
  	```

## Usage
```javascript
import KeyEvent from 'react-native-key-event';

...
	componentDidMount() {
		console.log('componentDidMount');

		// if you want to react to keyUp
		KeyEvent.onKeyUpListener((keyCode, event) => {
			console.log(keyCode);
			// console.log(event);
			listener(keyCode);
		});
	}
...
	componentWillUnmount() {

		// if you are listening to keyUp
		KeyEvent.removeKeyUpListener();
		clearInterval(this.interval);
	}
...
```
## add in android/app/src/main/java/[...]/MainActivtiy.java:
```java
import android.view.KeyEvent; // <--- import
import net.kangyufei.KeyEventModule; // <--- import


public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "navigationTest";
    }

  @Override  // <--- Add this method if you want to react to keyDown
  public boolean onKeyDown(int keyCode, KeyEvent event) {

    // A. Prevent multiple events on long button press
    //    In the default behavior multiple events are fired if a button
    //    is pressed for a while. You can prevent this behavior if you
    //    forward only the first event:
    //        if (event.getRepeatCount() == 0) {
    //            KeyEventModule.getInstance().onKeyDownEvent(keyCode);
    //        }
    //
    // B. If multiple Events shall be fired when the button is pressed
    //    for a while use this code:
    //        KeyEventModule.getInstance().onKeyDownEvent(keyCode);
    //
    // Using B.
    KeyEventModule.getInstance().onKeyDownEvent(keyCode, event);

    // There are 2 ways this can be done:
    //  1.  Override the default keyboard event behavior
    //    super.onKeyDown(keyCode, event);
    //    return true;

    //  2.  Keep default keyboard event behavior
    //    return super.onKeyDown(keyCode, event);

    // Using method #1 without blocking multiple
    super.onKeyDown(keyCode, event);
    return true;
  }

  @Override  // <--- Add this method if you want to react to keyUp
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    KeyEventModule.getInstance().onKeyUpEvent(keyCode, event);

    // There are 2 ways this can be done:
    //  1.  Override the default keyboard event behavior
    //    super.onKeyUp(keyCode, event);
    //    return true;

    //  2.  Keep default keyboard event behavior
    //    return super.onKeyUp(keyCode, event);

    // Using method #1
    super.onKeyUp(keyCode, event);
    return true;
  }

}
```
  