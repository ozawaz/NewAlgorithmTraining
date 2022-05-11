package code.oneToForty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code23_岛屿_高级 {

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind {
        private final int[] parent;
        private final int[] size;
        /**
         * 帮助数组，在查找父节点压缩路径的时候用到
         */
        private final int[] help;
        private int sets;
        private final int row;
        private final int col;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            // 初始化
            parent = new int[row * col];
            size = new int[row * col];
            help = new int[row * col];
            sets = 0;
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
            // 边界
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;
            }
            // 计算出点位置对应的的下标位置
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            // 假如有一个点没被初始化过，直接跳过
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
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

        private int connect(int r, int c) {
            int index = index(r, c);
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r + 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
            }
            return sets;
        }
    }
}
