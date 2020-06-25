package algorithm.search;

public class BinarySearchTree {
	private BSTNode root;

	private class BSTNode {
		private int data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public BSTNode() {
			this.data = 0;
			this.left = null;
			this.right = null;
		}
	}

	public BinarySearchTree() {
		this.root = new BSTNode();
	}

	public BSTNode BST_Search(BSTNode tree, int data) {
		if (tree == null) {
			return null;
		}
		if (tree.data == data) {
			return tree;
		} else if (tree.data > data) {
			return BST_Search(tree.left, data);
		} else {
			return BST_Search(tree.right, data);
		}
	}

	public BSTNode BST_Search(int data) {
		BSTNode tree = root;
		while (true) {
			if (root == null) {
				return null;
			}
			if (tree.data == data) {
				return root;
			} else if (tree.data > data) {
				tree = tree.left;
			} else {
				tree = tree.right;
			}
		}
	}

	public BSTNode BST_FIndMin(BSTNode root) {
		if (root == null)
			return null;
		else if (root.left == null)
			return root;
		else
			return BST_FIndMin(root.left);
	}

	public BSTNode BST_FIndMin() {
		if (root == null)
			return null;
		while (root.left != null) {
			root = root.left;
		}
		return root;
	}

	public BSTNode BST_FIndMax(BSTNode root) {
		if (root == null)
			return null;

		while (root.right != null) {
			root = root.right;
		}
		return root;
	}

	public void BST_Insert(int data) {
		insert(this.root, data);
	}

	private void insert(BSTNode root, int data) {
		if (root.data < data) {
			if (root.right == null) {
				root.right = new BSTNode(data);
			} else {
				insert(root.right, data);
			}
		} else {
			if (root.left == null) {
				root.left = new BSTNode(data);
			} else {
				insert(root.left, data);
			}
		}
	}

	public BSTNode BST_Remove(int data) {
		return remove(this.root, data);
	}

	private BSTNode remove(BSTNode root, int data) {

		if (root == null) {
			return null;
		}
		if (root.data < data) {
			root.right = remove(root.right, data);
		} else if (root.data > data) {
			root.left = remove(root.left, data);
		} else {
			BSTNode tmp = null;
			if (root.left != null && root.right != null) {
				tmp  = BST_FIndMax(root.left);
				root.data = tmp.data;
				root.left = remove(root.left, root.data);
				tmp = null;
			} else {
				tmp = root;
				if (root.left == null) {
					root= root.right;
				}
				if (root.right == null) {
					root = root.left;
				}
			}
			tmp = null;
		}
		return root;
	}
}
