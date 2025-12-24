/*
* Data structure: Tree
* Abstract data type: BinarySearchTree
* Version: 1.0.2
* Author: Mohammad Hasan
*/
package ds.tree;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        Node parent;
        Node left;
        T data;
        Node right;

        Node(T value) {
            parent = null;
            left = null;
            data = value;
            right = null;
        }
    }

    private int size;
    private Node root;
    
    public BinarySearchTree() {
        size = 0;
        root = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (root == null);
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

    private Node search(T key) {
        Node node = root;
        while ((node != null) && !key.equals(node.data)) {
            if (key.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    public boolean contains(T element) {
        if (element == null) {
            return false;
        }
        return (search(element) != null);
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

    private Node successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }
        Node ancestor = node.parent;
        while ((ancestor != null) && (node == ancestor.right)) {
            node = ancestor;
            ancestor = ancestor.parent;
        }
        return ancestor;
    }

    public T successor(T element) {
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
        node = successor(node);
        if (node == null) {
            throw new IllegalStateException("No Successor");
        }
        return node.data;
    }

    private Node predecessor(Node node) {
        if (node.left != null) {
            return maximum(node.left);
        }
        Node ancestor = node.parent;
        while ((ancestor != null) && (node == ancestor.left)) {
            node = ancestor;
            ancestor = ancestor.parent;
        }
        return ancestor;
    }

    public T predecessor(T element) {
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
        node = predecessor(node);
        if (node == null) {
            throw new IllegalStateException("No Predecessor");
        }
        return node.data;
    }

    private void insert(Node newNode) {
        Node ancestor = null;
        Node node = root;
        while (node != null) {
            ancestor = node;
            if (newNode.data.compareTo(node.data) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        newNode.parent = ancestor;
        if (ancestor == null) {
            root = newNode;    /* Tree was empty */
        } else if (newNode.data.compareTo(ancestor.data) < 0) {
            ancestor.left = newNode;
        } else {
            ancestor.right = newNode;
        }
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
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node successor = minimum(node.right);
            if (successor.parent != node) {
                transplant(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }
            transplant(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }
    }

    public void remove(T element) {
        if (element == null) {
            throw new NullPointerException("Element: " + element);
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