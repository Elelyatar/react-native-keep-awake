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

  static getCurrentForeground() {
    return NativeModules.PushForeground.getCurrentForeground();
  }

  static setForeground() {
    NativeModules.PushForeground.setForeground();
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
