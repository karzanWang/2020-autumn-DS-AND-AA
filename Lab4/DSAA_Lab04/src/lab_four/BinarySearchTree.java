package lab_four;

import java.util.*;

class EmptyBSTException extends Exception {
    public EmptyBSTException(String message) {
        super(message);
    }
}

class DuplicateKeyException extends Exception {
    public DuplicateKeyException(String message) {
        super(message);
    }
}

class NoSuchKeyException extends Exception {
    public NoSuchKeyException(String message) {
        super(message);
    }
}

/**
 * You may use {@link java.util.Stack},
 * or use MyStack in lab_two if you like.
 */
public class BinarySearchTree {
    private TreeNode root = null;
    private int size = 0;

    /**
     * default constructor
     */
    public BinarySearchTree() {

    }

    /**
     * todo: init from an array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(int[] array) throws DuplicateKeyException {

    }

    /**
     * todo: init from an Integer Object array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(Integer[] array) throws DuplicateKeyException {

    }

    /**
     * todo: Whether have this key or not
     *
     * @param key k
     * @return yes or no
     */
    public boolean hasKey(int key) {
        return false;
    }

    /**
     * todo: insert a new key into the BST
     *
     * @param x key
     * @throws DuplicateKeyException have no duplicate key
     */
    public void insert(int x) throws DuplicateKeyException {
        
    }

    /**
     * todo: remove an item
     *
     * @param x key
     * @throws EmptyBSTException  remove an item from an empty BST
     * @throws NoSuchKeyException item not found
     */
    public void remove(int x) throws EmptyBSTException, NoSuchKeyException {
        
    }

    /**
     * todo: preorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence of traversal
     */
    public List<Integer> preorder(boolean recur) {
        if (recur) {
            return null;
        } else {
            return null;
        }
    }


    /**
     * todo: Inorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence
     */
    public List<Integer> inorder(boolean recur) {
        if (recur) {
            return null;
        } else {
            return null;
        }
    }

    /**
     * todo: Postorder traversal
     *
     * @param recur recursive or loop
     * @return sequence
     */
    public List<Integer> postorder(boolean recur) {
        if (recur) {
            return null;
        } else {
            return null;
        }
    }

    /**
     * todo: Level order traversal
     * From depth 1, 2, ..., to depth n.
     *
     * @return sequence
     */
    public List<Integer> levelOrder() {
        
        return null;
    }

    /**
     * Max value
     *
     * @return max
     * @throws EmptyBSTException may be empty
     */
    public int max() throws EmptyBSTException {
        TreeNode m = max(this.root);
        return m.val;
    }

    /**
     * Min value
     *
     * @return min
     * @throws EmptyBSTException may be empty
     */
    public int min() throws EmptyBSTException {
        TreeNode m = min(this.root);
        return m.val;
    }

    /**
     * Max node
     *
     * @param node node
     * @return max node
     * @throws EmptyBSTException may be empty
     */
    private TreeNode max(TreeNode node) throws EmptyBSTException {
        if (node == null)
            throw new EmptyBSTException("BST is empty");
        if (node.right == null)
            return node;
        return max(node.right);
    }

    /**
     * Min node
     *
     * @param node node
     * @return min node
     * @throws EmptyBSTException may be empty
     */
    private TreeNode min(TreeNode node) throws EmptyBSTException {
        if (node == null)
            throw new EmptyBSTException("BST is empty");
        if (node.left == null)
            return node;
        return min(node.left);
    }

    /**
     * remove min val and return min value
     *
     * @return min value
     * @throws EmptyBSTException may be empty
     */
    public int removeMin() throws EmptyBSTException {
        int m = this.min();
        this.root = this.removeMin(this.root);
        return m;
    }

    /**
     * remove min node and return root node
     *
     * @param node node
     * @return root
     */
    private TreeNode removeMin(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            TreeNode right = node.right;
            node.right = null;
            this.size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * remove max value and return it
     *
     * @return max value
     * @throws EmptyBSTException may be empty
     */
    public int removeMax() throws EmptyBSTException {
        int m = this.max();
        this.root = this.removeMax(this.root);
        return m;
    }

    /**
     * remove max node and return root node
     *
     * @param node node
     * @return renewed node
     */
    private TreeNode removeMax(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            TreeNode left = node.left;
            node.left = null;
            this.size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * todo: find the kth smallest value
     *
     * @param k kth
     * @return kth smallest
     * @throws EmptyBSTException may be empty
     */
    public int kthSmallest(int k) throws EmptyBSTException {
        return 0;
    }


    /**
     * todo
     *
     * @return depth of BST
     */
    public int depth() {
        return 0;
    }


    /**
     * todo: find the closest value, if there are two or more closest values, pick the smallest one.
     *
     * @param target target value
     * @return closest value
     */
    public int closestSmall(double target) throws EmptyBSTException {
        return 0;
    }

    

    /**
     * the size of BST, i.e., how many items
     *
     * @return size of BST
     */
    public int size() {
        return this.size;
    }

}
