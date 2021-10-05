public class Sudoku{

	public static int[][] GRID_TO_SOLVE = {
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
	};
	
	private int[][] board;
	public static final int EMPTY = 0; 
	public static final int SIZE = 9;
	
	public Sudoku(int[][] board) {
		this.board = new int[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	private boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[row][i] == number)
				return true;
		
		return false;
	}
	
	private boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (board[i][col] == number)
				return true;
		
		return false;
	}
	
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		// ASas
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (board[i][j] == number)
					return true;
		
		return false;
	}
	
	private boolean isOk(int row, int col, int number) {
		return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
	}
	
       public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
          // we search an empty cell
          if (board[row][col] == EMPTY) {
            // we try possible numbers
            for (int number = 1; number <= SIZE; number++) {
              if (isOk(row, col, number)) {
                // number ok. it respects sudoku constraints
                board[row][col] = number;

                if (solve()) { // we start backtracking recursively
                  return true;
                } else { // if not a solution, we empty the cell and we continue
                  board[row][col] = EMPTY;
                }
             }
            }

            return false; 
           }
          }
         }

         return true; 
	}
	
	public void display() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j]);
			}
		
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
		System.out.println("Sudoku grid to solve");
		sudoku.display();
		
		if (sudoku.solve()) {
			System.out.println("Sudoku Grid solved with simple BT");
			sudoku.display();
		} else {
			System.out.println("Unsolvable");
		}
	}

}
