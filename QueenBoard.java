public class QueenBoard{
  private int[][] board;
  private int count;
  public QueenBoard(int size) {
    board = new int[size][size];
  }
  private boolean addQueens(int r, int c){
    // String f = "";
    if (placeUpLeft(r,c) && placeHorizon(r,c) && placeDownLeft(r,c)) {
      board[r][c] = -1;
      return true;
    }
    return false;
    // if (placeUpLeft(r,c)) f+="UpLeft";
    // if (placeHorizon(r,c)) f+="Horiz";
    // if (placeDownLeft(r,c)) f+="DownLeft";
    // if (placeUpLeft(r,c) && placeHorizon(r,c) && placeDownLeft(r,c)) {
    //    board[r][c] = -1;
    //  }
    // return f;
  }

  private boolean placeUpLeft(int r, int c) {
    for (int i = 1; i <= r && i <= c; i++) {
      if (board[r-i][c-i] == -1){
        return false;
      }
    }
    return true;
  }

  private boolean placeDownLeft(int r, int c) {
    for (int i = 1; i < board.length - r && i <= c; i++) {
        if (board[r+i][c-i] == -1) {
          return false;
      }
    }
    return true;
  }

  private boolean placeHorizon(int r, int c) {
    for (int i = c-1; i >= 0; i--) {
      if (board[r][i] == -1) {
        return false;
      }
    }
    return true;
  }

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
          s+="0 ";
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
    return solveR(0);
  }

  private boolean solveR(int col) {
    if (col == board[0].length) {
      return true;
    }
    for (int row = 0; row < board.length; row++) {
      if (addQueens(row, col)){
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
    countH(0);
    return count;
  }

  private boolean countH(int col){
    if (col == board[0].length) {
      count++;
    } else {
      for (int row = 0; row < board.length; row++) {
        if (addQueens(row, col)){
          if (countH(col+1)){
            return true;
          }
          removeQueen(row, col);
        }
      }
    }
    return false;
  }

  private void clearBoard(){
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }

  public static void main(String[] args) {
    QueenBoard a = new QueenBoard(8);
    a.solve();
    // System.out.println(a.addQueens(0,0));
    // System.out.println(a.addQueens(0,1));
    // System.out.println(a.addQueens(1,1));
    // System.out.println(a.addQueens(2,1));
    // System.out.println(a.addQueens(0,2));
    // System.out.println(a.addQueens(1,2));
    // System.out.println(a.addQueens(2,2));
    // System.out.println(a.addQueens(3,2));
    // System.out.println(a.addQueens(4,2));
    // System.out.println(a.addQueens(1,3));
    // System.out.println(a.addQueens(3,4));;
    // System.out.println(a.addQueens(6,5));
    System.out.println(a);
    a.clearBoard();
    System.out.println(a.countSolutions());
    System.out.println(a);
  }
}
