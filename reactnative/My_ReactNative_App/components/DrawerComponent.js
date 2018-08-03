import React, { Component } from 'react';
import { Text, View, Image, TouchableOpacity, ImageBackground } from 'react-native';
import UserAvatar from 'react-native-user-avatar';
import { NavigationActions } from "react-navigation";
export default class DrawerComponent extends Component {
    navigateTo(route) {
        return this
            .props
            .navigation
            .dispatch(NavigationActions.reset({
                index: 0,
                actions: [NavigationActions.navigate({ routeName: `${route}` })]
            }));
    }

    render() {

        return (
            <View style={{
                backgroundColor: 'White',
                flex: 1,
                flexDirection: 'column',

            }} >
               <ImageBackground source={{uri:"https://png.pngtree.com/element_origin_min_pic/16/12/11/0bbf79a6c710811c7bb25e46a082fdf4.jpg"}} style={{ flex:0.2, }}>
                    <View >
                        <View style={{ margin: 5, }}>
                            <UserAvatar size="100" name="Avishay Bar" src="https://ibgme.ae/wp-content/uploads/2017/01/assistant-icon-300x300.png" />
                            <Text style={{ marginTop: 10, marginLeft: 5, fontWeight: 'bold', fontSize: 20 }}  >Hitesh Singh</Text>
                            {/* <Text style={{ height: 2, marginTop: 10, backgroundColor: 'white' }}  ></Text> */}
                        </View>
                    </View>
                </ImageBackground>


                <View style={{ margin: 5, flex: 0.8, }}>
                    <TouchableOpacity onPress={() => this.navigateTo("Gmail", { isStatusBarHidden: false })}>
                        <View style={{ flexDirection: 'row', alignContent: 'center', alignItems: 'center' }}>
                            <Image
                                style={{ width: 50, height: 50, marginLeft: 10, }}
                                source={{ uri: 'https://cdn2.iconfinder.com/data/icons/picons-basic-1/57/basic1-007_house_home-128.png' }}
                            />

                            <Text style={{ marginLeft: 10, fontSize: 20 }}>Home Screen</Text>
                        </View>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => this.navigateTo("Details", { isStatusBarHidden: false })}>
                        <View style={{ flexDirection: 'row', alignContent: 'center', alignItems: 'center', marginTop: 10, }}>
                            <Image
                                style={{ width: 50, height: 50, marginLeft: 10, }}
                                source={{ uri: 'https://cdn0.iconfinder.com/data/icons/octicons/1024/book-128.png' }}
                            />
                            <Text style={{ marginLeft: 10, fontSize: 20 }}>Detail Screen</Text>
                        </View>
                    </TouchableOpacity>
                </View>

            </View>



        );
    }

}