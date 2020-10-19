package lab_four;

import com.sun.source.tree.Tree;

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
    public TreeNode current = null;
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
        for(int i=0;i<array.length;i++){
            insert(array[i]);
        }

    }

    /**
     * todo: init from an Integer Object array
     *
     * @param array init
     * @throws DuplicateKeyException should not have duplicate keys
     */
    public BinarySearchTree(Integer[] array) throws DuplicateKeyException {
        for(int i=0;i<array.length;i++){
            insert(array[i]);
        }

    }

    /**
     * todo: Whether have this key or not
     *
     * @param key k
     * @return yes or no
     */
    public boolean hasKey(int key) {
        TreeNode current = root;
        while (current != null) {
            //小于当前节点元素遍历其左子树
            if (key<current.val) {
                current = current.left;
                //大于当前节点元素遍历其右子树
            } else if (key>current.val) {
                current = current.right;
            } else {
                //查到
                return true;
            }
        }
        return false;
    }

    /**
     * todo: insert a new key into the BST
     *
     * @param x key
     * @throws DuplicateKeyException have no duplicate key
     */
    public void insert(int x) throws DuplicateKeyException {
        if(root == null){		//为空树
            root =new TreeNode(x);
            size++;
        }
        else{
            current = root;
            while(current != null){   //寻找叶子节点
                if(x == current.val){
                    throw new DuplicateKeyException("insert失败：key重复");
                }
                if(x < current.val){
                    if(current.left == null){
                        current.left = new TreeNode(x);
                        size++;
                        break;
                    }
                    current = current.left;

                }
                else{
                    if(current.right == null){
                        current.right = new TreeNode(x);
                        size++;
                        break;
                    }
                    current = current.right;
                }
            }
        }

    }



    /**
     * todo: remove an item
     *
     * @param x key
     * @throws EmptyBSTException  remove an item from an empty BST
     * @throws NoSuchKeyException item not found
     */
    public void remove(int x) throws EmptyBSTException, NoSuchKeyException {
        if (size == 0){
            throw new EmptyBSTException("remove失败");
        }
        if(!hasKey(x)){
            throw new NoSuchKeyException("remove失败");
        }
        remove(x,root);
        size--;
    }


    private TreeNode remove (int x, TreeNode node){
        if (null == node) return null;
        if (x<node.val)
            node.left = remove(x, node.left);
        else if (x> node.val)
        node.right = remove(x, node.right);
	    else{
            if (null == node.left)  		node = node.right;
            else if (null == node.right)	node = node.left;
            else {
                node.val = findMin(node.right).val;
                node.right = remove(node.val, node.right);
            }
        }
        return node;
    }

    // 找到以node为根节点的所有节点中值最小的节点，也就是最左端的节点
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }



    /**
     * todo: preorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence of traversal
     */
    public List<Integer> preorder(boolean recur) {
        List<Integer> integerList = new ArrayList<>();
        if (recur) {
            return preOrderPrintTree(root,integerList);
        } else {
            return preorder(root);
        }
    }

    //前序遍历数
    private List<Integer> preOrderPrintTree(TreeNode node,List<Integer> integerList){
        if(node == null)
            return integerList;
        integerList.add(node.val);
        preOrderPrintTree(node.left,integerList);
        preOrderPrintTree(node.right,integerList);
        return integerList;
    }


    private List<Integer> preorder(TreeNode node) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (root == null)
            return resultList;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            resultList.add(temp.val);
            if (temp.right != null)
                stack.push(temp.right);
            if (temp.left != null)
                stack.push(temp.left);
        }
        return resultList;

    }


    /**
     * todo: Inorder traversal
     *
     * @param recur using recursive or loop
     * @return sequence
     */
    public List<Integer> inorder(boolean recur) {
        List<Integer> integerList = new ArrayList<>();
        if (recur) {
            return inOrderPrintTree(root,integerList);
        } else {
            return inorder(root);
        }
    }

    //中序遍历数
    private List<Integer> inOrderPrintTree(TreeNode node,List<Integer> integerList){
        if(node == null)
            return integerList;
        inOrderPrintTree(node.left,integerList);
        integerList.add(node.val);
        inOrderPrintTree(node.right,integerList);
        return integerList;
    }

    private List<Integer> inorder (TreeNode node){
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        boolean done = false;
        TreeNode current = root;
        while (!done) {
            if (current != null) {
                s.push(current);
                current = current.left;
            } else {
                if (s.isEmpty())
                    done = true;
                else {
                    TreeNode temp = s.pop();
                    result.add(temp.val);
                    current = temp.right;
                }
            }
        }

        return result;
    }

    /**
     * todo: Postorder traversal
     *
     * @param recur recursive or loop
     * @return sequence
     */
    public List<Integer> postorder(boolean recur) {
        List<Integer> integerList = new ArrayList<>();
        if (recur) {
            return postOrderPrintTree(root,integerList);
        } else {
            return postOrder(root);
        }
    }

    //后序遍历数
    private List<Integer> postOrderPrintTree(TreeNode node,List<Integer> integerList){
        if(node == null)
            return integerList;
        postOrderPrintTree(node.left,integerList);
        postOrderPrintTree(node.right,integerList);
        integerList.add(node.val);
        return integerList;
    }

    private List<Integer> postOrder(TreeNode node){
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode prev = null;
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.peek();
            // we are traversing down the tree
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null)
                    stack.push(curr.left);
                else if (curr.right != null)
                    stack.push(curr.right);
                else {
                    result.add(curr.val);
                    stack.pop();
                }
                // we are traversing up the tree from the left
            } else if (curr.left == prev) {
                if (curr.right != null)
                    stack.push(curr.right);
                else {
                    result.add(curr.val);
                    stack.pop();
                }
                // we are traversing up the tree from the right
            } else if (curr.right == prev) {
                result.add(curr.val);
                stack.pop();
            }
            // record previously traversed node
            prev = curr;
        }
        return result;


    }

    /**
     * todo: Level order traversal
     * From depth 1, 2, ..., to depth n.
     *
     * @return sequence
     */
    public List<Integer> levelOrder() {
        List<Integer> result = new LinkedList<>();
        if(root==null){
            return result;
        }
        Queue<TreeNode> qu=new LinkedList<>();
        qu.add(root);
        while (!qu.isEmpty()){
            root=qu.peek();
            qu.poll();
            result.add(root.val);
            if(root.left!=null){
                qu.add(root.left);
            }
            if(root.right!=null){
                qu.add(root.right);
            }
        }
        return result;
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
        if (size == 0)
            throw new EmptyBSTException("BST is empty");
        Stack<TreeNode> s = new Stack<>();
        TreeNode node =  root;
        while(node!=null || !s.isEmpty()) {
            while(node!=null) {
                s.push(node);
                node = node.left;
            }
            node = s.pop();
            if(--k == 0) break;
            node = node.right;
        }
        return node.val;
    }



    /**
     * todo
     *
     * @return depth of BST
     */
    public int depth() {
        return height(root);
    }
    public int height(TreeNode node){
        if(node==null){
            return 0;
        }else{
            int i=height(node.left);
            int j=height(node.right);
            return (i<j)?(j+1):(i+1);
        }
    }


    /**
     * todo: find the closest value, if there are two or more closest values, pick the smallest one.
     *
     * @param target target value
     * @return closest value
     */
    public int closestSmall(double target) throws EmptyBSTException {
        if (size == 0)
            throw new EmptyBSTException("BST is empty");
        if (root == null) {
            return 0;
        }

        TreeNode lowerNode = lowerBound(root, target);
        TreeNode upperNode = upperBound(root, target);

        if (lowerNode == null) {
            return upperNode.val;
        }

        if (upperNode == null) {
            return lowerNode.val;
        }

        if (target - lowerNode.val > upperNode.val - target) {
            return upperNode.val;
        }

        return lowerNode.val;
    }

    // find the node with the largest value that smaller than target
    private TreeNode lowerBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }

        if (target <= root.val) {
            return lowerBound(root.left, target);
        }

        // root.val < target
        TreeNode lowerNode = lowerBound(root.right, target);
        if (lowerNode != null) {
            return lowerNode;
        }

        return root;
    }

    // find the node with the smallest value that larger than or equal to target
    private TreeNode upperBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }

        if (root.val < target) {
            return upperBound(root.right, target);
        }

        // root.val >= target
        TreeNode upperNode = upperBound(root.left, target);
        if (upperNode != null) {
            return upperNode;
        }

        return root;
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
