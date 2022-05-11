package code.oneToForty;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code21_岛屿_初级_感染 {

    public int numIslands(char[][] board) {
        // 区域数量
        int region = 0;
        // 循环找出区域数量
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 假如遇到岛屿
                if (board[i][j] == '1') {
                    // 区域数量加
                    region++;
                    // 感染操作
                    infected(board, i, j);
                }
            }
        }
        return region;
    }

    private void infected(char[][] board, int i, int j) {
        // 边界条件判断
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        // 感染
        board[i][j] = 2;
        // 四个方向进行感染
        infected(board, i + 1, j);
        infected(board, i - 1, j);
        infected(board, i, j + 1);
        infected(board, i, j - 1);
    }
}
