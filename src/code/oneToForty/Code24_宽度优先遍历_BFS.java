package code.oneToForty;

import code.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code24_宽度优先遍历_BFS {
    public void bfs(Node node) {
        // 遍历队列
        Queue<Node> queue = new LinkedList<>();
        // 去重节点set
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
