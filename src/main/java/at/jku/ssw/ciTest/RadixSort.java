package at.jku.ssw.ciTest;

public class RadixSort {
	private static final int BIT_SIZE = 8;
	private static final int N_BUCKETS = 1 << BIT_SIZE;

	public static void sort(double[] data) {
		Node first = null;
		Node last = null;
		first = last = new Node(new Tuple(Double.doubleToLongBits(data[0]), data[0]));
		for (int i = 1; i < data.length; i++) {
			last.next = new Node(new Tuple(Double.doubleToLongBits(data[i]), data[i]));
			last = last.next;
		}
		for (int i = 0; i < Long.SIZE / BIT_SIZE; i++) {
			first = sort(first, i);
		}
		int i = 0;
		for (Node n = first; n != null; n = n.next) {
			if (!n.dummy) {
				data[i++] = n.tuple.dval;
			}
		}
	}

	private static Node sort(Node oldFirst, int index) {
		Node[] firstNodes = new Node[N_BUCKETS];
		Node[] lastNodes = new Node[N_BUCKETS];

		for (; oldFirst != null; oldFirst = oldFirst.next) {
			if (!oldFirst.dummy) {
				final int s = (int) ((oldFirst.tuple.rval >> (index * BIT_SIZE)) & ((1 << BIT_SIZE) - 1));
				if (firstNodes[s] == null) {
					firstNodes[s] = lastNodes[s] = oldFirst;
				} else {
					lastNodes[s].next = oldFirst;
					lastNodes[s] = lastNodes[s].next;
				}
			}
		}
		Node dummy = Node.dummy();
		Node last = dummy;
		for (int i = 0; i < firstNodes.length; i++) {
			if (firstNodes[i] == null) {
				firstNodes[i] = lastNodes[i] = Node.dummy();
			}
			last.next = firstNodes[i];
			last = lastNodes[i];
		}
		last.next = null;
		return dummy.next;
	}

	private static record Tuple(long rval, double dval) {
	}

	private static class Node {
		final boolean dummy;
		Node next;
		final Tuple tuple;

		private Node(Tuple tuple, boolean dummy) {
			this.tuple = tuple;
			this.dummy = dummy;
		}

		public Node(Tuple tuple) {
			this(tuple, false);
		}

		public static Node dummy() {
			return new Node(null, true);
		}
	}
}
