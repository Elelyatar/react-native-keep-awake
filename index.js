// @flow

import React, { Component } from 'react';
import { NativeModules } from 'react-native';

let mounted = 0;

export default class TurnScreenOn extends Component<{}> {
  static activate() {
    NativeModules.TurnScreenOn.activate();
  }

  static deactivate() {
    NativeModules.TurnScreenOn.deactivate();
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
