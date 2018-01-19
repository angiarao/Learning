package angia.ds.algos.graph;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class UnionFindImpl{
	
	HashMap<String,Props> map = null;
	
	public UnionFindImpl(List<String> nodes){
		map = new HashMap<String, Props>();
		nodes.forEach(new Consumer<String>() {
			public void accept(String key) {
				map.put(key, new Props(key,0));
			}
		});
	}
	
	public String root(String node){
		if(map.get(node).parent.equals(node)) return node;
		map.get(node).parent = root(map.get(node).parent);
		return map.get(node).parent;
	}
	
	public boolean connected(String v1, String v2){
		return root(v1).equals(root(v2));
	}
	
	public void union(String v1, String v2){
		String r1 = root(v1);
		String r2 = root(v2);
		if(r1.equals(r2)) return;
		if(map.get(r1).rank>map.get(r2).rank){
			map.get(r2).parent=r1;
			map.get(r1).rank+=map.get(r2).rank;
		}else{
			map.get(r1).parent=r2;
			map.get(r2).rank+=map.get(r1).rank; 
		}
		
	}
	
	private class Props{
		String parent;
		int rank;
		public Props(String parent, int rank){
			this.parent = parent;
			this.rank = rank;
		}
		
	}
}
