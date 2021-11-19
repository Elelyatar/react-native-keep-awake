// @flow

import React, { Component } from 'react';
import { NativeModules } from 'react-native';

let mounted = 0;

export default class Tools extends Component<{}> {
  static activate() {
    NativeModules.TurnScreenOn.activate();
  }

  static deactivate() {
    NativeModules.TurnScreenOn.deactivate();
  }

  static isScreenOn(callback) {
    NativeModules.TurnScreenOn.isScreenOn(callback);
  }

  static getCurrentForeground() {
    return NativeModules.PushForeground.getCurrentForeground();
  }

  static setForegroundActivity(activityToStart) {
    NativeModules.PushForeground.setForegroundActivity(activityToStart);
  }

  static setForegroundPackage(packageName) {
    NativeModules.PushForeground.setForegroundPackage(packageName);
  }

  componentDidMount() {
    mounted++;
    TurnScreenOn.activate();
  }

  componentWillUnmount() {
    mounted--;
    if (!mounted) {
      TurnScreenOn.deactivate();
    }
  }

  render() {
    return null;
  }
}
