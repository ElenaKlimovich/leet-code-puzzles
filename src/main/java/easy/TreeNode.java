package easy;

public class TreeNode {

    int value;
    TreeNode leftNode;
    TreeNode rightNode;

    TreeNode() {
    }

    TreeNode(int val) {
        this.value = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.value = val;
        this.leftNode = left;
        this.rightNode = right;
    }
}