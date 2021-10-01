class Hello{

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
    public static final int EMPTY = 0; // empty cell
	public static final int SIZE = 9; // size of our Sudoku grids
    static boolean isInRow(int row, int number) {
		for (int i = 0; i < SIZE; i++)
			if (GRID_TO_SOLVE[row][i] == number)
				return true;
		return false;
	}
	
	// we check if a possible number is already in a column
	static boolean isInCol(int col, int number) {
		for (int i = 0; i < SIZE; i++)
			if (GRID_TO_SOLVE[i][col] == number)
				return true;
		
		return false;
	}
	
	// we check if a possible number is in its 3x3 box
	static boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		
		for (int i = r; i < r + 3; i++)
			for (int j = c; j < c + 3; j++)
				if (GRID_TO_SOLVE[i][j] == number)
					return true;
		
		return false;
	}
	
	// combined method to check if a number possible to a row,col position is ok
	static boolean isok(int row, int col, int number) {
		return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
	}

    static int run_number(){
        int num = (int)(Math.random()*10);
        if(num==0){
            run_number();
        }
        return num;
    }
    
    static void random_number(){
        int i =0;
        while(i!=9)
        {
            int row = (int)(Math.random()*9);
            int col = (int)(Math.random()*9);
            if(GRID_TO_SOLVE[row][col] == 0)
            {
                // int x = 0;
                int num = run_number();
                // System.out.println(num);
                if(isok(row,col,num))
                {
                    GRID_TO_SOLVE[row][col] = num;
                    i++;
                }
            }
        }
    }

    // This Main is God Don't Touch
    public static void main(String[] args){
        random_number();
        Sudoku sudoku = new Sudoku(GRID_TO_SOLVE);
		System.out.println("Sudoku grid to solve");
		sudoku.display();
		
		// we try resolution
		if (sudoku.solve()) {
			System.out.println("Sudoku Grid solved with simple BT");
			sudoku.display();
		} else {
			System.out.println("Unsolvable");
		}
    }
}

