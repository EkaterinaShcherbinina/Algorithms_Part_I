package PriorityQueues.PQ.SliderPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class Solver {
    private boolean isSolvable = true;
    private final LinkedList<Board> listBoardNodes = new LinkedList<>();

    private class SearchNode implements Comparable<SearchNode> {
        int moves;
        SearchNode prev;
        Board board;
        int manhattan;
        int priority;

        public SearchNode(Board board, SearchNode prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            this.manhattan = board.manhattan();

            priority = moves + manhattan;
        }

        @Override
        public int compareTo(SearchNode searchNode) {
            return Integer.compare(this.priority, searchNode.priority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        Board twin = initial.twin();
        solve(initial, twin);
    }

    private void solve(Board board, Board twin) {
        MinPQ<SearchNode> boardMinPq = new MinPQ<>();
        MinPQ<SearchNode> twinMinPq = new MinPQ<>();

        SearchNode boardNode = new SearchNode(board, null, 0);
        SearchNode twinNode = new SearchNode(twin, null, 0);

        boardMinPq.insert(boardNode);
        twinMinPq.insert(twinNode);
        SearchNode boardLast = addNeighbors(boardNode, twinNode, boardMinPq, twinMinPq);

        while (boardLast != null) {
            listBoardNodes.addFirst(boardLast.board);

            boardLast = boardLast.prev;
        }
    }

    private SearchNode addNeighbors(SearchNode initial, SearchNode twinInitial, MinPQ<SearchNode> boardMinPq, MinPQ<SearchNode> twinMinPq) {
        SearchNode boardNode = initial;
        SearchNode twinNode = twinInitial;
        SearchNode res;

        while (true) {
            if (twinNode.board.isGoal()) {
                res = twinNode;
                isSolvable = false;
                break;
            }
            if (boardNode.board.isGoal()) {
                res = boardNode;
                break;
            }

            boardNode = boardMinPq.delMin();
            if(boardMinPq.size() >= 50000) boardMinPq = new MinPQ<>();
            twinNode = twinMinPq.delMin();
            if(twinMinPq.size() >= 50000) twinMinPq = new MinPQ<>();

            for (Board val: boardNode.board.neighbors()) {
                if (boardNode.prev == null || !boardNode.prev.board.equals(val))
                    boardMinPq.insert(new SearchNode(val, boardNode, boardNode.moves + 1));
            }

            for (Board val: twinNode.board.neighbors()) {
                if (twinNode.prev == null || !twinNode.prev.board.equals(val))
                    twinMinPq.insert(new SearchNode(val, twinNode, twinNode.moves + 1));
            }
        }

        return res;
    }

    public boolean isSolvable() {
       return isSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {

        if (!isSolvable) return -1;
        else return listBoardNodes.size() - 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {

        if (!isSolvable) return null;
        else return listBoardNodes;
    }

    // test client (see below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
