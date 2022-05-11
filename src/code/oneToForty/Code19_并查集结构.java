package code.oneToForty;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 并查集主要完成的功能就是：
 * isSameSet()：判断两个元素是否在同一个集合当中；
 * union()：将两个集合合并到一起。
 * @since JDK1.8
 */
public class Code19_并查集结构 {

    /**
     * 包装层
     */
    private static class Node<V> {
        V value;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * 并查集结构
     */
    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
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

        /**
         * 找到node的最高祖先
         */
        public Node<V> findAncestors(Node<V> cur) {
            // 栈，用以存放沿途找到的各个节点
            Stack<Node<V>> path = new Stack<>();
            // 查找最高祖先
            while (cur != parents.get(cur)) {
                // 路径节点添加
                path.push(cur);
                cur = parents.get(cur);
            }
            // 将路径上的左右节点直接指向最高祖先，优化查找
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        /**
         * 判断元素是否在同一个集合中
         */
        public boolean isSameSet(V a, V b) {
            return findAncestors(nodes.get(a)) == findAncestors(nodes.get(b));
        }

        /**
         * 将元素合并到一个集合中
         */
        public void union(V a, V b) {
            // 找到各自的最高祖先
            Node<V> aHead = findAncestors(nodes.get(a));
            Node<V> bHead = findAncestors(nodes.get(b));
            // 假如祖先不一样
            if (aHead != bHead) {
                // 小的集合指向大的集合
                // 获取集合大小
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                // 找到大小节点
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                // parents添加指向
                parents.put(small, big);
                // 集合map大小改变
                sizeMap.put(big, aSetSize + bSetSize);
                // 移除不是代表node的大小
                sizeMap.remove(small);
            }
        }
    }
}
