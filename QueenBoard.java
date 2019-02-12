public class QueenBoard{
  private int[][] board;
  private int count;

  //constructor
  public QueenBoard(int size) {
    board = new int[size][size];
  }

  //addQueens function checks a position to see if its threatened
  //If not, place at Queen at that position
  private boolean addQueens(int r, int c){
    //checking for Queens
    if (placeUpLeft(r,c) && placeHorizon(r,c) && placeDownLeft(r,c)) {
      board[r][c] = -1;
      return true;
    }
    return false;
  }

  //helper function to check diagonally up and left if there exists a Queen
  private boolean placeUpLeft(int r, int c) {
    for (int i = 1; i <= r && i <= c; i++) {
      if (board[r-i][c-i] == -1){
        return false;
      }
    }
    return true;
  }

  //helper function to check diagonally down and left if there exists a Queen
  private boolean placeDownLeft(int r, int c) {
    for (int i = 1; i < board.length - r && i <= c; i++) {
        if (board[r+i][c-i] == -1) {
          return false;
      }
    }
    return true;
  }

  //helper function to check horizontally if there exists a Queen
  private boolean placeHorizon(int r, int c) {
    for (int i = c-1; i >= 0; i--) {
      if (board[r][i] == -1) {
        return false;
      }
    }
    return true;
  }

  //removes the Queen at the position
  private boolean removeQueen(int r, int c){
    board[r][c] = 0;
    return true;
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String s = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if (board[i][j] == -1){
          s+="Q ";
        } else {
          s+="_ ";
        }
      }
      s+="\n";
    }
    return s;
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() {
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if (board[i][j] != 0) {
          throw new IllegalStateException("The board is not empty.");
        }
      }
    }
    if (board.length == 0 || board.length == 2 || board.length == 3) return false;
    return solveR(0);
  }

  //helper method for solve
  private boolean solveR(int col) {
    //returns true when the last Queen is placed in the last colmun, one in each column
    if (col == board[0].length) {
      return true;
    }
    //loops through each row by in a column to see if a space in that column can be filled
    for (int row = 0; row < board.length; row++) {
      if (addQueens(row, col)){
        //recursive backtracking, if the next piece cant be placed, remove the current Queen
        if (solveR(col+1)){
          return true;
        }
        removeQueen(row, col);
      }
    }
    return false;
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() {
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if (board[i][j] != 0) {
          throw new IllegalStateException("The board is not empty.");
        }
      }
    }
    if (board.length == 0 || board.length == 2 || board.length == 3) return 0;
    //helper modifies count variable
    countH(0);
    return count;
  }
  //helper method for countSolutions, it is similar to solveR(int col) except it modifies the count
  //instead of stopping at a solution, it continues to find more
  private boolean countH(int col){
    //adds to the count when a solution is found
    if (col == board[0].length) {
      count++;
    }
    for (int row = 0; row < board.length; row++) {
      if (addQueens(row, col)){
        if (countH(col+1)){
          return true;
        }
        removeQueen(row, col);
      }
    }
    return false;
  }

  //function to clear the board
  private void clearBoard(){
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }

  // public static void main(String[] args) {
  //   QueenBoard a = new QueenBoard(2);
  //   System.out.println(a.solve());
  //   System.out.println(a);
  //   a.clearBoard();
  //   System.out.println(a.countSolutions());
  //   System.out.println(a);
  // }
}
