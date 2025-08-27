class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        Node parent;
        Node left;
        T data;
        Node right;
        Node(T val) {
            parent = null;
            left = null;
            data = val;
            right = null;
        }
    }
    private Node root;
    private int size;
    BinarySearchTree() {
        root = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return (root == null);
    }

    private Node search(Node iterator, T key) {
        while ((iterator != null) && (!key.equals(iterator.data))) {
            if (key.compareTo(iterator.data) < 0) {
                iterator = iterator.left;
            } else {
                iterator = iterator.right;
            }
        }
        return iterator;
    }

    boolean contains(T key) {
        if (key == null) {
            return false;
        }
        return (search(root, key) != null);
    }

    private Node successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }
        Node successor = node.parent;
        while ((successor != null) && (successor.right == node)) {
            node = successor;
            successor = successor.parent;
        }
        return successor;
    }

    T successor(T element) {
        if ((root == null) || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        }
        Node finder = search(root, element);
        if (finder == null) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        }
        Node successor = successor(finder);
        if (successor == null) {
            throw new java.util.NoSuchElementException("There is no successor of " + element);
        }
        return successor.data;
    }

    private Node predecessor(Node node) {
        if (node.left != null) {
            return maximum(node.left);
        }
        Node predecessor = node.parent;
        while ((predecessor != null) && (predecessor.left == node)) {
            node = predecessor;
            predecessor = predecessor.parent;
        }
        return predecessor;
    }

    T predecessor(T element) {
        if ((root == null) || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        }
        Node finder = search(root, element);
        if (finder == null) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        }
        Node predecessor = predecessor(finder);
        if (predecessor == null) {
            throw new java.util.NoSuchElementException("There is no predecessor of " + element);
        }
        return predecessor.data;
    }

    private Node maximum(Node maxNode) {
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode;
    }

    T maxElement() {
        if (root == null) {
            throw new java.util.NoSuchElementException("BinarySearchTree is empty");
        }
        return maximum(root).data;
    }

    private Node minimum(Node minNode) {
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
    }

    T minElement() {
        if (root == null) {
            throw new java.util.NoSuchElementException("BinarySearchTree is empty");
        }
        return minimum(root).data;
    }

    void insert(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        }
        if (root == null) {
            Node newNode = new Node(item);
            root = newNode;
            size = size + 1;
            return;
        }
        Node iterator = root;
        int comparator = 0;
        while (iterator != null) {
            comparator = item.compareTo(iterator.data);
            if (comparator < 0) {
                if (iterator.left == null) {
                    Node newNode = new Node(item);
                    iterator.left = newNode;
                    newNode.parent = iterator;
                    size = size + 1;
                    return;
                }
                iterator = iterator.left;
            } else if (comparator > 0) {
                if (iterator.right == null) {
                    Node newNode = new Node(item);
                    iterator.right = newNode;
                    newNode.parent = iterator;
                    size = size + 1;
                    return;
                }
                iterator = iterator.right;
            }
        }
    }

    private void destroy(Node node) {
        node.parent = null;
        node.left = null;
        node.data = null;
        node.right = null;
        node = null;
    }

    private Node delete(Node node, T element) {
        if (node == null) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        }
        int comparator = element.compareTo(node.data);
        if (comparator < 0) {
            node.left = delete(node.left, element);
        } else if (comparator > 0) {
            node.right = delete(node.right, element);
        } else { // element found
            if ((node.left != null) && (node.right != null)) { // two children
                Node predecessor = maximum(node.left);
                node.data = predecessor.data;
                node.left = delete(node.left, predecessor.data);
            } else { // zero or one child
                Node temp = node;
                if (node.left == null) {
                    node = node.right;
                } else {
                    node = node.left;
                }
                destroy(temp);
            }
        }
        return node;
    }

    void remove(T element) {
        if ((root == null) || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        }
        root = delete(root, element);
        size = size - 1;
    }

    private void modifyStringBuilder(StringBuilder sb) {
        int start = sb.lastIndexOf(", ");
        if (start != -1) {
            int end = start + 2;
            sb.replace(start, end, "]");
        } else {
            sb.append("]");
        }
    }

    private StringBuilder preorder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data + ", ");
            preorder(node.left, sb);
            preorder(node.right, sb);
        }
        return sb;
    }

    String preorder() {
        StringBuilder sb = preorder(root, new StringBuilder("["));
        modifyStringBuilder(sb);
        return sb.toString();
    }

    private StringBuilder inorder(Node node, StringBuilder sb) {
        if (node != null) {
            inorder(node.left, sb);
            sb.append(node.data + ", ");
            inorder(node.right, sb);
        }
        return sb;
    }

    String inorder() {
        StringBuilder sb = inorder(root, new StringBuilder("["));
        modifyStringBuilder(sb);
        return sb.toString();
    }

    private StringBuilder postorder(Node node, StringBuilder sb) {
        if (node != null) {
            postorder(node.left, sb);
            postorder(node.right, sb);
            sb.append(node.data + ", ");
        }
        return sb;
    }

    String postorder() {
        StringBuilder sb = postorder(root, new StringBuilder("["));
        modifyStringBuilder(sb);
        return sb.toString();
    }

    public String toString() {
        return inorder();
    }
}