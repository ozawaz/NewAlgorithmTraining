package utils;

/**
 * @author distantstar
 * @version 1.0
 * @date 2021
 * @description basic.sort.utils
 * @since JDK1.8
 */
public class NodeUtil {

    /**
     * 单向链表
     */
    public static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T v) {
            value = v;
        }
    }

    /**
     * 双向链表
     */
    public static class DoubleNode<T> {
        public T value;
        public DoubleNode<T> last;
        public DoubleNode<T> next;

        public DoubleNode(T v) {
            value = v;
        }
    }

    /**
     * 前缀树
     */
    public static class TrieNode {
        /**
         * 节点经过次数
         */
        public int pass;
        /**
         * 作为结尾的次数
         */
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new TrieNode[26];
        }
    }
}
