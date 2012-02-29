
public class Pair {
	private int freq;
	private MarkovNode node;
	
	public Pair(MarkovNode n) {
		node = n;
		freq = 1;
	}
	public void increaseFreq() {
		freq++;
	}
	public int getFreq() {
		return freq;
	}
	public MarkovNode getNode() {
		return node;
	}
	
	
}
