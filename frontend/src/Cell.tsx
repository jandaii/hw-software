import React from 'react';
import { Cell } from './game';

interface Props {
  cell: Cell
}
// component
class BoardCell extends React.Component<Props> {
  render(): React.ReactNode {
    let playable = this.props.cell.playable ? 'playable' : '';
    if (this.props.cell.movable) {
      playable = 'movable'
    }
    
    return (
      <div className={`cell ${playable}`}>{this.props.cell.text}</div>
    )
  }
}

export default BoardCell;