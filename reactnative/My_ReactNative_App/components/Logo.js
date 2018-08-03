import React, { Component } from 'react';
import {
    Text,
    View,
    Image, TouchableOpacity
} from 'react-native';
import App from '../App';
import DrawerComponent from './DrawerComponent';
import {NavigationActions} from "react-navigation";
import Icon from 'react-native-vector-icons/MaterialIcons';
export default class LogoTitle extends React.Component {
    // navigateTo(route) {
    //     return this
    //         .props
    //         .navigation
    //         .dispatch(NavigationActions.reset({
    //             index: 0,
    //             actions: [NavigationActions.navigate({routeName: `${route}`})]
    //         }));
    // }
   
    render() {
        console.log("props sent", this.props.navigate);
        return (
            <View style={{backgroundColor:'red', height:60,flexDirection: 'row',alignContent:'center',alignItems:'center' }}>
                
                <View style={{flex:.9,flexDirection:'row',marginLeft:20,}}>
                <TouchableOpacity onPress={() => this.props.navigate('DrawerOpen')}>
                <Icon name="menu" size={30} color="white"   />
                </TouchableOpacity>
                <Text style={{ height: 30, marginLeft: 20,color:"white",alignContent:'center',alignItems:'center',fontStyle:'bold'}}>Primary</Text>
                </View>
                <View style={{flex:.1}}>
                <Icon name="search" size={30} color="white"  />
                </View>
            </View>
            
        );
    }
}