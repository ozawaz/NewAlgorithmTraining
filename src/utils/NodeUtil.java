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
}
