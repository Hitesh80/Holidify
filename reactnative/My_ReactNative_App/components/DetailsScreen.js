import React from 'react';
import { View, Text } from 'react-native';
import DetailHeader from './DetailHeader';
import UserAvatar from 'react-native-user-avatar';
import { NavigationActions, navigation } from 'react-navigation';
import Icon from 'react-native-vector-icons/MaterialIcons';
export default class DetailsScreen extends React.Component {
  static navigationOptions = ({ navigation }) => ({
    header: <DetailHeader navigate={navigation} />
  });
  render() {
    return (
      <View style={{ flex: 1, flexDirection: 'column', }}>
        <View style={{ flex: 0.1, flexDirection: 'row', alignContent: 'center', alignItems: 'center' }}>
          <Text style={{ flex: 0.9, alignContent: 'center', alignItems: 'center', marginLeft: 20, fontSize: 20 }} >
            sample email Subject
            <Text ><Icon name="play-arrow" size={30} color="yellow" />inbox</Text>
          </Text>
          <Icon name="star-border" size={30} color="black" flex={0.1} marginTop={20} />
        </View>
        <Text style={{ height: 1, backgroundColor: 'black' }} />
        <View style={{ marginLeft: 20, flex: 0.1, flexDirection: 'row', alignContent: 'center', alignItems: 'center' }} >
          <UserAvatar size="50" name="This" flex={.1} />
          <View style={{ flex: 0.8, flexDirection: 'column', margin: 10 }}>
            <Text>Recipent</Text>
            <Text>to me</Text>
            <Text>Today</Text>
          </View>
          <View style={{ flex: 0.1, flexDirection: 'row', alignContent: 'center', alignItems: 'center' }}>

            <Icon name="reply" size={30} color="black" />
            <Icon name="more-vert" size={30} color="black" />
          </View>

        </View>
        <View style={{ flex: 0.7, flexDirection: 'row', alignContent: 'center', alignItems: 'center' }} >
          <Text>inbox</Text>
        </View>
        <Text style={{ height: 1, backgroundColor: 'black' }} />
        <View style={{ flex: 0.1, flexDirection: 'row', alignContent: 'center', alignItems: 'center' }} >
          <View style={{ flex: 0.3, alignContent: 'center', alignItems: 'center' }}>
            <Icon name="reply" size={30} color="black" />
            <Text >Reply</Text>
          </View>
          <View style={{ flex: 0.3, alignContent: 'center', alignItems: 'center' }}>
            <Icon name="reply-all" size={30} color="black" />
            <Text >ReplyAll</Text>
          </View>
          <View style={{ flex: 0.3, alignContent: 'center', alignItems: 'center' }}>
            <Icon name="forward" size={30} color="black" />
            <Text >Forward</Text>
          </View>

        </View>
      </View>
    );
  }
}
