package code.oneToForty;

import utils.NodeUtil;

/**
 * @author ozawa
 * @version 1.0
 * @date 2022
 * @description code
 * @since JDK1.8
 */
public class Code05_简单链表问题 {

    /**
     * 翻转单向链表
     */
    public static NodeUtil.Node<Integer> revertNode(NodeUtil.Node<Integer> head) {
        NodeUtil.Node<Integer> cur = null;
        NodeUtil.Node<Integer> next;
        while (head != null) {
            next = head.next;
            head.next = cur;
            cur = head;
            head = next;
        }
        return cur;
    }

    /**
     * 翻转双向链表
     */
    public static NodeUtil.DoubleNode<Integer> revertDoubleNode(NodeUtil.DoubleNode<Integer> head) {
        NodeUtil.DoubleNode<Integer> cur = null;
        NodeUtil.DoubleNode<Integer> next;
        while (head != null) {
            next = head.next;
            head.next = cur;
            head.last = next;
            cur = head;
            head = next;
        }

        return cur;
    }

    /**
     * 删除链表中指定的值
     */
    public static NodeUtil.Node<Integer> deleteData(NodeUtil.Node<Integer> head, int num) {
        while (head != null) {
            // 找到第一个不需要删除的位置
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        NodeUtil.Node<Integer> cur = head;
        NodeUtil.Node<Integer> next = head;
        while (cur != null) {
            if (cur.value == num) {
                next.next = cur.next;
            } else {
                next = cur;
            }
            cur = cur.next;
        }

        return head;
    }
}
