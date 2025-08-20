import java.util.*;

// Time Complexity : O(n), where n is the number of nodes (each node visited once in BFS).
// Space Complexity : O(n), for the queues used by level-order traversal.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
//
// Approach (Level-order BFS with parent tracking):
// - Traverse the tree level by level.
// - For each node, also track its parent in a parallel queue.
// - If x and y are found on the same level with different parents, they are cousins → return true.
// - If one is found without the other at a level, they cannot be cousins → return false.
// - If neither condition triggers by the end, return false.
public class CousinsInTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> parentQ = new LinkedList<>();
        q.add(root);
        parentQ.add(null);  // root has no parent

        while (!q.isEmpty()) {
            int size = q.size();
            boolean xFound = false, yFound = false;
            TreeNode xParent = null, yParent = null;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                TreeNode parent = parentQ.poll();

                // Check current node for matches
                if (node.val == x) {
                    xFound = true;
                    xParent = parent;
                } else if (node.val == y) {
                    yFound = true;
                    yParent = parent;
                }

                // Enqueue children with parent references
                if (node.left != null) {
                    q.add(node.left);
                    parentQ.add(node);
                }
                if (node.right != null) {
                    q.add(node.right);
                    parentQ.add(node);
                }
            }

            // After finishing this level, decide
            if (xFound && yFound) return xParent != yParent; // same level, different parents?
            if (xFound || yFound) return false;              // only one found at this level
        }

        return false; // not found as cousins
    }

    // --- Main method for testing ---
    public static void main(String[] args) {

        // Tree 1
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.right = new TreeNode(4);
        t1.right.right = new TreeNode(5);

        CousinsInTree sol = new CousinsInTree();
        System.out.println("Tree1: x=4, y=5 → " + sol.isCousins(t1, 4, 5)); // true

        // Tree 2
        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(3);
        t2.left.left = new TreeNode(4);
        System.out.println("Tree2: x=4, y=3 → " + sol.isCousins(t2, 4, 3)); // false

        // Tree 3
        TreeNode t3 = new TreeNode(1);
        t3.left = new TreeNode(2);
        t3.right = new TreeNode(3);
        t3.left.left = new TreeNode(4);
        t3.left.right = new TreeNode(5);
        System.out.println("Tree3: x=4, y=5 → " + sol.isCousins(t3, 4, 5)); // false
    }
}


