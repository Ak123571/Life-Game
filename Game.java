 /* 
 * Imagine an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two
   possible states, live or dead. Every cell interacts with its eight neighbors, which are the cells that are
   directly horizontally, vertically, or diagonally adjacent.
 * Conventions
 *  int[][] board;   // The game board is a 2D array.
 *  board[4][5] = 1; // Means that the cell at (4,5) is live.
 *  board[4][5] = 0; // Means that the cell at (4,5) is dead.
 *
 * Rules of the game
 * 1. Any live cell with fewer than two live neighbors dies, as if by loneliness.
 * 2. Any live cell with more than three live neighbors dies, as if by overcrowding.
 * 3. Any live cell with two or three live neighbors lives, unchanged,to the next generation.
 * 4. Any dead cell with exactly three live neighbors comes to life.
 */
public final class Game 
    {

    // The value representing a dead cell
    public final static int DEAD    = 0x00;

    // The value representing a live cell
    public final static int LIVE    = 0x01;

    public final static void main(String[] args)
     {

        // Implementation
        Game gof = new Game();
        gof.test(5);
     }

    private void test(int nrIterations) 
       {

   
        int[][] board = {{DEAD, DEAD, DEAD, DEAD, DEAD},
                         {DEAD, DEAD, DEAD, LIVE, DEAD},
                         {DEAD, DEAD, LIVE, LIVE, DEAD},
                         {DEAD, DEAD, DEAD, LIVE, DEAD},
                         {DEAD, DEAD, DEAD, DEAD, DEAD}}; 
        
        System.out.println("Life Game ");
        printBoard(board);

        for (int i = 0 ; i < nrIterations ; i++) 
         {
            System.out.println();
            board = getNextBoard(board);
            printBoard(board);
          }
    }

    private void printBoard(int[][] board)
      {

        for (int i = 0, e = board.length ; i < e ; i++) 
            {

            for (int j = 0, f = board[i].length ; j < f ; j++) 
               {
                System.out.print(Integer.toString(board[i][j]) + ",");
               } 
            System.out.println();
        }
    }

     
    public int[][] getNextBoard(int[][] board) 
           {

         
               if (board.length == 0 || board[0].length == 0)
                 {
                    throw new IllegalArgumentException("Board must have a positive amount of rows and/or columns");
                  }

                int nrRows = board.length;
                int nrCols = board[0].length;

         
                int[][] buf = new int[nrRows][nrCols];

                for (int row = 0 ; row < nrRows ; row++) 
                {
	
                    for (int col = 0 ; col < nrCols ; col++)
                      {
                          buf[row][col] = getNewCellState(board[row][col], getLiveNeighbours(row, col, board));
                      }
                }   
        return buf;
    }

     
    private int getLiveNeighbours(int cellRow, int cellCol, int[][] board) 
      {

        int liveNeighbours = 0;
        int rowEnd = Math.min(board.length , cellRow + 2);
        int colEnd = Math.min(board[0].length, cellCol + 2);

        for (int row = Math.max(0, cellRow - 1) ; row < rowEnd ; row++) 
          {
            
            for (int col = Math.max(0, cellCol - 1) ; col < colEnd ; col++) 
             {
                 
                if ((row != cellRow || col != cellCol) && board[row][col] == LIVE)
                 {
                    liveNeighbours++;
                }
            }
        }
        return liveNeighbours;
    }


     
    private int getNewCellState(int curState, int liveNeighbours)
      {

        int newState = curState;

        switch (curState) {
        case LIVE:

            // Any live cell with fewer than two  live neighbours dies

            if (liveNeighbours < 2) 
             {
                newState = DEAD;
             }

            // Any live cell with more than three live neighbours dies, as if by overcrowding.
             
            if (liveNeighbours > 3) 
             {
                newState = DEAD;
             }
            
            // Any live cell with two or three live neighbours lives,unchanged, to the next generation.  
             
            if (liveNeighbours == 2 || liveNeighbours == 3) 
             {
                newState = LIVE;
             }
            break;

        case DEAD:
            // Any dead cell with exactly three live neighbours comes to life
            
            if (liveNeighbours == 3) {
                newState = LIVE;
            }
            break;

        default:
            throw new IllegalArgumentException("The cell must be either LIVE or DEAD");
        }			
        return newState;
    }
}

