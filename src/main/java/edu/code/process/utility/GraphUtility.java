package edu.code.process.utility;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import edu.code.model.Graph;
import edu.code.model.GraphEdge;

public class GraphUtility {

	/**
	 * Get Number of connected components when graph is represented in 
	 * Adjacency List - (Array of linked lists)
	 * @param graph
	 * @return
	 */
	public static int getNumberOfConnectedComponents(Graph graph){
		boolean[] visited = new boolean[graph.getVertices()];
		int count = 0;
		System.out.println("Checking .. "+visited[2]);
		for (int i=0; i<graph.getVertices(); i++){
			if (!visited[i]){
				count++;
				DFSTraverse(graph, i, visited);
			}
		}
		return count;
	}

	/**
	 * Recursive calls to traverse the graph
	 * @param graph: Adjacency List - (Array of linked lists)
	 * @param i
	 * @param visited
	 */
	private static void DFSTraverse(Graph graph, int i, boolean[] visited) {
		if (visited[i]){
			return;
		}
		visited[i] = true;
		if (graph.getNodeList()[i]==null){
			return;
		}
		Iterator<GraphEdge> edgesIterator = graph.getNodeList()[i].iterator();
		while(edgesIterator.hasNext()){
			GraphEdge edge = edgesIterator.next();

			DFSTraverse(graph, edge.getDestination(), visited);
		}
	}
	
	/**
	 * Retrieves shortest path and shortest distance between two nodes in a graph
	 * It is using recursive dynamic programming
	 * @param graph: Adjacency List - (Array of linked lists)
	 * @param source
	 * @param dest
	 * @return
	 */
	public static int getShortestDistance(Graph graph, int source, int dest){
		boolean[] visited = new boolean[graph.getVertices()];
		int[] distances = new int[graph.getVertices()];
		for ( int i=0; i< distances.length; i++)
			distances[i] = -1;
		// paths hold shortest distance from each node to target
		String[] paths = new String[graph.getVertices()];
		int shortestDist = DFSTraverseForShortestPath(graph, source, dest, visited, distances, paths);
		System.out.println("shortest path:"+paths[source]);
		return shortestDist;
	}

	private static int DFSTraverseForShortestPath(Graph graph, int source,
			int dest, boolean[] visited, int[] distances, String[] paths) {
		if (source == dest){
			paths[source] = ""+dest;
			return 0;
		}
		if (visited[source]){
			return distances[source];
		}
		visited[source] = true;
		int shortestDist = -1;
		LinkedList<GraphEdge> edges = graph.getNodeList()[source];
		if (edges != null){
			Iterator<GraphEdge> iterator = edges.iterator();
			while(iterator.hasNext()){
				GraphEdge edge = iterator.next();
				int distance = DFSTraverseForShortestPath(graph, edge.getDestination(), dest, visited, distances, paths);
				// update shortest distance only when there is connectivity to target. Else it would be -1
				if ( distance >=0){
					distance += edge.getWeight();
					if (shortestDist == -1 || shortestDist > distance){
						shortestDist = distance;
						paths[source] = source+paths[edge.getDestination()];
					}
				}
				
			}
		}
		
		distances[source] = shortestDist;
		return shortestDist;
	}
	
	/**
	 * Get Number of connected components when graph is represented in 
	 * Adjacency Matrix representation of graph
	 * @param graph
	 * @return
	 */
	public static int getNumberOfConnectedComponents(int[][] adjacencyMatrix){
		int count = 0;
		if (adjacencyMatrix != null && adjacencyMatrix.length > 1){
			boolean[] visited = new boolean[adjacencyMatrix.length];
			for (int i=0; i<adjacencyMatrix.length;i++){
				if (!visited[i]){
					count++;
					DFSTraverse(adjacencyMatrix, visited, i);
				}
					
			}
		}
		return count;
	}

	private static void DFSTraverse(int[][] adjacencyMatrix, boolean[] visited, int source) {
		if (visited[source])
			return;
		visited[source]=true;
		if (adjacencyMatrix.length <= source){
			return;
		}
		
		for (int i=0;i<adjacencyMatrix[source].length;i++){
			if (adjacencyMatrix[source][i] == 1){
				DFSTraverse(adjacencyMatrix, visited, i);
			}
		}
	}
	
	/**
	 * Get Number of connected components when graph is represented in 
	 * Adjacency Matrix representation of graph
	 * @param graph
	 * @return
	 */
	public static int getNumberOfConnectedComponentsIterative(int[][] adjacencyMatrix){
		int count = 0;
		
		boolean[] visited = new boolean[adjacencyMatrix.length];
		for (int i=0;i<adjacencyMatrix.length;i++){
			if (!visited[i]){
				count++;
				Stack<Integer> stack = new Stack<Integer>();
				visited[i]=true;
				stack.push(i);
				while(true){
					if (stack.isEmpty())
						break;
					int source = stack.pop();
					if (adjacencyMatrix.length <= source ){
						continue;
					}
					for (int j=0;j<adjacencyMatrix[source].length;j++){
						if (i==j){
							continue;
						}
						if (adjacencyMatrix[source][j] == 1 && !visited[j]){
							stack.push(j);
							visited[j]=true;
						}
					}
				}
			}
		}
		
		return count;
		
	}
	
	
	/**
	 * Retrieves shortest path and shortest distance between two nodes in a graph
	 * It is using recursive dynamic programming
	 * @param adjacencyMatrix : Graph represented in adjacency matrix
	 * @param source
	 * @param dest
	 * @return
	 */
	public static int getShortestDistance(int[][] adjacencyMatrix, int source, int destination){
		boolean visited[] = new boolean[adjacencyMatrix.length];
		int[] distances = new int[adjacencyMatrix.length];
		for (int i=0;i<distances.length;i++){
			distances[i] = -1;
		}
		String[] paths = new String[adjacencyMatrix.length];
		int shortestDist = getShortestDistance(adjacencyMatrix, source, destination, distances, visited, paths);
		System.out.println("shortest path:"+paths[source]);
		return shortestDist;
	}

	private static int getShortestDistance(int[][] adjacencyMatrix, int source,
			int destination, int[] distances, boolean[] visited, String[] paths) {
		if (source==destination){
			paths[source]=""+source;
			visited[source]=true;
			return 0;
		}
		if (visited[source]){
			return distances[source];
		}
		visited[source]=true;
		int shortestDist = -1;
		if (adjacencyMatrix.length >= source){
			
			for (int i=0;i<adjacencyMatrix[source].length;i++){
				if (adjacencyMatrix[source][i] == 1){
					int distance = getShortestDistance(adjacencyMatrix, i, destination, distances, visited, paths);
					if (distance >=0){
						distance+=1;
						if (shortestDist == -1 || shortestDist > distance){
							shortestDist = distance;
							paths[source]=source+paths[i];
						}
					}
				}
			}
		}
		
		distances[source] = shortestDist;
		return shortestDist;
	}
	
	/**
	 * Get number of Islands which are surrounded by water
	 * water - 0
	 * land - 1
	 * @param islandGrid
	 * @return
	 */
	public static int getNumberOfIslands(int[][] islandGrid){
		int count = 0;
		for (int i=0;i<islandGrid.length;i++){
			for (int j=0;j<islandGrid[i].length;j++){
				if (islandGrid[i][j] == 1){
					count++;
					DFSTraverseInGrid(islandGrid, i, j);
				}
			}
		}
		return count;
	}

	private static void DFSTraverseInGrid(int[][] islandGrid, int i, int j) {
		// Mark node as visited with -1
		islandGrid[i][j] = -1;
		
		// check adjacent matrix grid nodes i.e move horizontal left and right keeping x=0 {0,1}, {0,-1}
		// Similarly move up and down in y direction keeping y coordinate as 0 {1,0}, {-1,0}
		int[][] directions = {{0,1}, {0,-1},{1,0}, {-1,0}};
		for (int[] dir : directions){
			int row = i+dir[0];
			int col = j+dir[1];
			//check if x and y are in matrix bounded lengths 
			if (row >= 0 && row<islandGrid[i].length && col >=0 && col <islandGrid.length && islandGrid[row][col] == 1){
				DFSTraverseInGrid(islandGrid, row, col);
			}
		}
		
	}
	
	/**
	 * Get number of Islands which are surrounded by water
	 * water - 0
	 * land - 1
	 * @param islandGrid
	 * @return
	 */
	public static int getNumberOfIslandsIterative(int[][] islandGrid){
		
		int count = 0;
		for (int i=0;i<islandGrid.length;i++){
			for (int j=0;j<islandGrid[i].length;j++){
				if (islandGrid[i][j] == 1){
					count++;
					Stack<int[]> stack = new Stack<int[]>();
					pushToStack(islandGrid, i, j, stack);
					while(true){
						if (stack.isEmpty())
							break;
						int[] currentNode = stack.pop();
						int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
						for (int[] dir : directions){
							int row = currentNode[0] + dir[0];
							int col = currentNode[1] + dir[1];
							if (row >= 0 && row<islandGrid[i].length && col >=0 && col <islandGrid.length && islandGrid[row][col] == 1){
								pushToStack(islandGrid, row, col, stack);
							}
						}
					}
				}
			}
		}
		return count;
		
	}

	private static void pushToStack(int[][] islandGrid, int i, int j,
			Stack<int[]> stack) {
		int[] currentNode = {i,j};
		// Mark as visited
		islandGrid[i][j] = -1;
		stack.push(currentNode);
	}
	
	
	
}
