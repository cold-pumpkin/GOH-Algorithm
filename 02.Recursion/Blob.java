/* Counting Cells in a Blob
  : 서로 연결된(상하좌우+대각선 포함) image pixel들의 집합을 blob이라고 할 때, 
    픽셀 (x, y)가 포함된 blob의 크기 구하기

  1) 현재 픽실에 image color가 아니라면 0 반환
  2) image color라면 
    - 현재 픽셀을 카운트
    - 현재 픽셀이 중복 카운트 되는 것 방지하기 위해 다른 색을 칠하기
    - 그 픽셀이 속한 blob의 크기를 카운트해 카운터에 더해줌
*/

public class Blob {
    private static int BACKGROUND_COLOR = 0;
    private static int IMAGE_COLOR = 1;
    private static int ALREADY_COUNTED = 2;
    private static int N = 8;
    private static int[][] grid = {
        {1, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 1, 0, 0, 1, 0, 0},
        {1, 1, 0, 0, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 0},
        {0, 1, 0, 1, 0, 1, 0, 0},
        {1, 0, 0, 0, 1, 0, 0, 1},
        {0, 1, 1, 0, 0, 1, 1, 1},
    };

    public static int countCells(int x, int y) {
        // * 범위 체크
        if (x < 0 || x >= N || y < 0 || y >= N)
            return 0;
        // * image color 체크
        else if (grid[x][y] != IMAGE_COLOR)
            return 0;
        // * image color 라면
        else {
            // - 다른 색으로 칠한 후
            grid[x][y] = ALREADY_COUNTED;
            // - 현재 위치를 포함해 연결되어있는 image color 픽셀 카운트
            return 1 + countCells(x-1, y+1) + countCells(x, y+1) + countCells(x+1, y+1) + countCells(x-1, y) +
                    countCells(x+1, y) + countCells(x-1, y-1) + countCells(x, y-1) + countCells(x+1, y-1);

        }
    }
    public static void main(String[] args) {
        printGrid();
        int blobCount = countCells(3, 5);
        System.out.println();
        System.out.println("BlobCount : " + blobCount);
        printGrid();
      }
    
      private static void printGrid() {
        for (int x = 0; x < N; x++) {
          System.out.print("[");
          for (int y = 0; y < N; y++)
            System.out.print(grid[x][y] + ", ");
          System.out.println("]");
        }
      }
}