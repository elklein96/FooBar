import java.util.LinkedList;

public class Answer {
    private static int ROWS;
    private static int COLS;
    private static final int rowDir[] = { -1, 0, 1, 0 };
    private static final int colDir[] = { 0, -1, 0, 1 };

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    };

    private static class Node {
        Point pt;
        int dist;

        public Node(Point pt, int dist) {
            this.pt = pt;
            this.dist = dist;
        }

        public Point getPoint() {
            return this.pt;
        }

        public int getDist() {
            return this.dist;
        }
    };

    private static boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROWS) && (col >= 0) && (col < COLS);
    }

    private static int bfs(int maze[][], Point start, Point finish) {
        boolean[][] visited = new boolean[COLS][ROWS];
        LinkedList<Node> q = new LinkedList<Node>();
        Node s = new Node(start, 1);
        Node curr, adjCell;
        Point pt;

        visited[start.getY()][start.getX()] = true;
        q.push(s);

        while (q.size() > 0) {
            curr = q.peek();
            pt = curr.getPoint();

            if (pt.getX() == finish.getX() && pt.getY() == finish.getY()) {
                return curr.getDist();
            }

            q.pop();

            for (int i = 0; i < 4; i++) {
                int row = pt.getX() + rowDir[i];
                int col = pt.getY() + colDir[i];

                if (isValid(row, col) && maze[col][row] == 0 && !visited[col][row]) {
                    visited[col][row] = true;
                    adjCell = new Node(new Point(row, col), curr.getDist() + 1);
                    q.push(adjCell);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static int answer(int[][] maze) {
        Point start, finish;
        int shortestPath, tmpPath;

        COLS = maze.length;
        ROWS = maze[0].length;

        start = new Point(0, 0);
        finish = new Point(ROWS - 1, COLS - 1);

        shortestPath = bfs(maze, start, finish);

        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (maze[i][j] == 1) {
                    maze[i][j] = 0;
                    tmpPath = bfs(maze, start, finish);
                    if (shortestPath > tmpPath) {
                        shortestPath = tmpPath;
                    }
                    maze[i][j] = 1;
                }
            }
        }
        return shortestPath;
    }

    public static void main(String[] args) {
//         int maze[][] = new int[][] {
//             {0, 1, 1},
//             {1, 0, 0},
//             {1, 0, 0}
//         };

//         int maze[][] = new int[][] {
//             {0, 1, 1, 0},
//             {0, 0, 0, 1},
//             {1, 1, 0, 0},
//             {1, 1, 1, 0}
//         };

//         int maze[][] = new int[][] {
//             {0, 0, 0, 0, 0, 0},
//             {1, 1, 1, 1, 1, 0},
//             {0, 0, 0, 0, 0, 0},
//             {0, 1, 1, 1, 1, 1},
//             {0, 1, 1, 1, 1, 1},
//             {0, 0, 0, 0, 0, 0}
//         };

        int maze[][] = new int[][] {
            {0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 1, 1},
            {0, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 0, 0}
        };

        int dist = answer(maze);

        if (dist != Integer.MAX_VALUE)
            System.out.println("Shortest Path is " + dist);
        else
            System.out.println("Shortest Path doesn't exist");
    }
}
