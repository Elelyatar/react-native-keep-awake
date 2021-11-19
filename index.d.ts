import React from 'react'

export default class Tools extends React.Component {
  static activate(): void
  static deactivate(): void
  static isScreenOn(callback): void
  static getCurrentForeground(): string
  static setForegroundActivity(activityToStart): void
  static setForegroundPackage(packageName): void
}
