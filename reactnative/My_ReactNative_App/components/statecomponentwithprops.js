import  React from 'react'
class StateComponentWithProps extends React.Component {
   render() {
      return (
         <div>
            <h1>{this.props.headerProp}</h1>
            <h2>{this.props.contentProp}</h2>
         </div>
      );
   }
}
StateComponentWithProps.defaultProps={
  headerProp: "Header from default props...",
  contentProp:"Content default from props..."
}
export default StateComponentWithProps;
