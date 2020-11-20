package PriorityQueues.SliderPuzzle;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class Board {

    private final int[][] board;
    private int rowEmpty;
    private int colEmpty;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        int size = tiles.length;
        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] == 0) {
                    rowEmpty = i;
                    colEmpty = j;
                }
                board[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        int n = dimension();

        boardString.append(n);
        boardString.append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boardString.append(board[i][j]);
                boardString.append(" ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    // board dimension n
    public int dimension() {
        return board.length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        int order = 1;
        int sz = dimension() - 1;
        for (int i = 0; i < dimension(); i++)
        {
            for (int j = 0; j < dimension(); j++) {
                if (j == sz && i == sz) break;
                if (board[i][j] != order) ++count;

                ++order;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                int value = board[i][j];
                if (value == 0) continue;
                int row = calculateRow(value);
                int col = calculateCol(value, row);

                sum += Math.abs(row - i) + Math.abs(col - j);
            }
        }
        return sum;
    }

    private int calculateRow(int value) {
        int res;
        if (value % dimension() == 0) res = value / dimension() - 1;
        else res = value / dimension();

        return res;
    }

    private int calculateCol(int value, int row) {
        return value - 1 - dimension() * row;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;

        Board yBoard;
        if (y.getClass() == this.getClass()) {
            yBoard = (Board) y;
        } else return false;

        if (this.board.length != yBoard.board.length) return false;
        else {
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board.length; j++) {
                    if (this.board[i][j] != yBoard.board[i][j]) return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> list = new ArrayList<>();

        int size = dimension();
        int[][] firstBoard = new int[size][size];
        int[][] secondBoard = new int[size][size];
        int[][] thirdBoard = new int[size][size];
        int[][] fourthBoard = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                firstBoard[i][j] = board[i][j];
                secondBoard[i][j] = board[i][j];
                thirdBoard[i][j] = board[i][j];
                fourthBoard[i][j] = board[i][j];
            }
        }
        if (rowEmpty - 1 >= 0) {
            swap(firstBoard, rowEmpty - 1, colEmpty);
            list.add(new Board(firstBoard));
        }

        if (rowEmpty + 1 < size) {
            swap(secondBoard, rowEmpty + 1, colEmpty);
            list.add(new Board(secondBoard));
        }

        if (colEmpty - 1 >= 0) {
            swap(thirdBoard, rowEmpty, colEmpty - 1);
            list.add(new Board(thirdBoard));
        }

        if (colEmpty + 1 < size) {
            swap(fourthBoard, rowEmpty, colEmpty + 1);
            list.add(new Board(fourthBoard));
        }
        return list;
    }

    private void swap(int[][] a, int i, int j) {
        int temp = a[i][j];
        a[i][j] = a[rowEmpty][colEmpty];
        a[rowEmpty][colEmpty] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int size = dimension();
        int i = 0;
        int j = 0;
        int x = size - 1;
        int y = size - 1;

        if (board[i][j] == 0) i++;
        if (board[x][y] == 0) x--;

        int[][] twinBoard = new int[size][size];
        for (int k = 0; k < size; k++) {
            System.arraycopy(board[k], 0, twinBoard[k], 0, size);
        }

        int temp = twinBoard[i][j];
        twinBoard[i][j] = twinBoard[x][y];
        twinBoard[x][y] = temp;
        return new Board(twinBoard);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        int[][] otherTiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
                otherTiles[i][j] = tiles[i][j];
            }

        int temp = otherTiles[0][0];
        otherTiles[0][0] = otherTiles[2][2];
        otherTiles[2][2] = temp;

        Board board = new Board(tiles);
        Board other = new Board(otherTiles);

        System.out.println("hamming: " + board.hamming());
        System.out.println("hamming: " + board.hamming());

        System.out.println("equals: " + board.equals(other));
        for (Board b: board.neighbors()) {
            System.out.println(b.toString());
        }
        System.out.println("equals: " + board.equals(other));
        System.out.println("hamming: " + board.hamming());
        System.out.println("isGoal: " + board.isGoal());
        System.out.println("twin: ");
        System.out.println(board.twin());
        System.out.println(board.twin());
    }
}
