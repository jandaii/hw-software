interface GameState {
  cells: Cell[];
}

interface Cell {
  text: string;
  playable: boolean;
  x: number;
  y: number;
  layer: number;
  player: number;
  selectable: boolean;
  movable:boolean;
  buildable:boolean
}

export type { GameState, Cell }