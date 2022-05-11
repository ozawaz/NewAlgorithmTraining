package code.oneToForty;

import code.graph.Edge;
import code.graph.Graph;
import code.graph.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description 1. 将连通网中的所有顶点分为两类（假设为 A 类和 B 类）。初始状态下，所有顶点位于 B 类；
 * 2. 选择任意一个顶点，将其从 B 类移动到 A 类；
 * 3. 从 B 类的所有顶点出发，找出一条连接着 A 类中的某个顶点且权值最小的边，将此边连接着的 A 类中的顶点移动到 B 类；
 * 4. 重复执行第 3  步，直至 B 类中的所有顶点全部移动到 A 类，恰好可以找到 N-1 条边。
 * @since JDK1.8
 */
public class Code27_最小生成树_Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> prim(Graph graph) {
        // 最小边
        PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
        // 去重节点
        Set<Node> set = new HashSet<>();
        // 依次挑选的的边在result里
        Set<Edge> result = new HashSet<>();
        // 循环
        for (Node node : graph.nodes.values()) {
            // 假如可以添加该节点，证明无重复
            if (set.add(node)) {
                // 由一个点，解锁所有相连的边
                queue.addAll(node.edges);
                // 循环选择最小的边进行尝试
                while (!queue.isEmpty()) {
                    // 取出此时最小的边
                    Edge edge = queue.poll();
                    // 获取其出点
                    Node toNode = edge.getTo();
                    // 判断
                    if (set.add(toNode)) {
                        result.add(edge);
                        // 添加其边
                        queue.addAll(toNode.edges);
                    }
                }
            }
        }
        return result;
    }
}
