package net.deep;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    List<TreeNode> deepest = new ArrayList<>();
    int maxDepth = 0;

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return root;
        getDeepestNodes(root, 0);

        //Now among all of the deepest nodes find the lca(deepest[0], deepest[1], ... deepest[n-1])
        if (deepest.size() == 1) return deepest.get(0);
        if (deepest.size() == 2) return lca(root, deepest.get(0), deepest.get(1));

        TreeNode lca = lca(root, deepest.get(0), deepest.get(1));

        for (int i = 2; i < deepest.size(); i++) {
            lca = lca(root, lca, deepest.get(i));
        }

        return lca;
    }


    TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p) return p;
        if (root == q) return q;

        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        if (left != null && right != null) return root;
        if (left != null && right == null) return lca(root.left, p, q);
        //if (left == null && right != null)
        return lca(root.right, p, q);
    }

    void getDeepestNodes(TreeNode root, int depth) {
        if (depth > maxDepth) {
            maxDepth = depth; //Update the maximum depth
            deepest = new ArrayList<>(); //Erase old candidates and start new candidates list
            //Don't forget to add this node as a candidate
            deepest.add(root);
        } else if (depth == maxDepth) {
            deepest.add(root); //This node is one of the deepest children.
        }

        //Now try traversing the rest of the binary tree
        if (root.left != null) getDeepestNodes(root.left, depth + 1);
        if (root.right != null) getDeepestNodes(root.right, depth + 1);

    }
}
