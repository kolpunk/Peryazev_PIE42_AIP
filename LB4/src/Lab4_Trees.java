import java.util.*;

class TreeNode {
    int value;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class MyBinaryTree {
    TreeNode head;

    // 1. Прямой обход
    public void traversePre(TreeNode node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        traversePre(node.leftChild);
        traversePre(node.rightChild);
    }

    // 2. Центрированный обход
    public void traverseIn(TreeNode node) {
        if (node == null) return;
        traverseIn(node.leftChild);
        System.out.print(node.value + " ");
        traverseIn(node.rightChild);
    }

    // 3. Обратный обход 
    public void traversePost(TreeNode node) {
        if (node == null) return;
        traversePost(node.leftChild);
        traversePost(node.rightChild);
        System.out.print(node.value + " ");
    }

    // 4. Поуровневый обход 
    public void traverseLevel() {
        if (head == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.print(curr.value + " ");
            if (curr.leftChild != null) queue.offer(curr.leftChild);
            if (curr.rightChild != null) queue.offer(curr.rightChild);
        }
    }

    // 5. Высота дерева
    public int getDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getDepth(node.leftChild), getDepth(node.rightChild));
    }

    // 6. Проверка на полное дерево
    public boolean checkFull(TreeNode node) {
        if (node == null) return true;
        if (node.leftChild == null && node.rightChild == null) return true;
        if (node.leftChild != null && node.rightChild != null) {
            return checkFull(node.leftChild) && checkFull(node.rightChild);
        }
        return false;
    }

    // 7. Вставка в BST 
    public void addToBST(int val) {
        TreeNode newNode = new TreeNode(val);
        if (head == null) {
            head = newNode;
            return;
        }

        TreeNode current = head;
        while (true) {
            if (val < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    break;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    break;
                }
                current = current.rightChild;
            }
        }
    }

    // 8. Построение сбалансированного дерева из отсортированного массива
    public TreeNode buildBalanced(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.leftChild = buildBalanced(arr, start, mid - 1);
        node.rightChild = buildBalanced(arr, mid + 1, end);
        return node;
    }

    // 9. Вставка по уровням
    public void insertByLevel(int val) {
        TreeNode newNode = new TreeNode(val);
        if (head == null) {
            head = newNode;
            return;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(head);

        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp.leftChild == null) {
                temp.leftChild = newNode;
                break;
            } else {
                q.offer(temp.leftChild);
            }
            if (temp.rightChild == null) {
                temp.rightChild = newNode;
                break;
            } else {
                q.offer(temp.rightChild);
            }
        }
    }

    // 10. Удаление узла
    public void removeNode(int key) {
        if (head == null) return;
        if (head.leftChild == null && head.rightChild == null) {
            if (head.value == key) head = null;
            return;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(head);
        TreeNode target = null, last = null;

        while (!q.isEmpty()) {
            last = q.poll();
            if (last.value == key) target = last;
            if (last.leftChild != null) q.offer(last.leftChild);
            if (last.rightChild != null) q.offer(last.rightChild);
        }

        if (target != null) {
            int deepestVal = last.value;
            deleteDeepestNode(last);
            target.value = deepestVal;
        }
    }

    private void deleteDeepestNode(TreeNode delNode) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(head);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.leftChild != null) {
                if (curr.leftChild == delNode) {
                    curr.leftChild = null;
                    return;
                } else q.offer(curr.leftChild);
            }
            if (curr.rightChild != null) {
                if (curr.rightChild == delNode) {
                    curr.rightChild = null;
                    return;
                } else q.offer(curr.rightChild);
            }
        }
    }

    // 11. Визуализация дерева
    public void displayTree() {
        System.out.println("\nСтруктура дерева:");
        printNode(head, "", true);
    }

    private void printNode(TreeNode node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("└── ");
                indent += "    ";
            } else {
                System.out.print("├── ");
                indent += "│   ";
            }
            System.out.println(node.value);
            printNode(node.leftChild, indent, false);
            printNode(node.rightChild, indent, true);
        }
    }
}

public class Lab4_Trees {
    public static void main(String[] args) {
        MyBinaryTree tree = new MyBinaryTree();

        System.out.println("1. Базовые обходы");
        tree.head = new TreeNode(100);
        tree.head.leftChild = new TreeNode(200);
        tree.head.rightChild = new TreeNode(300);
        tree.head.leftChild.leftChild = new TreeNode(400);
        tree.head.leftChild.rightChild = new TreeNode(500);

        System.out.print("Прямой: ");
        tree.traversePre(tree.head);

        System.out.print("\nЦентрированный: ");
        tree.traverseIn(tree.head);

        System.out.print("\nОбратный: ");
        tree.traversePost(tree.head);

        System.out.print("\nПо уровням: ");
        tree.traverseLevel();

        System.out.println("\nГлубина: " + tree.getDepth(tree.head));

        System.out.println("\n2. Полное бинарное дерево");
        MyBinaryTree fullTree = new MyBinaryTree();
        fullTree.head = new TreeNode(15);
        fullTree.head.leftChild = new TreeNode(25);
        fullTree.head.rightChild = new TreeNode(35);
        fullTree.head.leftChild.leftChild = new TreeNode(45);
        fullTree.head.leftChild.rightChild = new TreeNode(55);
        fullTree.head.rightChild.leftChild = new TreeNode(65);
        fullTree.head.rightChild.rightChild = new TreeNode(75);

        System.out.println("Является полным? " + fullTree.checkFull(fullTree.head));
        fullTree.displayTree();

        System.out.println("\n3. BST из последовательности");
        MyBinaryTree bst = new MyBinaryTree();
        int[] nums = {44, 22, 66, 11, 33, 55, 77};
        for (int n : nums) bst.addToBST(n);
        System.out.print("Центрированный обход BST (сорт.): ");
        bst.traverseIn(bst.head);
        bst.displayTree();

        System.out.println("\n4. Сбалансированное дерево из массива");
        int[] sortedArr = {5, 10, 15, 20, 25, 30, 35};
        MyBinaryTree balTree = new MyBinaryTree();
        balTree.head = balTree.buildBalanced(sortedArr, 0, sortedArr.length - 1);
        balTree.displayTree();
        System.out.println("Высота сбалансированного: " + balTree.getDepth(balTree.head));

        System.out.println("\n5. Вставка/удаление по уровням");
        MyBinaryTree levelTree = new MyBinaryTree();
        for (int i = 1; i <= 6; i++) levelTree.insertByLevel(i * 10);

        System.out.print("До удаления: ");
        levelTree.traverseLevel();
        levelTree.displayTree();

        levelTree.removeNode(30);
        System.out.print("После удаления 30: ");
        levelTree.traverseLevel();
        levelTree.displayTree();
    }
}