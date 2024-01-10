import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    
    void insert(final T value) {
        if (root == null) {
            root = new Node<T>(value);
        } else {
            root.insert(value);
        }
    }

    List<T> getAsSortedList() {
        return root.getAsSortedList();
    }

    List<T> getAsLevelOrderList() {
        final List<T> result = new ArrayList<>();
        Deque<Node<T>> queue = new ArrayDeque<>();
        queue.addLast(root);
        
        bfsTraversal(result, queue);

        return result;
    }

    private void bfsTraversal(final List<T> result, final Deque<Node<T>> queue) {
        while (queue.size() > 0) {
            final Node<T> node = queue.removeFirst();
            result.add(node.getData());
            if (node.getLeft() != null) queue.addLast(node.getLeft());
            if (node.getRight() != null) queue.addLast(node.getRight());
        }
    }
    
    Node<T> getRoot() {
        return root;
    }

    static class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        Node(final T data) {
            this.data = data;
        }

        Node<T> getLeft() {
            return this.left;
        }

        Node<T> getRight() {
            return this.right;
        }

        T getData() {
            return this.data;
        }

        void insert(final T value) {
            if (value.compareTo(data) <= 0) {
                if (left == null) {
                    left = new Node(value);    
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new Node(value);    
                } else {
                    right.insert(value);
                }
            }
        }

        List<T> getAsSortedList() {
            final List<T> result = new ArrayList<>();
            if (left != null) {
                result.addAll(left.getAsSortedList());
            }
            result.add(data);
            if (right != null) {
                result.addAll(right.getAsSortedList());
            }
            return result;
        }
    }
}
