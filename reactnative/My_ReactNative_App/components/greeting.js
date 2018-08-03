import React, { Component } from 'react';
import { AppRegistry, Image, View, Text, Button, TextInput } from 'react-native';

export default class Greetings extends Component {
  constructor(props) {
    super(props);
    this.state = {
      reciever: "",
      text: "",
    }
    this.updateName = this.updateName.bind(this);
    this.updateState = this.updateState.bind(this);
    
  }
  componentWillMount() {
    console.log("greetings componentWillMount", "1");
  }
  componentDidMount() {
    console.log("greetings componentDidMount", "3");
  }

  componentWillReceiveProps(nextProps) {
    console.log("greetings componentWillReceiveProps", "4");
  }

  
  updateState() {
    this.setState({
      reciever: "heyyy"
    });
  }

  updateName() {
    this.props.updateName(this.state.text)
  }


  render() {
    console.log("greetings render", "2");
    return (
      <View style={{
        flex: 1,
        margin: 10,
      }}>

        {/* <View >
          <Button
            onPress={this.updateState}
            title="say hey"
            color="#841584"
    
          />
        </View> */}
        <View style={{
          marginTop: 10,
        }}>

          <TextInput
            style={{ height: 40 }}
            placeholder="Type here a name!"
            onChangeText={(text) => this.setState({ text })}
          />
        </View>
        <View style={{
          marginTop: 10,
        }}>

          <Text style={{ padding: 10 ,backgroundColor:'green'}}>
            {this.state.text}
          </Text>
        </View>
        <View style={{
          marginTop: 10,
        }}>

          <Button
            onPress={this.updateName } 
            title="update Name"
            color="#841584"
          />
        </View>
        <View style={{ alignContent: 'center', alignItems: 'center', flex: 1, marginTop: 10, }}>
          <Text>{this.state.reciever} {this.props.name} </Text>
        </View>
      </View>
    );
  }
}
