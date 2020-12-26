
public class TESTDRIVER {
	public static final Character A = new Character('A');
	public static final Character B = new Character('B');
	public static final Character C = new Character('C');
	public static final Character Z = new Character('Z');
	
	public static int recursionCount(SLLNode n) {
		if(n.getNext() == null) {
		return 1;}
		
		int count = 1+ recursionCount(n.getNext());
		return count;
		
		}
	
	public static <T> void main(String[] args) {
		SLLNode<T> node1 = new SLLNode<T>((T)A);
		SLLNode<T> node2 = new SLLNode<T>((T)B);
		SLLNode<T> node3 = new SLLNode<T>((T)C);
		SLLNode<T> node4 = new SLLNode<T>((T)Z);
		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		System.out.println(recursionCount(node1));
	}

}
