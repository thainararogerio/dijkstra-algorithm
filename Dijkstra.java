
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	/* Graph:
	 *   s  v  w  t
	 * s 0  1  4    
	 * v    0  2  6 
	 * w       0  3
	 * t          0
	 * */
	public static void main(String[] args) {
		Integer [][] graph = new Integer [][]
				{
				{0,    1,    4, null},
				{null, 0,    2,    6},
				{null, null, 0,    3},
				{null, null, null, 0}
				};
		int origin = 0;
	
		Vertex d[] = new Vertex[graph.length]; //origin to v distance
		for (int i = 0; i < d.length; ++i)
		{
			d[i] = new Vertex(Integer.MAX_VALUE, false);
		}
		d[origin].distance = 0;
		
		int pi[] = new int[graph.length];
		
		int u = min(d);
		
		while(u != -1) {
			d[u].visited = true;
			
			ArrayList<Integer> neighbors = neighborsOf(u, graph);
			
			for (Integer v : neighbors) {
				if (d[v].distance > d[u].distance + graph[u][v])
				{
					d[v].distance = d[u].distance + graph[u][v];
					pi[v] = u;
				}
			}
			
			u = min(d);
		}
		
		int destiny = 3;
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		while(destiny != origin)
		{
			path.add(destiny);
			destiny = pi[destiny];
		}
		path.add(destiny);
		
		Collections.reverse(path);
		
		for (Integer vert : path) {
			System.out.println(vert + " - ");
		}
	}

	private static ArrayList<Integer> neighborsOf(int vertex,
			Integer[][] grafo) {
		if (vertex < 0 || vertex >= grafo.length)
			return null;
		
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for (int i = 0; i < grafo[vertex].length; i++) {
			if (i == vertex)
				continue;
			
			if (grafo[vertex][i] != null)
				neighbors.add(i);
		}
		
		return neighbors;
	}

	private static int min(Vertex[] d) {
		int menorD = Integer.MAX_VALUE;
		int vert = -1;
		
		for (int i = 0; i < d.length; i++) {
			if (d[i].distance < menorD && !d[i].visited)
			{
				menorD = d[i].distance;
				vert = i;
			}
		}	
		
		return vert;
	}
}
