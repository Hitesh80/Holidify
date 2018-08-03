import React from 'react';
import { View, Text, Image,TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
export default class DetailHeader extends React.Component {

    
    render() {
        const navigate=this.props.navigate;
        return (
            <View style={{ backgroundColor: 'red', height: 50, flexDirection: 'row', alignContent: 'center', alignItems: 'center' }}>
                <View style={{ flex: 1, flexDirection: 'row' }}>
                    
                    <View style={{ flex: 0.6, flexDirection: 'row' }}>
                    <TouchableOpacity onPress={() =>navigate.goBack()}>
                        <Icon name="arrow-back" size={30} color="white" style={{ marginLeft: 10 }} />
                        </TouchableOpacity>
                    </View>
                
                    <View style={{ flex: 0.4, flexDirection: 'row' }}>
                        <Icon name="cloud-download" size={30} color="white" style={{ flex: 0.25 }} />
                        <Icon name="delete" size={30} color="white" style={{ flex: 0.25 }} />
                        <Icon name="email" size={30} color="white" style={{ flex: 0.25 }} />
                        <Icon name="more-vert" size={30} color="white" style={{ flex: 0.25 }} />
                    </View>
                </View>
            </View>
        );
    }
}
