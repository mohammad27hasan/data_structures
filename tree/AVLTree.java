/*
* Data structure: Tree
* Abstract data type: AVL tree
* Version: 1.0.1
* Author: Mohammad Hasan
*/
package ds.tree;

public class AVLTree<T extends Comparable<T>> {
    private class Node {
        int balance;
        T data;
        Node left;
        Node right;
        Node parent;

        Node(T value) {
            balance = 0;
            data = value;
            left = null;
            right = null;
            parent = null;
        }
    }

    private int size;
    private Node root;

    public AVLTree() {
        size = 0;
        root = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    private Node search(T key) {
        Node node = root;
        while ((node != null) && !key.equals(node.data)) {
            node = ((key.compareTo(node.data) < 0) ? node.left : node.right);
        }
        return node;
    }

    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        return (search(element) != null);
    }

    private int inorder(Node node, StringBuilder sb, int commaSize) {
        if (node != null) {
            commaSize = inorder(node.left, sb, commaSize);
            sb.append(node.data);
            if (commaSize > 0) {
                sb.append(", ");
                commaSize--;
            }
            commaSize = inorder(node.right, sb, commaSize);
        }
        return commaSize;
    }

    public String inorder() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        inorder(root, sb, (size - 1));
        sb.append("]");
        return sb.toString();
    }

    private int preorder(Node node, StringBuilder sb, int commaSize) {
        if (node != null) {
            sb.append(node.data);
            if (commaSize > 0) {
                sb.append(", ");
                commaSize--;
            }
            commaSize = preorder(node.left, sb, commaSize);
            commaSize = preorder(node.right, sb, commaSize);
        }
        return commaSize;
    }

    public String preorder() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        preorder(root, sb, (size - 1));
        sb.append("]");
        return sb.toString();
    }

    private int postorder(Node node, StringBuilder sb, int commaSize) {
        if (node != null) {
            commaSize = postorder(node.left, sb, commaSize);
            commaSize = postorder(node.right, sb, commaSize);
            sb.append(node.data);
            if (commaSize > 0) {
                sb.append(", ");
                commaSize--;
            }
        }
        return commaSize;
    }

    public String postorder() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        postorder(root, sb, (size - 1));
        sb.append("]");
        return sb.toString();
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T minElement() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return minimum(root).data;
    }

    private Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public T maxElement() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        return maximum(root).data;
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return ((leftHeight > rightHeight) ? (leftHeight + 1) : (rightHeight + 1));
    }

    private int balance(Node node) {
        return (height(node.right) - height(node.left));
    }

    private void leftRotate(Node node) {
        Node descendant = node.right;
        node.right = descendant.left;
        if (descendant.left != null) {
            descendant.left.parent = node;
        }
        descendant.parent = node.parent;
        if (node.parent == null) {
            root = descendant;
        } else if (node == node.parent.left) {
            node.parent.left = descendant;
        } else {
            node.parent.right = descendant;
        }
        descendant.left = node;
        node.parent = descendant;
    }

    private void rightRotate(Node node) {
        Node descendant = node.left;
        node.left = descendant.right;
        if (descendant.right != null) {
            descendant.right.parent = node;
        }
        descendant.parent = node.parent;
        if (node.parent == null) {
            root = descendant;
        } else if (node == node.parent.left) {
            node.parent.left = descendant;
        } else {
            node.parent.right = descendant;
        }
        descendant.right = node;
        node.parent = descendant;
    }

    private void fixup(Node node) {
        while (node != null) {
            node.balance = balance(node);
            switch (node.balance) {
                case -2:
                    if (node.left.balance == 1) {
                        leftRotate(node.left);
                    }
                    rightRotate(node);
                    break;
                case 2:
                    if (node.right.balance == -1) {
                        rightRotate(node.right);
                    }
                    leftRotate(node);
                    break;
                default:
                    break;
            }
            node = node.parent;
        }
    }

    private void insert(Node newNode) {
        Node ancestor = null;
        Node node = root;
        while (node != null) {
            ancestor = node;
            node = ((newNode.data.compareTo(node.data) < 0) ? node.left : node.right);
        }
        newNode.parent = ancestor;
        if (ancestor == null) {
            root = newNode;
        } else if (newNode.data.compareTo(ancestor.data) < 0) {
            ancestor.left = newNode;
        } else {
            ancestor.right = newNode;
        }
        fixup(newNode);
    }

    public void insert(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        insert(new Node(element));
        size++;
    }

    private void transplant(Node first, Node second) {
        if (first.parent == null) {
            root = second;
        } else if (first == first.parent.left) {
            first.parent.left = second;
        } else {
            first.parent.right = second;
        }
        if (second != null) {
            second.parent = first.parent;
        }
    }

    private void remove(Node node) {
        Node ancestor = node.parent;
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node successor = minimum(node.right);
            ancestor = successor;
            if (successor.parent != node) {
                ancestor = successor.parent;
                transplant(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }
            transplant(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }
        fixup(ancestor);
    }

    public void remove(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
        }
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Size: " + size);
        }
        Node node = search(element);
        if (node == null) {
            throw new IllegalStateException("No Element: " + element);
        }
        remove(node);
        size--;
    }

    public void clear() {
        while (!isEmpty()) {
            remove(root);
            size--;
        }
    }

    public String toString() {
        return inorder();
    }
}