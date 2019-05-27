/* 미로 찾기
  : 출발 위치(0, 0)에서 이미 가본 곳을 다시 지나지 않고 출구(N, N)까지 가는 경로가 있는지?

  - 조건에 맞는 경로를 찾기 위해서는
    1) 현재 위치가 출구이거나
    2) 이웃한 셀들 중 하나에서 이미 가본 곳을 다시 지나지 않고 출구까지 가는 경로가 있어야 함
*/

public class Maze {
    private static int N = 8;
    private static int[][] maze = {
        {0, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 1, 0, 1, 1, 0, 1},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 0, 0, 1, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 1, 1},
        {0, 1, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 1, 0, 0, 0, 1},
        {0, 1, 1, 1, 0, 1, 0, 0}
    };
    private static final int PATHWAY_COLOR = 0; // 
    private static final int WALL_COLOR = 1;    // 벽
    private static final int BLOCKED_COLOR = 2; // 방문했는데 출구까지의 경로상에 있지 않음이 밝혀진 셀
    private static final int PATH_COLOR = 3;    // 방문했는데 아직 출구로 가는 경로가 될 가능성 존재하는 셀 


    public static boolean findMazePath(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N)
            // * 범위를 벗어난 경우
            return false;
        else if (maze[x][y] != PATHWAY_COLOR)
            // * 벽이거나, 이미 방문한 경우
            return false;
        else if (x == N-1 && y == N-1) {
            // * 출구에 닿은 경우
            maze[x][y] = PATH_COLOR;
            return true;
        } else {
            // * 아직 방문 안한 경우
            maze[x][y] = PATH_COLOR;     // 밝혀지지 않았으니 일단 PATHWAY_COLOR
            if ( findMazePath(x-1, y) || findMazePath(x, y+1) || 
                findMazePath(x+1, y) || findMazePath(x, y-1) )
                    return true;    // 방문하지 않았으며 경로에 해당

            // * 방문 안했지만 경로에 해당하지 않는 경우
            maze[x][y] = BLOCKED_COLOR;   
            return false;
        }
    }

    public static void printMaze() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) 
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printMaze();
        System.out.println(findMazePath(0, 0));
        System.out.println();
        printMaze();
    }
}