public class QueenBoard{
  private int[][] board;
  public QueenBoard(int size) {
    board = new int[size][size];
  }
  private boolean addQueens(int r, int c){
    if (board[r][c] == 0) {
      board[r][c] = -1;
    }
    for (int i = c+1; i < board[r].length; i++) {
      board[r][i] = 1;
    }
    for (int i = r+1; i < board.length; i++) {
      for (int j = c+1; j < board[r].length; j++) {
        board[i][j] = 1;
      }
    }
    for (int i = r-1; i > 0; i--) {
      for (int j = c+1; j < board[r].length; j++) {
        board[i][j] = 1;
      }
    }
  }

  private boolean placeUpLeft(int r, int c) {
    for (int i = r-1; i > 0; i--) {
      for (int j = c-1; j > 0; j--) {
        if (board[i][j] != 0){
          return false;
        }
      }
    }
    return true;
  }
  private boolean placeDownLeft(int r, int c) {
    for (int i = r+1; i < board.length; i++) {
      for (int j = c-1; j > 0; j--) {
        if (board[i][j] != 0) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean placeHorizon(int r, int c) {
    for (int i = c-1; i > 0; i-- {
      if (board[r][i] != 0) {
        return false;
      }
    }
    return true;
  }
  private boolean removeQueen(int r, int c){

  }
  public String toString(){

  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (!(addQueens(i,j))) {
          removeQueen(i,j);
        }
      }
    }
  }
  public int countSolutions() {

  }
}
