## Installation

As the first step, install this module:

### React Native 0.57+

```
npm install --save https://github.com/elelyatar/react-native-keep-awake.git
```

### Manual install

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-keep-awake` and add `KCKeepAwake.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libKCKeepAwake.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Click `KCKeepAwake.xcodeproj` in the project navigator and go the `Build Settings` tab. Make sure 'All' is toggled on (instead of 'Basic'). In the `Search Paths` section, look for `Header Search Paths` and make sure it contains both `$(SRCROOT)/../../react-native/React` and `$(SRCROOT)/../../../React` - mark both as `recursive`.


#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java (React Native 0.29+)
  - Add `import com.corbt.keepawake.KCKeepAwakePackage;` to the imports at the top of the file
  - Add `new KCKeepAwakePackage()` to the list returned by the `getPackages()` method

3. Append the following lines to `android/settings.gradle`:

```groovy
include ':react-native-keep-awake'
	project(':react-native-keep-awake').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-keep-awake/android')
```

4. Insert the following lines inside the dependencies block in `android/app/build.gradle`:

```groovy
compile project(':react-native-keep-awake')
```

## Usage

There are two ways to use this package: by rendering it as a component, or by explicitly calling
the `KeepAwake.activate()` and `KeepAwake.deactivate()` static methods. Both are demonstrated below.

```jsx

import React, { Component } from 'react';
import { View, Text } from 'react-native';

import KeepAwake from 'react-native-keep-awake';

// Method 1
class MyComponent extends Component {
  render() {
    if (this.props.screenShouldBeAwake) {
      return (
        <View>
          <Text>Screen will be kept awake</Text>
          <KeepAwake />
        </View>
      )
    } else {
      return (
        <View>
          <Text>Screen can sleep</Text>
        </View>
      );
    }
  }
}

// Method 2
function changeKeepAwake(shouldBeAwake) {
  if (shouldBeAwake) {
    KeepAwake.activate();
  } else {
    KeepAwake.deactivate();
  }
}

```

In method 1, the screen will be locked awake when the `<KeepAwake />` component is mounted, and the lock will be disabled when the component is unmounted. Method 2 gives you more explicit control of when the lock is active or not.
