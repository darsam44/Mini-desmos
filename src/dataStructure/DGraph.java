package dataStructure;


import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;


public class DGraph implements graph{
	private int MC=0;
	//this hash will represents the node
	Hashtable<Integer, node_data> Nodes;

	/*The hash represent an edges by Hashtable<node_data, Hashtable<Integer, edge_data>>   */
	Hashtable<node_data, Hashtable<Integer, edge_data>>  Edge;

	public DGraph() {
		Hashtable<Integer, node_data> Nodes = new Hashtable<Integer, node_data>();
		Hashtable<node_data, Hashtable<Integer, edge_data>>  Edge= new Hashtable<node_data, Hashtable<Integer, edge_data>>();
		this.MC=0;
	}

	@Override
	public node_data getNode(int key) {
		if (key != 0) {
			return Nodes.get(key);
		}
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		node_data Src = Nodes.get(src);
		edge_data edge = Edge.get(Src).get(dest);
		return edge;
	}

	@Override
	public void addNode(node_data n) {
		Nodes ver = (Nodes) n; 
		Nodes.put(n.getKey(), ver);
		MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		edge_data edge = new Edges(Nodes.get(src) , Nodes.get(dest), w);
		Hashtable<Integer, edge_data> table= new Hashtable<Integer, edge_data>();
		table.put(dest, edge);
		Edge.put(Nodes.get(src), table );
		MC++;
	}

	@Override
	public Collection<node_data> getV() {
		return Nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return 	Edge.get(Nodes.get(node_id)).values();
	}
	
	
	//finished
	@Override
	public node_data removeNode(int key) {
		/*we will run on the Hashtable of the edges and check for every edge if the source or the dest is 
		   the node of the key that we get*/
		node_data to_remove=Nodes.get(key); //the node that we need to remove
		Set <node_data> nodes = Edge.keySet();
		for (node_data node_data : nodes) {
			Edge.get(node_data).remove(key);
			//count eadge --
		}
		Edge.remove(to_remove);
		MC++;
		return Nodes.remove(key);
	}

	//the function isn't right
	@Override
	public edge_data removeEdge(int src, int dest) {
		node_data srcNode= Nodes.get(src); //get the node_data of src
		this.countEdge--;
		return Edge.get(srcNode).remove(dest); //remove the edge that start from src and ends in dest
		MC++;
	}

	@Override
	public int nodeSize() {
		return Nodes.size();
	}

	@Override
	public int edgeSize() {
		return this.countEdge;
	}

	@Override
	public int getMC() {
		return 0;
	}

}
