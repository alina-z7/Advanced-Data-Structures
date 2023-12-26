import java.util.*;
import java.util.LinkedList;

public class Graph {

    private HashMap<String, ArrayList<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public boolean addVertex(String vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean removeVertex(String vertex) {
        if (adjacencyList.containsKey(vertex)) {
            for (String v : adjacencyList.get(vertex)) {
                adjacencyList.get(v).remove(vertex);
            }
            adjacencyList.remove(vertex);
            return true;
        }
        return false;
    }

    public boolean addEdge(String vertexA, String vertexB) {
        if (adjacencyList.containsKey(vertexA) && adjacencyList.containsKey(vertexB)) {
            adjacencyList.get(vertexA).add(vertexB);
            adjacencyList.get(vertexB).add(vertexA);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertexA, String vertexB) {
        if (adjacencyList.containsKey(vertexA) && adjacencyList.containsKey(vertexB)) {
            adjacencyList.get(vertexA).remove(vertexB);
            adjacencyList.get(vertexB).remove(vertexA);
            return true;
        }
        return false;
    }


    public ArrayList<String> BFS(String startVertex) {
        ArrayList<String> result = new ArrayList<>();
        if (!adjacencyList.containsKey(startVertex)) {
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        queue.offer(startVertex);
        visited.add(startVertex);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            for (String neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

    public ArrayList<String> DFS(String startVertex) {
        ArrayList<String> result = new ArrayList<>();
        if (!adjacencyList.containsKey(startVertex)) {
            return result;
        }

        HashSet<String> visited = new HashSet<>();
        DFSHelper(startVertex, visited, result);

        return result;
    }

    private void DFSHelper(String vertex, HashSet<String> visited, ArrayList<String> result) {
        visited.add(vertex);
        result.add(vertex);

        for (String neighbor : adjacencyList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSHelper(neighbor, visited, result);
            }
        }
    }

    public ArrayList<String> DFS() {
        return null;
    }

    public static void main(String[] args) {
        Graph bidirectGraph = new Graph();

        bidirectGraph.addVertex("A");
        bidirectGraph.addVertex("B");
        bidirectGraph.addVertex("C");
        bidirectGraph.addVertex("D");


        bidirectGraph.addEdge("A", "B");
        bidirectGraph.addEdge("A", "C");
        bidirectGraph.addEdge("A", "D");
        bidirectGraph.addEdge("B", "D");
        bidirectGraph.addEdge("C", "D");

        bidirectGraph.removeEdge("A", "B");

        bidirectGraph.removeVertex("B");

        System.out.println(bidirectGraph.adjacencyList);

        System.out.println(bidirectGraph.BFS("A"));
        System.out.println(bidirectGraph.DFS("A"));

    }
}
