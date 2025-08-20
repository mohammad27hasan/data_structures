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
    private int count;
    BinarySearchTree() {
        root = null;
        size = 0;
        count = 0;
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
        } else {
            return (search(root, key) != null);
        }
    }

    private Node successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        } else {
            Node successor = node.parent;
            while ((successor != null) && (successor.right == node)) {
                node = successor;
                successor = successor.parent;
            }
            return successor;
        }
    }

    T successor(T element) {
        if ((root == null) || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        } else {
            Node findedNode = search(root, element);
            if (findedNode == null) {
                throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
            } else {
                Node successor = successor(findedNode);
                if (successor == null) {
                    throw new java.util.NoSuchElementException("There is no successor of " + element);
                } else {
                    return successor.data;
                }
            }
        }
    }

    private Node predecessor(Node node) {
        if (node.left != null) {
            return maximum(node.left);
        } else {
            Node predecessor = node.parent;
            while ((predecessor != null) && (predecessor.left == node)) {
                node = predecessor;
                predecessor = predecessor.parent;
            }
            return predecessor;
        }
    }

    T predecessor(T element) {
        if ((root == null) || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        } else {
            Node findedNode = search(root, element);
            if (findedNode == null) {
                throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
            } else {
                Node predecessor = predecessor(findedNode);
                if (predecessor == null) {
                    throw new java.util.NoSuchElementException("There is no predecessor of " + element);
                } else {
                    return predecessor.data;
                }
            }
        }
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
        } else {
            return maximum(root).data;
        }
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
        } else {
            return minimum(root).data;
        }
    }

    void insert(T item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null");
        } else {
            Node newNode = new Node(item);
            if (root == null) {
                root = newNode;
                size = size + 1;
            } else {
                Node iterator = root;
                while (iterator != null) {
                    if (item.equals(iterator.data)) {
                        return;
                    } else if (item.compareTo(iterator.data) < 0) {
                        if (iterator.left == null) {
                            iterator.left = newNode;
                            newNode.parent = iterator;
                            size = size + 1;
                        }
                        iterator = iterator.left;
                    } else {
                        if (iterator.right == null) {
                            iterator.right = newNode;
                            newNode.parent = iterator;
                            size = size + 1;
                        }
                        iterator = iterator.right;
                    }
                }
            }
        }
    }

    private Node delete(Node root, T element) {
        if (root == null) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        } else {
            int comparedValue = element.compareTo(root.data);
            if (comparedValue < 0) {
                root.left = delete(root.left, element);
            } else if (comparedValue > 0) {
                root.right = delete(root.right, element);
            } else { // element found
                Node temp = null;
                if ((root.left != null) && (root.right != null)) { // two children
                    temp = maximum(root.left);
                    root.data = temp.data;
                    root.left = delete(root.left, temp.data);
                } else { // zero or one child
                    temp = root;
                    if (root.left == null) {
                        root = root.right;
                    } else {
                        root = root.left;
                    }
                    temp.left = null;
                    temp.data = null;
                    temp.right = null;
                }
            }
        }
        return root;
    }

    void remove(T element) {
        if ((root == null) || (element == null)) {
            throw new java.util.NoSuchElementException(element + " is not present in BinarySearchTree");
        } else {
            root = delete(root, element);
            size = size - 1;
        }
    }

    private StringBuilder preorder(Node root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.data);
            if (count < (size - 1)) {
                sb.append(", ");
            }
            count = count + 1;
            preorder(root.left, sb);
            preorder(root.right, sb);
        }
        return sb;
    }

    String preorder() {
        StringBuilder sb = preorder(root, new StringBuilder("["));
        sb.append("]");
        count = 0;
        return sb.toString();
    }

    private StringBuilder inorder(Node root, StringBuilder sb) {
        if (root != null) {
            inorder(root.left, sb);
            sb.append(root.data);
            if (count < (size - 1)) {
                sb.append(", ");
            }
            count = count + 1;
            inorder(root.right, sb);
        }
        return sb;
    }

    String inorder() {
        StringBuilder sb = inorder(root, new StringBuilder("["));
        sb.append("]");
        count = 0;
        return sb.toString();
    }

    private StringBuilder postorder(Node root, StringBuilder sb) {
        if (root != null) {
            postorder(root.left, sb);
            postorder(root.right, sb);
            sb.append(root.data);
            if (count < (size - 1)) {
                sb.append(", ");
            }
            count = count + 1;
        }
        return sb;
    }

    String postorder() {
        StringBuilder sb = postorder(root, new StringBuilder("["));
        sb.append("]");
        count = 0;
        return sb.toString();
    }

    public String toString() {
        return inorder();
    }
}