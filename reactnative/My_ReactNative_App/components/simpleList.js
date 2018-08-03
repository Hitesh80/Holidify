import React, { Component } from 'react';
import { StackNavigator } from 'react-navigation';
import { FlatList, StyleSheet, TouchableOpacity, Text, View,Image } from 'react-native';
import UserAvatar from 'react-native-user-avatar';
import Icon from 'react-native-vector-icons/MaterialIcons';
export default class SimpleList extends Component {


  constructor(props) {
    super(props);
    this.state = {
      data: [],
      curTime:new Date().toLocaleString().substring(10,16),
    }
    this.pressBttn = this._onPressButton.bind(this);
  }

  componentDidMount() {
    setInterval( () => {
      this.setState({
        curTime : new Date().toLocaleString().substring(10,16)
      })
    },100)
  }
  _onPressButton() {
    this.props.navigation.navigate('Details')
  }

  render() {
    const navigate = this.props.navigate;
    return (
      <View style={styles.container}>
        <FlatList
          data={this.props.data}
          renderItem={
            ({ item }) =>
              <TouchableOpacity onPress={() => navigate('Details')}>
                <View style={{ flex: 1, backgroundColor: 'white', flexDirection: 'row' }} >
                  <View style={styles.image}>
                  <UserAvatar size="50" name="This"  />
                  </View>
                  <View style={{ flex: 0.9 }}>
                  <View style={{flexDirection:'row'}}>
                    <Text style={styles.container2}>
                      {item.key}
                    </Text>
                    <Text style={{flex:0.1,alignContent:'center',alignItems:'center',marginTop:10,marginLeft:30,}}>
                    {this.state.curTime}
                    </Text>
                    </View>
                   
                    <View style={{ flexDirection:'row'}}>
                    <View style={{flex: 0.9 }}>
                    <Text style={styles.container1}>
                      {item.key1}
                    </Text>
                    <Text style={styles.container1}>
                      {item.key2}
                    </Text>
                    </View>
                    <Icon name="star-border" size={30} color="black" flex={0.1} marginTop={20}/>
                    </View>
                  </View>
                </View>
                <Text style={{ height: 1, backgroundColor: 'black' }}>

                </Text>


              </TouchableOpacity>

          }
        />
      </View>

    );
  }

}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    fontWeight: 'bold',
    fontSize: 30,
    marginLeft: 5,

  },
  container2: {
    flex: 0.8,
    justifyContent: 'center',
    fontWeight: 'bold',
    fontSize: 30,
    marginLeft: 5,

  },
  container1: {
    flex: 1,
    justifyContent: 'center',
    fontSize: 20,
    marginLeft: 10,

  },
  image: {
    flex: 0.1,
    alignSelf: 'center',
    alignItems: 'center',
    fontSize: 20,
    justifyContent: 'center',
  },

});