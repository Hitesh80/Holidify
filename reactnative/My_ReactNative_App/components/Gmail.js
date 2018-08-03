import React, { Component } from 'react';
import { Text, View, Dimensions } from 'react-native';
import SimpleList from './simpleList';
import ActionButton from 'react-native-action-button';
import LogoTitle from './Logo';
import {NavigationActions,navigation} from 'react-navigation';
//import Icon from 'react-native-vector-icons/Ionicons';


const ROWS_IN_DATA_SOURCE = 3000;
const dataSource = [];
for (let i = 0; i < ROWS_IN_DATA_SOURCE; i++)
    dataSource.push({
        key: `This data of Recipent # ${i + 1}`,
        key1: `This is the data for subject # ${i + 1}`,
        key2: `This is the data for message # ${i + 1}`,

    });
export default class GmailListView extends Component {

    static navigationOptions = ({navigation}) => ({
        header: <LogoTitle navigate={navigation.navigate} />
    });
    navigateTo(route) {
        return this
            .props
            .navigation
            .dispatch(NavigationActions.reset({
                index: 0,
                actions: [NavigationActions.navigate({ routeName: `${route}` })]
            }));
    }
    constructor(props) {
        super(props);
    }
    render() {
        const { navigate } = this.props.navigation;
        return (
            <View style={{
                backgroundColor: 'blue',
                flex: 1,
                flexDirection: 'column',
            }} >
                <SimpleList data={dataSource} navigate={navigate} />
                <ActionButton
                    buttonColor="rgba(231,76,60,1)"
                    onPress={() =>this.navigateTo('Gmail')}
                >
                    {/* <ActionButton.Item buttonColor='#9b59b6' title="New Task" onPress={() => console.log("notes tapped!")}>
                        <Icon name="md-create" style={styles.actionButtonIcon} />
                    </ActionButton.Item> */}
                </ActionButton>
            </View>

        );
    }

}
