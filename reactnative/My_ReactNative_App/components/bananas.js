import React, { Component } from 'react';
import { AppRegistry, Image,View,Text } from 'react-native';

export default class Bananas extends Component {
  render() {
    console.log("render","2");
    return (
      <View style={{marginLeft:10,}}>
        <Text>Hitesh </Text>

        <Image source={this.props.img} style={{width: 193, height: 110,}}/>
      </View>
    );
  }
  componentWillMount() {
    console.log("componentWillMount","1");
  }
  componentDidMount() {
    console.log("componentDidMount","3");
  }
}
