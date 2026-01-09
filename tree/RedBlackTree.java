/*
* Data structure: Tree
* Abstract data type: Red-black tree
* Version: 1.0
* Author: Mohammad Hasan
*/
package ds.tree;

public class RedBlackTree<T extends Comparable<T>> {
    private enum Color {
        RED, BLACK
    }

    private class Node {
        Color color;
        T data;
        Node left;
        Node right;
        Node parent;

        Node(Node parent) {
            color = Color.BLACK;
            data = null;
            left = null;
            right = null;
            this.parent = parent;
        }

        Node(T value) {
            color = Color.RED;
            data = value;
            left = new Node(this);
            right = new Node(this);
            parent = null;
        }
    }

    private int size;
    private Node root;
    
    public RedBlackTree() {
        size = 0;
        root = null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return ((root == null) || (root.data == null));
    }

    private int inorder(Node node, StringBuilder sb, int commaSize) {
        if (node.data != null) {
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
        if (node.data != null) {
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
        if (node.data != null) {
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
        if (node != null) {
            while (!key.equals(node.data)) {
                if (node.data == null) {
                    return null;
                }
                node = (key.compareTo(node.data) < 0) ? node.left : node.right;
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
        while (node.left.data != null) {
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
        while (node.right.data != null) {
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
        if (node.right.data != null) {
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
        if (node.left.data != null) {
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

    private void leftRotate(Node node) {
        Node descendant = node.right;       /* set descendant */
        node.right = descendant.left;       /* turn descendant's left subtree into node's right subtree */
        if (descendant.left != null) {
            descendant.left.parent = node;
        }
        descendant.parent = node.parent;    /* link node's parent to descendant */
        if (node.parent == null) {
            root = descendant;
        } else if (node == node.parent.left) {
            node.parent.left = descendant;
        } else {
            node.parent.right = descendant;
        }
        descendant.left = node;             /* put node on descendant's left */
        node.parent = descendant;
    }

    private void rightRotate(Node node) {
        Node descendant = node.left;        /* set descendant */
        node.left = descendant.right;       /* turn descendant's right subtree into node's left subtree */
        if (descendant.right != null) {
            descendant.right.parent = node;
        }
        descendant.parent = node.parent;    /* link node's parent to descendant */
        if (node.parent == null) {
            root = descendant;
        } else if (node == node.parent.left) {
            node.parent.left = descendant;
        } else {
            node.parent.right = descendant;
        }
        descendant.right = node;            /* put node on descendant's right */
        node.parent = descendant;
    }

    private void insertFixup(Node node) {
        Node uncle = null;
        while ((node.parent != null) && (node.parent.color == Color.RED)) {
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;
                if (uncle.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    rightRotate(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.left;
                if (uncle.color == Color.RED) {
                    node.parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = Color.BLACK;
                    node.parent.parent.color = Color.RED;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    private void insert(Node newNode) {
        Node ancestor = null;
        Node node = root;
        if (node != null) {
            while (node.data != null) {
                ancestor = node;
                node = (newNode.data.compareTo(node.data) < 0) ? node.left : node.right;
            }
        }
        newNode.parent = ancestor;
        if (ancestor == null) {
            root = newNode;
        } else if (newNode.data.compareTo(ancestor.data) < 0) {
            ancestor.left = newNode;
        } else {
            ancestor.right = newNode;
        }
        insertFixup(newNode);
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
        second.parent = first.parent;
    }

    private void removeFixup(Node node) {
        Node sibling = null;
        while ((node != root) && (node.color == Color.BLACK)) {
            if (node == node.parent.left) {
                sibling = node.parent.right;
                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    leftRotate(node.parent);
                    sibling = node.parent.right;
                }
                if ((sibling.left.color == Color.BLACK) && (sibling.right.color == Color.BLACK)) {
                    sibling.color = Color.RED;
                    node = node.parent;
                } else {
                    if (sibling.right.color == Color.BLACK) {
                        sibling.left.color = Color.BLACK;
                        sibling.color = Color.RED;
                        rightRotate(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.right.color = Color.BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {
                sibling = node.parent.left;
                if (sibling.color == Color.RED) {
                    sibling.color = Color.BLACK;
                    node.parent.color = Color.RED;
                    rightRotate(node.parent);
                    sibling = node.parent.left;
                }
                if ((sibling.right.color == Color.BLACK) && (sibling.left.color == Color.BLACK)) {
                    sibling.color = Color.RED;
                    node = node.parent;
                } else {
                    if (sibling.left.color == Color.BLACK) {
                        sibling.right.color = Color.BLACK;
                        sibling.color = Color.RED;
                        leftRotate(sibling);
                        sibling = node.parent.left;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = Color.BLACK;
                    sibling.left.color = Color.BLACK;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.color = Color.BLACK;
    }

    private void remove(Node oldNode) {
        Node node = oldNode;
        Color nodeOriginalColor = node.color;
        Node descendant = null;
        if (oldNode.left.data == null) {
            descendant = oldNode.right;
            transplant(oldNode, oldNode.right);
        } else if (oldNode.right.data == null) {
            descendant = oldNode.left;
            transplant(oldNode, oldNode.left);
        } else {
            node = minimum(oldNode.right);
            nodeOriginalColor = node.color;
            descendant = node.right;
            if (node.parent != oldNode) {
                transplant(node, node.right);
                node.right = oldNode.right;
                node.right.parent = node;
            }
            transplant(oldNode, node);
            node.left = oldNode.left;
            node.left.parent = node;
            node.color = oldNode.color;
        }
        if (nodeOriginalColor == Color.BLACK) {
            removeFixup(descendant);
        }
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