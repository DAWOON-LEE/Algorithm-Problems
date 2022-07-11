package problem_3584;

import java.io.*;
import java.util.*;

public class Problem_3584 {
    private static class Node {
        private int data;
        private Node parent;
        private List<Node> children;

        public Node(int data) {
            this.data = data;
            this.parent = this;
            this.children = new ArrayList<>();
        }
    }

    private static StringBuilder answer = new StringBuilder();
    private static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(input.readLine());
        while (T-- > 0) {
            final int N = Integer.parseInt(input.readLine());
            tree = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                tree[i] = new Node(i);
            }

            for (int i = 1; i < N; i++) {
                StringTokenizer tokenizer = new StringTokenizer(input.readLine());
                int parent = Integer.parseInt(tokenizer.nextToken());
                int child = Integer.parseInt(tokenizer.nextToken());
                tree[parent].children.add(tree[child]);
                tree[child].parent = tree[parent];
            }

            StringTokenizer tokenizer = new StringTokenizer(input.readLine());
            int node1 = Integer.parseInt(tokenizer.nextToken());
            int node2 = Integer.parseInt(tokenizer.nextToken());
            answer.append(findLCA(getRoot(), tree[node1], tree[node2]).data).append('\n');
        }

        System.out.print(answer);
    }

    private static Node getRoot() {
        for (int i = 1; i <= tree.length - 1; i++) {
            if (tree[i].parent == tree[i]) {
                return tree[i];
            }
        }

        return null;
    }

    private static Node findLCA(Node root, Node node1, Node node2) {
        HashMap<Integer, Node> parent = new HashMap<>(); // key : child, value : parent

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node cur = nodes.poll();

            for (Node nxt : cur.children) {
                parent.put(nxt.data, cur);
                nodes.add(nxt);
            }
        }

        HashSet<Integer> ancestor = new HashSet<>();
        while (node1 != null) {
            ancestor.add(node1.data);
            node1 = parent.get(node1.data);
        }

        while (node2 != null) {
            if (ancestor.contains(node2.data)) {
                return node2;
            }

            node2 = parent.get(node2.data);
        }

        return null;
    }
}
