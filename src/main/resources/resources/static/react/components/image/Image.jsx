import { Block } from 'jsxstyle';

var React = require('react');

var Image = React.createClass({
   render: function() {
        <Block margin="0 auto" width={this.props.width}>
            <img src={this.props.imageSrc} />
        </Block>
   }
});

module.exports = Image;