public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    public int myOpenCount;
    public PercolationUF (IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        myFinder = finder;
        myFinder.initialize((size * size + 2) );
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }
    public boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length)
        {
            return false;
        }
        if (col < 0 || col >= myGrid[0].length)
        {
            return false;
        }
        return true;
    }
    public int getIndex(int row, int col, int size) {
        return (row*size) + col;
    }
    @Override
    public boolean isOpen(int row, int col) {
        if(!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(String.format("(%d,%d) is out of the bounds", row,col));
        }
        return myGrid[row][col];
    }
    @Override
    public boolean isFull(int row, int col) {
        if(!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(String.format("(%d,%d) is out of the bounds", row,col));
        }
        return myFinder.connected(getIndex(row, col, myGrid.length), VTOP);
    }
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
    @Override
    public void open(int row, int col) {
        if(!inBounds(row, col))
        {
            throw new IndexOutOfBoundsException(String.format("(%d,%d) is out of the bounds", row,col));
        }
        int ind = getIndex(row, col, myGrid.length);
        if(myGrid[row][col])
        {
            return;
        }
        myGrid[row][col] = true;
        myOpenCount+=1;
        if(inBounds(row, col-1) && isOpen(row, col-1))
        {
            myFinder.union(ind, getIndex(row, col-1, myGrid.length));
        }
        if(inBounds(row, col+1) && isOpen(row, col+1))
        {
            myFinder.union(ind, getIndex(row, col+1, myGrid.length));
        }
        if(inBounds(row-1, col) && isOpen(row-1, col))
        {
            myFinder.union(ind, getIndex(row - 1, col, myGrid.length));
        }
        if(inBounds(row+1, col) && isOpen(row+1, col))
        {
            myFinder.union(ind, getIndex(row+1, col, myGrid.length));
        }
        if(row==0)
        {
            myFinder.union(ind, VTOP);
        }
        if(row==myGrid.length-1)
        {
            myFinder.union(ind, VBOTTOM);
        }
    }
}