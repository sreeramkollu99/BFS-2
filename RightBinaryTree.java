import java.util.*;

// Time Complexity : O(n), where n is the number of nodes (each node is visited once).
// Space Complexity : O(h), where h = height of the tree, due to recursion stack.
//                    Output list also stores up to O(h) elements (one per level).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
//
// Approach (DFS, right-first traversal):
// - Perform a DFS where we visit the right child before the left child.
// - Maintain a "level" index while traversing.
// - If the current level is being visited for the first time (list.size() == level),
//   then the current node is the rightmost node of that level → add it to the list.
// - This way, the first node we see at each level (going right-first) is what shows up in the right side view.

public class RightBinaryTree {
    List<Integer> list;

    public List<Integer> rightSideView(TreeNode root) {
        list = new ArrayList<>();
        helper(root, 0);
        return list;
    }

    private void helper(TreeNode root, int level) {
        if (root == null) return;

        // If this level is seen first time, add the node
        if (list.size() == level) {
            list.add(root.val);
        }

        // Recurse right first to ensure rightmost node is seen first
        helper(root.right, level + 1);
        helper(root.left, level + 1);
    }

    // --- Main method for testing ---
    public static void main(String[] args) {
        /*
           Expected right side view: [1, 3, 4]
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        RightBinaryTree sol = new RightBinaryTree();
        List<Integer> rightView = sol.rightSideView(root);

        System.out.println("Right side view:");
        System.out.println(rightView); // [1, 3, 4]
    }
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

