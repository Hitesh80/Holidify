import React, { Component } from 'react';
import {
 Image,
} from 'react-native';

export default class BackgroundImage extends Component {

    render() {
        return (
            <Image source="https://cdn.pixabay.com/photo/2017/06/08/17/43/blue-2384333_960_720.png">
                    
                    {this.props.children}
                    
            </Image>
        )
    }
}