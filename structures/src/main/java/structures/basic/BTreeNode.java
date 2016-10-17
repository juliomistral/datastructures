package structures.basic;


public class BTreeNode<D extends Comparable<D>> {
    private BTreeNode parent;
    private BTreeNode left, right;
    private D data;


    public BTreeNode(D data) {
        this(data, null);
    }

    private BTreeNode(D data, BTreeNode parent) {
        this.parent = parent;
        this.data = data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public BTreeNode<D> setLeft(BTreeNode<D> left) {
        BTreeNode<D> orig = this.left;

        this.left = left;
        if (this.left != null) {
            this.left.parent = this;
        }

        return orig;
    }

    public BTreeNode<D> setRight(BTreeNode<D> right) {
        BTreeNode<D> orig = this.right;

        this.right = right;
        if (this.right != null) {
            this.right.parent = this;
        }

        return orig;
    }

    public BTreeNode<D> getParent() {
        return parent;
    }

    public BTreeNode<D> getLeft() {
        return left;
    }

    public BTreeNode<D> getRight() {
        return right;
    }

    public D getData() {
        return data;
    }

    public boolean isLeaf() {
        return (this.right != null && this.left != null);
    }

    public boolean isLessThanOrEqual(D data) {
        return this.getData().compareTo(data) <= 0;
    }

    public boolean isMoreThan(D data) {
        return this.getData().compareTo(data) > 0;
    }

    public boolean leftExists() {
        return getLeft() != null;
    }

    public boolean rightExists() {
        return getRight() != null;
    }
}
