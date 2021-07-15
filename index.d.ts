import React from 'react'

export default class Tools extends React.Component {
  static activate(): void
  static deactivate(): void
  static getCurrentForeground(): string
  static setForeground(): void
}
