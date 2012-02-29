import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	static TreeMap<String, MarkovNode> nodes;
	public static void main(String[] args) throws FileNotFoundException {
		// Make the graph from the input file
		System.out.println("Running");
		nodes = new TreeMap<String, MarkovNode>();
		Scanner in = new Scanner(new FileReader(
				"4300.txt"));
		String[] words = {null,null,null};
		// Oldest at 0
		words[0] = in.next();
		words[1] = in.next();
		words[2] = in.next();
		String old = words[0] +" " + words[1];
		while (in.hasNext()) {
				
				

			// If the node is not in the graph, add it
			String current = words[1] +" " + words[2];
			if (!nodes.containsKey(current)) {
		//		System.out.println("Adding '"+current+"'");
				nodes.put(current, new MarkovNode(current));
			} else {
				// Otherwise, get the node corresponding to the triple before
				// it, and add it to its list
			//	System.out.println("To '"+nodes.get(old).getText()+"' adding '"+current+"'");
				nodes.get(old).addNode(nodes.get(current));				
			}
			old = current;

			words[0] = words[1];
			words[1] = words[2];
			words[2] = in.next();
			// Shuffle down
			
		}
		in = new Scanner(System.in);
		//System.out.println(nodes);
		System.out.println("Enter your search, 2 words");
		while(in.hasNext()) {
			
			String s = in.nextLine();
			if(s.equals("!random")) {
				Object[] values =nodes.keySet().toArray();
				Random gen = new Random();
				System.out.println(randWalk((String) values[gen.nextInt(values.length)],30));
				
			}
			// Should pick 2 random consecutive words and see if they are in
			else if(nodes.containsKey(s)) {
				// Node found
				
				System.out.println(randWalk(s,30));
				

				System.out.println("\n----------------------------\n");
			} else {
				System.out.println("No word!");
			}
			
			
		}
	}
	private static String randWalk(String startKey, int words) {
		String out = "";
		String s= startKey;
		for (int i = 0; i < words; i++) {
			out += s.split(" ")[0]+" ";
			MarkovNode n = nodes.get(s).nextNode();
			if(n == null) {
				i--;
				
			}
			else {
				
				s = n.getText();
//				
//				System.out.println("Neighbours of "+ s +"  are:");
//				for (Pair p : nodes.get(s).getList()) {
//					System.out.println(p.getNode().getText()+':'+p.getFreq());
//				}
			}
			
		}
		return out;
	}

}

