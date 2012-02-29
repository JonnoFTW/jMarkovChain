import java.util.ArrayList;
import java.util.Random;

public class MarkovNode {
	private ArrayList<Pair> nodes;
	private String s;
	private Random r;
	public MarkovNode(String s) {
		this.r = new Random();
		this.s = s;
		this.nodes = new ArrayList<Pair>(0);
	}
	public MarkovNode nextNode() {
		int s= nodes.size();
		if(s == 0)
			return null;
	//	int idx = r.nextInt(s);
	//	System.out.println("using node at "+idx);
	//	return nodes.get(idx).getNode();
		
		int sub = 0;
		int total = 0;
		
		for (Pair p : nodes) {
			total += p.getFreq();
		}
		int c = r.nextInt(total);
		for (Pair p : nodes) {
			sub += p.getFreq();
			if (c < sub)
				return p.getNode();
		}
		System.out.println("got here");
		return null;
	}
	public String getText() {
		return s;
	}
	public ArrayList<Pair> getList() {
		return nodes;
	}
	public void addNode(MarkovNode n){
		for (Pair p : nodes) {
			if(p.getNode().getText().equals(n.getText())) {
				p.increaseFreq();
				return;
			}
		}
		// Otherwise add the node the list of possible nexts
		nodes.add(new Pair(n));
	}

}