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
     * init from an array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(int[] array) throws DuplicateKeyException {
        if (array != null) for (int x : array) this.insert(x);
    }

    /**
     * init from an Integer Object array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(Integer[] array) throws DuplicateKeyException {
        if (array != null) for (Integer x : array) if (x != null) this.insert(x);
    }

    /**
     * Whether have this key or not
     *
     * @param key k
     * @return yes or no
     */
    public boolean hasKey(int key) {
        return hasKey(this.root, key);
    }

    /**
     * whether have this key or not
     *
     * @param node root node
     * @param x    key
     * @return yes or no
     */
    private boolean hasKey(TreeNode node, int x) {
        if (node == null) return false;
        return (node.val == x) || hasKey(node.left, x) || hasKey(node.right, x);
    }

    /**
     * insert a new key into the BST
     *
     * @param x key
     * @throws DuplicateKeyException have no duplicate key
     */
    public void insert(int x) throws DuplicateKeyException {
        this.root = insert(this.root, x);
    }

    /**
     * helper for inserting a new key
     *
     * @param node root node
     * @param x    key
     * @return renewed root node
     * @throws DuplicateKeyException no duplicate key
     */
    private TreeNode insert(TreeNode node, int x) throws DuplicateKeyException {
        if (node == null) {
            this.size++;
            node = new TreeNode(x);
            return node;
        }
        if (x < node.val) {
            node.left = insert(node.left, x);
        } else if (x > node.val) {
            node.right = insert(node.right, x);
        } else {
            throw new DuplicateKeyException("Duplicate Key");
        }
        return node;
    }

    /**
     * remove an item
     *
     * @param x key
     * @throws EmptyBSTException  remove an item from an empty BST
     * @throws NoSuchKeyException item not found
     */
    public void remove(int x) throws EmptyBSTException, NoSuchKeyException {
        if (this.size == 0) {
            throw new EmptyBSTException("BST is empty");
        }
        this.root = remove(this.root, x);
    }

    /**
     * helper for remove an item
     *
     * @param node root node
     * @param x    key
     * @return renewed root node
     * @throws EmptyBSTException  remove an item from an empty BST
     * @throws NoSuchKeyException item not found
     */
    private TreeNode remove(TreeNode node, int x) throws EmptyBSTException, NoSuchKeyException {
        if (node == null) {
            throw new NoSuchKeyException(String.format("x: %d not found", x));
        }
        if (x < node.val) {
            node.left = this.remove(node.left, x);
            return node;
        } else if (x > node.val) {
            node.right = this.remove(node.right, x);
            return node;
        } else {
            if (node.left == null) {
                return this.removeMin(node);
            } else if (node.right == null) {
                return this.removeMax(node);
            } else {
                TreeNode successor = this.min(node.right);
                successor.right = this.removeMin(node.right);
                successor.left = node.left;
                node.right = node.left = null;
                return successor;
            }
        }
    }

    /**
     * preorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence of traversal
     */
    public List<Integer> preorder(boolean recur) {
        if (recur) {
            List<Integer> seq = new ArrayList<>();
            preorderRecur(this.root, seq);
            return seq;
        } else {
            return this.preorderNonRecur(this.root);
        }
    }

    /**
     * Recursive preorder traversal
     *
     * @param node root node
     * @param seq  sequence
     */
    private void preorderRecur(TreeNode node, List<Integer> seq) {
        if (node == null)
            return;
        seq.add(node.val);
        preorderRecur(node.left, seq);
        preorderRecur(node.right, seq);
    }

    /**
     * Non recursive traversal
     *
     * @param node root node
     * @return sequence of traversal
     */
    private List<Integer> preorderNonRecur(TreeNode node) {
        List<Integer> seq = new ArrayList<>(this.size);
        if (node == null) return seq;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            seq.add(cur.val);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return seq;
    }

    /**
     * Inorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence
     */
    public List<Integer> inorder(boolean recur) {
        if (recur) {
            List<Integer> seq = new ArrayList<>();
            inorderRecur(this.root, seq);
            return seq;
        } else {
            return inorderNonRecur(this.root);
        }
    }

    /**
     * Recursive inorder traversal
     *
     * @param node root node
     * @param seq  sequence
     */
    private void inorderRecur(TreeNode node, List<Integer> seq) {
        if (node == null)
            return;
        inorderRecur(node.left, seq);
        seq.add(node.val);
        inorderRecur(node.right, seq);
    }


    /**
     * Non recursive traversal
     *
     * @param node root node
     * @return sequence
     */
    private List<Integer> inorderNonRecur(TreeNode node) {
        List<Integer> seq = new ArrayList<>(this.size);
        if (node == null) return seq;
        TreeNode cur = node;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                seq.add(cur.val);
                cur = cur.right;
            }
        }
        return seq;
    }

    /**
     * Postorder traversal
     *
     * @param recur recursive or loop
     * @return sequence
     */
    public List<Integer> postorder(boolean recur) {
        if (recur) {
            List<Integer> seq = new ArrayList<>();
            postorderRecur(this.root, seq);
            return seq;
        } else {
            return postorderNonRecur(this.root);
        }
    }

    /**
     * Recursive postorder traversal
     *
     * @param node root node
     * @param seq  sequence
     */
    private void postorderRecur(TreeNode node, List<Integer> seq) {
        if (node == null)
            return;
        postorderRecur(node.left, seq);
        postorderRecur(node.right, seq);
        seq.add(node.val);
    }

    /**
     * Non recursive postorder traversal
     *
     * @param node root node
     * @return sequence
     */
    private List<Integer> postorderNonRecur(TreeNode node) {
        List<Integer> seq = new ArrayList<>(this.size);
        if (node == null) return seq;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node;
        TreeNode prev = node;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.right == null || cur.right == prev) {
                    seq.add(cur.val);
                    prev = cur;
                    cur = null;
                } else {
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
        return seq;
    }

    /**
     * Level order traversal
     * From depth 1, 2, ..., to depth n.
     *
     * @return sequence
     */
    public List<Integer> levelOrder() {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> seq = new ArrayList<>(this.size);

        while (!queue.isEmpty()) {
            TreeNode removeNode = queue.poll();
            seq.add(removeNode.val);
            if (removeNode.left != null)
                queue.add(removeNode.left);
            if (removeNode.right != null)
                queue.add(removeNode.right);
        }
        return seq;
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
     * find the kth smallest value
     *
     * @param k kth
     * @return kth smallest
     * @throws EmptyBSTException may be empty
     */
    public int kthSmallest(int k) throws EmptyBSTException {
        if (this.size == 0) {
            throw new EmptyBSTException("BST is empty");
        }
        int[] kth = new int[1];
        int[] cur = new int[1];
        kSmaller(this.root, k, cur, kth);

        return kth[0];
    }

    /**
     * helper for finding kth smallest item
     *
     * @param node node
     * @param k    kth
     * @param cur  pointer for currently parsing node
     * @param kth  pointer for kth node
     */
    private void kSmaller(TreeNode node, int k, int[] cur, int[] kth) {
        if (node == null) return;

        this.kSmaller(node.left, k, cur, kth);
        if (cur[0] >= k) return;
        cur[0]++;
        kth[0] = node.val;
        this.kSmaller(node.right, k, cur, kth);
    }

    /**
     * @return depth of BST
     */
    public int depth() {
        return this.depth(this.root);
    }

    /**
     * helper for calculating depth of BST
     *
     * @param node node
     * @return depth of BST
     */
    private int depth(TreeNode node) {
        if (node == null) return 0;
        int leftDepth = this.depth(node.left);
        int rightDepth = this.depth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * find the closest value, if there are two or more closest values, pick the smallest one.
     *
     * @param target target value
     * @return closest value
     */
    public int closestSmall(double target) throws EmptyBSTException {
        return closestSmall(this.root, target);
    }

    /**
     * find the closest value, if there are two or more closest values, pick the smallest one.
     *
     * @param node   node
     * @param target target value
     * @throws EmptyBSTException may be empty
     */
    private int closestSmall(TreeNode node, double target) throws EmptyBSTException {
        if (node == null) throw new EmptyBSTException("BST is empty");
        if (node.left == null && node.right == null) {
            return node.val;
        } else if (node.left == null) {
            int rightMin = min(node.right).val;
            if (target <= ((double) node.val + rightMin) / 2)
                return node.val;
            else return closestSmall(node.right, target);
        } else if (node.right == null) {
            int leftMax = max(node.left).val;
            if (target > ((double) node.val + leftMax) / 2)
                return node.val;
            else return closestSmall(node.left, target);
        } else {
            int rightMin = min(node.right).val;
            int leftMax = max(node.left).val;
            double leftBound = ((double) leftMax + node.val) / 2;
            double rightBound = ((double) rightMin + node.val) / 2;
            if (target < leftBound) return closestSmall(node.left, target);
            else if (target == leftBound) return leftMax;
            else if (leftBound < target && target <= rightBound) return node.val;
            else return closestSmall(node.right, target);
        }
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
