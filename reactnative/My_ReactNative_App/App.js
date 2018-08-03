/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';
import { StackNavigator, DrawerNavigator ,navigation} from 'react-navigation';
import Bananas from './components/bananas';
import Greetings from './components/greeting';
import SimpleList from './components/simpleList';
import GmailListView from './components/Gmail';
import DetailsScreen from './components/DetailsScreen';
import LogoTitle from './components/Logo'
import DrawerComponent from './components/DrawerComponent'
const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});
//type Props = {};
var SampleArray = [];
 class App2 extends Component {

  constructor(props) {
    super(props);
    this.state = {
      name: '',
      data: [],

    }
    this.updateName = this.updateName.bind(this);
  }

  updateName(val) {
    var x = { key: val };
    console.log('val', x);
    SampleArray.push(x);
    console.log('sampleArray', SampleArray);
    // const name = val;
    // this.setState({ name })
    this.setState({
      name: val,
      data: SampleArray,
    });

  }


  render() {
    let pic = {
      uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg'
    };
    // <LogoTitle {...this.props}/>
    return (
      <View style={styles.container3}>
        {/* <View style={styles.container}>
          <View style={styles.container1}>
            <Bananas img={pic} />
          </View>
          <View style={styles.container4}>
            <Greetings name={this.state.name} updateName={this.updateName} />
          </View>
        </View>
        <View style={styles.container}>
          <SimpleList data={this.state.data}/>
        </View> */}
        {/* <RootStack />
        <drawerNavigator/> */}
      </View>
    );
  }
}

const RootStack = StackNavigator(
  {
    Gmail: {
      screen: GmailListView,
      // navigationOptions: ({navigation}) => {
      //   header: (<LogoTitle  navigate={navigation.navigate}/>)
      // },
    },
    Details: {
      screen: DetailsScreen,
    },
    Drawer: {
      screen: DrawerComponent,
    },
   
  },
  {
    initialRouteName: 'Gmail',
  }
);


 const App = DrawerNavigator(
  {
    mainRouter:{
      screen: RootStack
    },
    
  }
  ,
  {
     contentComponent: DrawerComponent,
     drawerWidth: 250,
     drawerPosition : 'left',
  }
);
export default App;



const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'row',
    backgroundColor: 'blue',
  },
  container3: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: 'black'
  },
  container2: {
    flex: 0.5,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'gray',
  },
  container1: {
    flex: 0.5,
    marginBottom: 20,
    backgroundColor: 'green',
  },
  container4: {
    flex: 0.5,
    marginBottom: 20,
    backgroundColor: 'yellow',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  bigblue: {
    color: 'blue',
    fontWeight: 'bold',
    fontSize: 30,
  },

});
