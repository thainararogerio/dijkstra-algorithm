package tcc.algoritmo.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstraSimples {

	/*   s  v  w  t
	 * s 0  1  4    
	 * v    0  2  6 
	 * w       0  3
	 * t          0
	 * */
	public static void main(String[] args) {
		Integer [][] grafo = new Integer [][]
				{
				{0,    1,    4, null},
				{null, 0,    2,    6},
				{null, null, 0,    3},
				{null, null, null, 0}
				};
		int origem = 0;
	
		VerticeSimples d[] = new VerticeSimples[grafo.length]; //distância da origem ao v
		for (int i = 0; i < d.length; ++i)
		{
			d[i] = new VerticeSimples(Integer.MAX_VALUE, false);
		}
		d[origem].distancia = 0;
		
		int pi[] = new int[grafo.length];
		
		int u = menor(d);
		
		while(u != -1) {
			d[u].visitado = true;
			
			ArrayList<Integer> vizinhos = vizinhosDe(u, grafo);
			
			for (Integer v : vizinhos) {
				if (d[v].distancia > d[u].distancia + grafo[u][v])
				{
					d[v].distancia = d[u].distancia + grafo[u][v];
					pi[v] = u;
				}
			}
			
			u = menor(d);
		}
		
		int destino = 3;
		
		ArrayList<Integer> caminho = new ArrayList<Integer>();
		while(destino != origem)
		{
			caminho.add(destino);
			destino = pi[destino];
		}
		caminho.add(destino);
		
		Collections.reverse(caminho);
		
		for (Integer vert : caminho) {
			System.out.println(vert + " - ");
		}
	}

	private static ArrayList<Integer> vizinhosDe(int vertice,
			Integer[][] grafo) {
		if (vertice < 0 || vertice >= grafo.length)
			return null;
		
		ArrayList<Integer> vizinhos = new ArrayList<Integer>();
		for (int i = 0; i < grafo[vertice].length; i++) {
			if (i == vertice)
				continue;
			
			if (grafo[vertice][i] != null)
				vizinhos.add(i);
		}
		// TODO Auto-generated method stub
		return vizinhos;
	}

	private static int menor(VerticeSimples[] d) {
		int menorD = Integer.MAX_VALUE;
		int vert = -1;
		
		for (int i = 0; i < d.length; i++) {
			if (d[i].distancia < menorD && !d[i].visitado)
			{
				menorD = d[i].distancia;
				vert = i;
			}
		}	
		
		return vert;
	}
}
