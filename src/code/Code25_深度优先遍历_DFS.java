package code;

import code.graph.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code25_深度优先遍历_DFS {
    public void dfs(Node start) {
        // 栈，用于记录上层节点
        Stack<Node> stack = new Stack<>();
        // 去重set
        Set<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
