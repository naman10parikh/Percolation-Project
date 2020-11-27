public class PercolationDFSFast extends PercolationDFS {
    public PercolationDFSFast(int x) {
        super(x);
    }
    @Override
    public void updateOnOpen(int row, int col) {
        int k = 0;
        if (row == 0) k = 1;
        if (row != 0 && myGrid[row - 1][col] == FULL) k = 1;
        if (row != myGrid[row].length - 1 && myGrid[row+1][col] == FULL) k = 1;
        if (col != 0 && myGrid[row][col - 1] == FULL) k = 1;
        if (col != myGrid[col].length - 1 && myGrid[row][col + 1] == FULL) k = 1;
        if (k == 1) dfs(row, col);
    }
}