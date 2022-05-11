package code.oneToForty;

import java.util.HashMap;
import java.util.List;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code22_岛屿_初级_并查集 {

    /**
     * 并查集结构，map
     * 因为字符之间的比较会变成值比较，因此需要一个额外的结构dot
     * 这样就可以直接比较内存地址的位置了
     */
    private static class UnionFind1<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind1(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public static class Node<V> {
            V value;
            public Node(V v) {
                value = v;
            }
        }
    }

    /**
     * 其主要思路就是，首先是获取到这个二维数组的行长度row和列长度col，
     * 然后进行初始化的时候，进行一个定义，假如此时的位置是（r, c），
     * 那么在数组中r * col + c的位置就代表这个点
     */
    public static class UnionFind2 {
        private final int[] parent;
        private final int[] size;
        /**
         * 帮助数组，在查找父节点压缩路径的时候用到
         */
        private final int[] help;
        private int sets;
        int row;
        int col;

        public UnionFind2(char[][] board) {
            // 获取行和列的长度
            row = board.length;
            col = board[0].length;
            // 初始化
            parent = new int[row * col];
            size = new int[row * col];
            help = new int[row * col];
            sets = 0;
            // 遍历初始化
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    if (board[r][c] == '1') {
                        // 找到对应的数组下表
                        int i = index(r, c);
                        parent[i] = i;
                        size[i] = 1;
                        sets++;
                    }
                }
            }
        }

        public int getSets() {
            return sets;
        }

        /**
         * 根据此时的位置找到对应的数组下标
         * @param r 行
         * @param c 列
         * @return 返回下标
         */
        private int index(int r, int c) {
            return r * col + c;
        }

        /**
         * 这里就是根据对应的下标位置找到其祖先下标
         * @param i 下标位置
         * @return 返回祖先下标
         */
        private int findAncestors(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        private void union(int r1, int c1, int r2, int c2) {
            // 计算出点位置对应的的下标位置
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            // 找祖先
            int f1 = findAncestors(i1);
            int f2 = findAncestors(i2);
            // 判断
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }

    /**
     * 在进行主方法调用的时候，其主要思路就是，
     * 判断一个点的左上是否有'1'，有则进行合并操作。
     * 此时实际代码的做法就是分成三个循环，
     * 第一个和第二个就是分别循环最上面一行和最左边一列，
     * 然后剩下的点就按照判断左上的来。
     * 这样做，就是没必要在一个循环中，进行重复的边界判断，节省了一点性能。
     */
    public static int numIslands2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        UnionFind2 uf = new UnionFind2(board);

        // 第一个循环判断最上面一行
        for (int c = 1; c < col; c++) {
            if (board[0][c - 1] == '1' && board[0][c] == '1') {
                uf.union(0, c - 1, 0, c);
            }
        }
        // 第二个循环判断最左一列
        for (int r = 1; r < row; r++) {
            if (board[r - 1][0] == '1' && board[r][0] == '1') {
                uf.union(r - 1, 0, r, 0);
            }
        }
        // 第三个循环判断剩下的点
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (board[r][c] == '1') {
                    // 上
                    if (board[r][c - 1] == '1') {
                        uf.union(r, c - 1, r, c);
                    }
                    // 左
                    if (board[r - 1][c] == '1') {
                        uf.union(r - 1, c, r, c);
                    }
                }
            }
        }

        return uf.getSets();
    }
}
