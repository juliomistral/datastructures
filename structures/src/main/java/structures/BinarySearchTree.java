package structures;


import structures.basic.BTreeNode;

import java.util.function.Consumer;


public class BinarySearchTree<D extends Comparable<D>> {
    public enum TraversalStrategy {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER,
    }

    private BTreeNode<D> root;


    /**
     * Find the node matching the supplied data by VALUE.
     *
     * Time:  O(log(n))  Find based on height, which grows at log^2(n)
     *
     * @param data
     * @return
     */
    public BTreeNode<D> find(D data) {
        return findInternal(this.root, data);
    }

    public BTreeNode<D> findInternal(BTreeNode<D> current, D data) {
        if (current == null) return null;
        if (data.equals(current.getData())) return current;

        if (current.isMoreThan(data)) {
            return findInternal(current.getLeft(), data);
        } else {
            return findInternal(current.getRight(), data);
        }
    }

    /**
     * Inserts the provided data into the BST, returning the created node.
     *
     * Time:  O(log(n))
     *
     * @param data
     * @return
     */
    public BTreeNode<D> insert(D data) {
        // Empty tree, create and return root node
        if (empty()) {
            this.root = new BTreeNode<D>(data);
            return this.root;
        }
        return insertInternal(this.root, data);
    }

    private BTreeNode<D> insertInternal(BTreeNode<D> current, D data) {
        BTreeNode<D> newNode;

        if (current.isLessThanOrEqual(data)) { // insert right
            if (!current.rightExists()) {
                newNode = new BTreeNode<D>(data);
                current.setRight(newNode);
            } else {
                newNode = insertInternal(current.getRight(), data);
            }
        } else {
            if (!current.leftExists()) {
                newNode = new BTreeNode<D>(data);
                current.setLeft(newNode);
            } else {
                newNode = insertInternal(current.getLeft(), data);
            }
        }

        return newNode;
    }

    /**
     * Removes the node matching the provided data by VALUE from the BST.
     *
     * @param data
     * @return
     */
    public BTreeNode<D> remove(D data) {
        return null;
    }

    /**
     * Traverses the tree using the provided strategy, executing the callback on each node visit.
     *
     * @param strategy
     * @param visitCallback
     */
    public void traverse(TraversalStrategy strategy, Consumer<BTreeNode<D>> visitCallback) {
        traverseInternal(this.root, strategy, visitCallback);
    }

    private void traverseInternal(BTreeNode<D> current,
                                  TraversalStrategy strategy,
                                  Consumer<BTreeNode<D>> visitCallback) {
        if (current == null) return;

        switch(strategy) {
            case IN_ORDER:
                traverseInternal(current.getLeft(), strategy, visitCallback);
                visitCallback.accept(current);
                traverseInternal(current.getRight(), strategy, visitCallback);
                break;
            case PRE_ORDER:
                visitCallback.accept(current);
                traverseInternal(current.getLeft(), strategy, visitCallback);
                traverseInternal(current.getRight(), strategy, visitCallback);
            case POST_ORDER:
                traverseInternal(current.getLeft(), strategy, visitCallback);
                traverseInternal(current.getRight(), strategy, visitCallback);
                visitCallback.accept(current);
        }
    }

    public boolean empty() {
        return root == null;
    }

}
