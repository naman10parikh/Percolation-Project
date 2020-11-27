import java.util.*;

public class PercolationBFS extends PercolationDFSFast {

    public PercolationBFS(int x) {
        super(x);
    }

    @Override
    public void dfs(int row, int col) {
        if (!inBounds(row, col)) return;
        myGrid[row][col] = FULL;
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.add((row * myGrid.length) + col);
        while (queue1.size() != 0) {
            int rem = queue1.remove();
            int r1 = rem / myGrid.length;
            int c1 = rem % myGrid.length;
            if (inBounds(r1, c1 + 1) && isOpen(r1, c1 + 1) && !isFull(r1, c1 + 1)) {
                myGrid[r1][c1 + 1] = FULL;
                queue1.add(r1 * myGrid.length + c1 + 1);
            }
            if (inBounds(r1, c1 - 1) && isOpen(r1, c1 - 1) && !isFull(r1, c1 - 1)) {
                myGrid[r1][c1 - 1] = FULL;
                queue1.add(r1 * myGrid.length + c1 - 1);
            }
            if (inBounds(r1 + 1, c1) && isOpen(r1 + 1, c1) && !isFull(r1 + 1, c1)) {
                myGrid[r1 + 1][c1] = FULL;
                queue1.add((r1 + 1) * myGrid.length + c1);
            }
            if (inBounds(r1 - 1, c1) && isOpen(r1 - 1, c1) && !isFull(r1 - 1, c1)) {
                myGrid[r1 - 1][c1] = FULL;
                queue1.add((r1 - 1) * myGrid.length + c1);
            }
        }
    }
}