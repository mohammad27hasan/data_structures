class AVLTree<T extends Comparable<T>> {
    private class Node {
        Node parent;
        Node left;
        T data;
        Node right;
        int bf; /* balance factor */
        Node(T val) {
            parent = null;
            left = null;
            data = val;
            right = null;
            bf = 0;
        }
    }
    private Node root;
    private int size;
    AVLTree() {
        root = null;
        size = 0;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return (root == null);
    }    

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return (Math.max(height(node.right), height(node.left)) + 1);
    }

    private int balanceFactor(Node node) {
        return (height(node.right) - height(node.left));
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

    private Node maximum(Node maxNode) {
        while (maxNode.right != null) {
            maxNode = maxNode.right;
        }
        return maxNode;
    }

    private Node minimum(Node minNode) {
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        return minNode;
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
        if (isEmpty() || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in AVLTree");
        }
        Node finder = search(root, element);
        if (finder == null) {
            throw new java.util.NoSuchElementException(element + " is not present in AVLTree");
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
        if (isEmpty() || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in AVLTree");
        }
        Node finder = search(root, element);
        if (finder == null) {
            throw new java.util.NoSuchElementException(element + " is not present in AVLTree");  
        }
        Node predecessor = predecessor(finder);
        if (predecessor == null) {
            throw new java.util.NoSuchElementException("There is no predecessor of " + element);  
        }
        return predecessor.data;
    }

    T maxElement() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("AVLTree is empty");
        }
        return maximum(root).data;
    }

    T minElement() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("AVLTree is empty");
        }
        return minimum(root).data;
    }
    
    private void updateBalanceFactor(Node node) {
        node.bf = balanceFactor(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateBalanceFactor(node);
        updateBalanceFactor(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateBalanceFactor(node);
        updateBalanceFactor(newParent);
        return newParent;
    }

    private Node leftRightRotation(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftRotation(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node rebalance(Node node) {
        if (node.bf == -2) { /* left-heavy */
            if (node.left.bf <= 0) {
                return rightRotation(node); /* left-left case */
            }
            return leftRightRotation(node); /* node.left.bf > 0 */
        }
        if (node.bf == +2) { /* right-heavy */
            if (node.right.bf >= 0) {
                return leftRotation(node); /* right-right case */
            }
            return rightLeftRotation(node); /* node.right.bf < 0 */
        }
        return node; /* balanced */
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            return new Node(value);
        }
        int comparator = value.compareTo(node.data);
        if (comparator < 0) {
            node.left = insert(node.left, value);
            node.left.parent = node;
        } else {
            node.right = insert(node.right, value);
            node.right.parent = node;
        }
        updateBalanceFactor(node);
        return rebalance(node);
    }

    void insert(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        }
        if (!contains(item)) {
            root = insert(root, item);
            size = size + 1;
        }
    }

    private void destroy(Node node) {
        node.parent = null;
        node.left = null;
        node.data = null;
        node.right = null;
        node.bf = 0;
        node = null;
    }

    private Node delete(Node node, T element) {
        if (node == null) {
            throw new java.util.NoSuchElementException(element + " is not present in AVLTree");
        }
        int comparator = element.compareTo(node.data);
        if (comparator < 0) {
            node.left = delete(node.left, element);
        } else if (comparator > 0) {
            node.right = delete(node.right, element);
        } else { /* element found */
            if ((node.left != null) && (node.right != null)) { /* two children */
                Node successor = minimum(node.right);
                node.data = successor.data;
                node.right = delete(node.right, successor.data);
            } else { /* zero or one child */
                Node temp = node;
                if (node.left == null) {
                    node = node.right;
                } else {
                    node = node.left;
                }
                destroy(temp);
                return node;
            }
        }
        updateBalanceFactor(node);
        return rebalance(node);
    }

    void remove(T element) {
        if (isEmpty() || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in AVLTree");
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