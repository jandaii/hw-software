import React from 'react';
import './App.css'; // import the css file to enable your styles.
import { GameState, Cell } from './game';
import BoardCell from './Cell';

/**
 * Define the type of the props field for a React component
 */
interface Props { }

/**
 * Using generics to specify the type of props and state.
 * props and state is a special field in a React component.
 * React will keep track of the value of props and state.
 * Any time there's a change to their values, React will
 * automatically update (not fully re-render) the HTML needed.
 * 
 * props and state are similar in the sense that they manage
 * the data of this component. A change to their values will
 * cause the view (HTML) to change accordingly.
 * 
 * Usually, props is passed and changed by the parent component;
 * state is the internal value of the component and managed by
 * the component itself.
 */
class App extends React.Component<Props, GameState> {
  private initialized: boolean = false;
  private count: number = 0;
  private ifMove: boolean = false;
  private ifSelect: boolean = false;
  private player: number = 1;
  private winner: number = 0;
  /**
   * @param props has type Props
   */
  constructor(props: Props) {
    super(props)
    /**
     * state has type GameState as specified in the class inheritance.
     */
    this.state = { cells: [] }
  }

  /**
   * Use arrow function, i.e., () => {} to create an async function,
   * otherwise, 'this' would become undefined in runtime. This is
   * just an issue of Javascript.
   */
  newGame = async () => {
    this.count = 0;
    this.winner ++;
    this.ifMove = false;
    const response = await fetch('/newgame');
    const json = await response.json();
    this.setState({ cells: json['cells'] });
    
  }


  /**
   * play will generate an anonymous function that the component
   * can bind with.
   * @param x 
   * @param y 
   * @returns 
   */
  play(x: number, y: number): React.MouseEventHandler {
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      const response = await fetch(`/play?x=${x}&y=${y}`)
      const json = await response.json();
      this.setState({ cells: json['cells'] });
    }
  }
  build(x: number, y: number): React.MouseEventHandler {
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      
      const response = await fetch(`/build?x=${x}&y=${y}`)
      const json = await response.json();
      this.setState({ cells: json['cells'] });
      this.ifSelect = false;
      this.ifMove = false;
      if (this.player === 1) {
        this.player = 2
      } else {
        this.player = 1
      }
    }
  }


  move(x: number, y: number): React.MouseEventHandler {
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      const response = await fetch(`/move?x=${x}&y=${y}`)
      const json = await response.json();
      this.ifMove = true;
      this.setState({ cells: json['cells'] });
      //this.setState({ cells: json['cells'] });
    }
  }
  select(x: number, y: number): React.MouseEventHandler {
    return async (e) => {
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      const response = await fetch(`/select?x=${x}&y=${y}`)
      const json = await response.json();
      this.ifSelect = true;
      this.setState({ cells: json['cells'] });
    }
  }

  set(x: number, y: number): React.MouseEventHandler {
    
    return async (e) => {
      
      // prevent the default behavior on clicking a link; otherwise, it will jump to a new page.
      e.preventDefault();
      this.count = this.count + 1;
      const response = await fetch(`/set?x=${x}&y=${y}&count=${this.count}`)
      const json = await response.json();

      this.setState({ cells: json['cells'] });
      if (this.count === 2) {
        this.player = 2
      } 
      else if (this.count === 4) this.player = 1;
    }
    
  }

  testWinner(cell: Cell): number {
    if (cell.layer === 3 && cell.player !== -1) return cell.player
    else return -1
  }

  getWinner(): number {
    for (let i = 0; i < this.state.cells.length;i ++) {
      if (this.testWinner(this.state.cells[i]) !== -1) return this.testWinner(this.state.cells[i])
    }
    return -1
  }
  

  createCell(cell: Cell, index: number): React.ReactNode {
    if (this.getWinner() > 0) return (
      <div key={index}><BoardCell cell={cell}></BoardCell></div>
    )
    else if (this.count < 4 && this.initialized && cell.selectable) {
      return (
        <div key={index}>
            <a href='/' onClick={this.set(cell.x, cell.y)}>
              <BoardCell cell={cell}></BoardCell>
            </a>
          </div>
      )
    }
    // Before move, they could select again
    else if (!this.ifMove && cell.selectable) return (
      <div key={index}>
          <a href='/' onClick={this.select(cell.x, cell.y)}>
            <BoardCell cell={cell}></BoardCell>
          </a>
        </div>
    ) 
    else if (!this.ifMove && cell.movable) return (
      <div key={index}>
          <a href='/' onClick={this.move(cell.x, cell.y)}>
            <BoardCell cell={cell}></BoardCell>
          </a>
        </div>
    ) 
    else if (!this.ifMove) return (
      <div key={index}><BoardCell cell={cell}></BoardCell></div>
    ) 
    else if (this.ifMove && cell.buildable)return (
      <div key={index}>
          <a href='/' onClick={this.build(cell.x, cell.y)}>
            <BoardCell cell={cell}></BoardCell>
          </a>
        </div>
    ) 
    else if (this.ifMove && !cell.buildable)return (
      <div key={index}><BoardCell cell={cell}></BoardCell></div>
    )
    // if (cell.playable &&!this.ifMove)
    //   /**
    //    * key is used for React when given a list of items. It
    //    * helps React to keep track of the list items and decide
    //    * which list item need to be updated.
    //    * @see https://reactjs.org/docs/lists-and-keys.html#keys
    //    */
    //   return (
    //     <div key={index}>
    //       <a href='/' onClick={this.play(cell.x, cell.y)}>
    //         <BoardCell cell={cell}></BoardCell>
    //       </a>
    //     </div>
    //   )
    //   else if (cell.playable &&this.ifMove)
    //   return(
    //     <div key={index}>
    //     <a href='/' onClick={this.build(cell.x, cell.y)}>
    //       <BoardCell cell={cell}></BoardCell>
    //     </a>
    //   </div>
    //   )
    // else
    //   return (
    //     <div key={index}><BoardCell cell={cell}></BoardCell></div>
    //   )
  }

  /**
   * This function will call after the HTML is rendered.
   * We update the initial state by creating a new game.
   * @see https://reactjs.org/docs/react-component.html#componentdidmount
   */
  componentDidMount(): void {
    /**
     * setState in DidMount() will cause it to render twice which may cause
     * this function to be invoked twice. Use initialized to avoid that.
     */
    if (!this.initialized) {
      this.newGame();
      this.initialized = true;
    }
  }

  createTips():React.ReactNode{
    if (this.getWinner() > 0) return (
      <div>The game ends! The winner is player{this.getWinner()}, if you want to play again, please click new game</div>
    )
    if (this.count < 4) return (
      <div>Now is player {this.player} setting your worker</div>
    )
    else if (!this.ifSelect) return (
      <div>Now is player {this.player} select one worker to work</div>
    ) 
    else if (this.ifSelect && !this.ifMove) return (
      <div>Now is player {this.player} move the selected worker to move</div>
    ) 
    else if (this.ifMove) return (
      <div>Now is player {this.player} make the selected worker to build...</div>
    ) 
  }

  /**
   * The only method you must define in a React.Component subclass.
   * @returns the React element via JSX.
   * @see https://reactjs.org/docs/react-component.html
   */
  render(): React.ReactNode {
    /**
     * We use JSX to define the template. An advantage of JSX is that you
     * can treat HTML elements as code.
     * @see https://reactjs.org/docs/introducing-jsx.html
     */
    
    return (
      <div>
        {this.createTips()}
        <div id="board">
          {this.state.cells.map((cell, i) => this.createCell(cell, i))}
        </div>
        <div id="bottombar">
          <button onClick={/* get the function, not call the function */this.newGame}>New Game</button>
          {/* Exercise: implement Undo function */}
          <button>Undo</button>
        </div>
      </div>
    );
  }
}

export default App;
