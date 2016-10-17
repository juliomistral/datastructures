package structures;


import static org.assertj.core.api.Assertions.*;

import jdk.nashorn.internal.ir.annotations.Ignore;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import structures.basic.BTreeNode;

import java.util.ArrayList;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;


    @Nested
    public class WhenInsertingElements {
        @Test
        public void noInsertsMeansTheBSTIsEmtpy() {
            bst = new BinarySearchTree<>();

            assertThat(bst.empty()).isTrue();
        }

        @Test
        public void insertingAnElementMeansBstIsNotEmpty(){
            bst = new BinarySearchTree<>();
            bst.insert(10);

            assertThat(bst.empty()).isFalse();
        }
    }

    @Nested
    public class WhenFindingElements {
        @Test
        public void anEmptyBSTReturnsNull() {
            bst = new BinarySearchTree<>();
            assertThat(bst.find(10)).isNull();
        }

        @Test
        public void canFindTheOnlyElementAddedToTheBST() {
            bst = new BinarySearchTree<>();
            BTreeNode<Integer> root = bst.insert(10);

            assertThat(bst.find(10)).isEqualTo(root);
        }

        @Test
        public void canFindALeafElementInTreeWithHeightOne() {
            bst = simpleTree();

            BTreeNode<Integer> result = bst.find(5);
            assertThat(result).isNotNull();
            assertThat(result.getData()).isEqualTo(5);

            result = bst.find(15);
            assertThat(result).isNotNull();
            assertThat(result.getData()).isEqualTo(15);
        }

        @Test
        public void canFindALeafElementInTreeWithHeightTwo() {
            bst = complexTree();

            BTreeNode<Integer> result = bst.find(8);
            assertThat(result).isNotNull();
            assertThat(result.getData()).isEqualTo(8);

            result = bst.find(10);
            assertThat(result).isNotNull();
            assertThat(result.getData()).isEqualTo(10);
        }

        @Test
        public void returnsNullIfProbidedDataCantBeFound() {
            bst = complexTree();

            assertThat(bst.find(10000)).isNull();
        }
    }

    @Nested
    public class WhenTraversing {
        @Test
        public void traversingInOrderVisitsNodesByAscendingNodeValue() {
            bst = complexTree();
            ArrayList results = new ArrayList();

            bst.traverse(BinarySearchTree.TraversalStrategy.IN_ORDER, node -> {
                results.add(node.getData());
            });

            assertThat(results).containsExactly(0, 5, 8, 10, 12, 15, 18);
        }

//        @Test
//        @Ignore
//        public void traversingPreOrderVisitsNodesByCurrentNodeFirst() {
//            bst = complexTree();
//            ArrayList results = new ArrayList();
//
//            bst.traverse(BinarySearchTree.TraversalStrategy.PRE_ORDER, node -> {
//                results.add(node.getData());
//            });
//
//            assertThat(results).containsExactly(0, 5, 8, 10, 12, 15, 18);
//        }
    }

    /**
     * Simple tree:
     *          10
     *         /  \
     *        5   15
     *
     * @return
     */
    public BinarySearchTree<Integer> simpleTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(10);  // root;
        tree.insert(5);   // left;
        tree.insert(15);  // right;

        return tree;
    }

    /**
     * Complex Tree:
     *
     *           10
     *         /   \
     *        5    15
     *      /  \  /  \
     *     0   8 12  18
     */
    public BinarySearchTree<Integer> complexTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(10);  // root;
        tree.insert(5);   // left;
        tree.insert(15);  // right;

        tree.insert(0);
        tree.insert(8);
        tree.insert(12);
        tree.insert(18);

        return tree;
    }
}