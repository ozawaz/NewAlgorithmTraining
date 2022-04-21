package code;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 现在有一个二维数组，其中记录着每一个人与其他人的关系.
 * 例如，[0][1]=1，代表0与1相互认识，那么反过来[1][0]=1，这个认识关系是相互的。
 * 相互认识的人会形成一个朋友圈，这个认识关系是有传递性的，即[0][1]=1,[1][2]=1，那么{0,1,2}就可以组成一个朋友圈。
 * 统计有多少个不同的朋友圈。
 * @since JDK1.8
 */
public class Code20_朋友圈 {

    /**
     * 并查集结构
     * 首先有一个parents数组，里面记录的就是当前位置对应的代表节点，
     * 例如parents[0]=1，代表0所在的集合的代表节点为1。
     * 第二个，有一个size数组，里面记录的就是此代表节点所在集合的数量大小，
     * 例如size[1]=2，代表1节点所在集合的大小为2，非代表节点的size为0
     * 还有一个辅助数组help，就是在查找最高祖先的时候，代替stack来进行操作,
     * 最后一个就是sets，记录此时并查集中有多少个不同的集合
     */
    public static class UnionFind {
        private final int[] parents;
        private final int[] size;
        private final int[] help;
        private int sets;

        // n 为总人数
        public UnionFind(int n) {
            parents = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 查找最高祖先
         */
        private int findAncestors(int n) {
            // 辅助数组的索引
            int hi = 0;
            // 循环找到祖先
            while (n != parents[n]) {
                // 记录路径
                help[hi++] = n;
                n = parents[n];
            }
            // 路径压缩
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = n;
            }
            return n;
        }

        /**
         * 合并
         */
        public void union(int a, int b) {
            // 找最高祖先
            int f1 = findAncestors(a);
            int f2 = findAncestors(b);
            // 合并
            if (f1 != f2) {
                // 集合size比较
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parents[f2] = f1;
                    size[f2] = 0;
                } else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                    size[f1] = 0;
                }
                // 并查集不同集合数量减少
                sets--;
            }
        }

        public int getSets() {
            return sets;
        }
    }

    public static int findCircleNum(int[][] m) {
        int n = m.length;
        // 并查集
        UnionFind unionFind = new UnionFind(n);
        // 二位数组只需要判断右上，因为是对称关系
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // i和j互相认识
                if (m[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSets();
    }
}
