package code.oneToForty;

import utils.NodeUtil;

/**
 * @author : zheng.xiong
 * @version : 1.0
 * @date : Created at 2022/04/01
 * @description : code
 */
public class Code12_前缀树 {

    public static class Tire {
        public NodeUtil.TrieNode root;

        public Tire() {
            this.root = new NodeUtil.TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] chars = word.toCharArray();
            NodeUtil.TrieNode node = root;
            node.pass++;
            for (char ch : chars) {
                // 判断路径
                int path = ch - 'a';
                // 没有对应的路径则创造
                if (node.nexts[path] == null) {
                    node.nexts[path] = new NodeUtil.TrieNode();
                }
                node = node.nexts[path];
                // 节点经过次数++
                node.pass++;
            }
            node.end++;
        }

        /**
         * word加入过几次
         */
        public int search(String word) {
            NodeUtil.TrieNode node = findNode(word);
            return node == null ? 0 : node.end;
        }

        /**
         * word作为前缀过几次
         */
        public int prefix(String word) {
            NodeUtil.TrieNode node = findNode(word);
            return node == null ? 0 : node.pass;
        }

        /**
         * 找到特定的节点位置
         */
        public NodeUtil.TrieNode findNode(String word) {
            if (word == null) {
                return null;
            }

            char[] chars = word.toCharArray();
            NodeUtil.TrieNode node = root;
            int index;
            for (char ch : chars) {
                index = ch - 'a';
                // 没有对应路径
                if (node.nexts[index] == null) {
                    return null;
                }
                node = node.nexts[index];
            }
            return node;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                NodeUtil.TrieNode node = root;
                node.pass--;
                int path;
                for (char ch : chars) {
                    path = ch - 'a';
                    // 判断是否已经没有经过的次数
                    if (--node.nexts[path].pass == 0) {
                        // 没有则释放
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }
    }
}
