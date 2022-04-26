package code;

import code.graph.Edge;
import code.graph.Graph;
import code.graph.Node;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 将所有的边按照权重一次排列，然后每次从最短的边开始，看是否形成了环路，没有形成，就添加该边的权重。
 * @since JDK1.8
 */
public class Code26_最小生成树_Kruskal {

    /**
     * 边的权重排列就可以使用一个优先队列，然后每次判断的时候，就将边从里面获取。
     * 判断是否i形成了环路，可以使用一个并查集，将每个已经判断的节点添加到集合中，
     * 假如已经判断过该节点（即在集合中），那么就直接跳过，如果不在，则进行union操作。
     */
    private static class UnionFind {
        // key 某一个节点， value key节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // key 某一个集合的代表节点, value key所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findAncestors(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != fatherMap.get(node)) {
                stack.push(node);
                node = fatherMap.get(node);
            }
            while(!stack.isEmpty()) {
                fatherMap.put(stack.pop(), node);
            }
            return node;
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }

            Node node1 = findAncestors(a);
            Node node2 = findAncestors(b);
            if (node1 != node2) {
                int aSetSize = sizeMap.get(node1);
                int bSetSize = sizeMap.get(node2);
                if (aSetSize <= bSetSize) {
                    fatherMap.put(node1, node2);
                    sizeMap.put(node2, aSetSize + bSetSize);
                    sizeMap.remove(node1);
                } else {
                    fatherMap.put(node2, node1);
                    sizeMap.put(node1, aSetSize + bSetSize);
                    sizeMap.remove(node2);
                }
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findAncestors(a) == findAncestors(b);
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 从小的边到大的边，依次弹出，小根堆！
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
