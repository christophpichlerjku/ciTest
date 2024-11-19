package at.jku.ssw.ciTest;

public class MaxHeap {
	public static final void sort(double[] data) {
		MaxHeap instance = new MaxHeap(data);
		instance.toMaxHeap();
		instance.toArray();
	}

	public final double[] data;
	private int count;

	private MaxHeap(double[] data) {
		this.data = data;
		this.count = 0;
	}

	private void toMaxHeap() {
		for(int i=0;i<data.length;i++) {
			count++;
			up(i);
		}
	}

	private void toArray() {
		for(int i=data.length-1;i>0;i--) {
			swap(0, i);//max is at i
			count--;
			down(0);
		}
	}
	
	private void up(int i) {
		int parent = parent(i);
		while(i>0 && data[i]>data[parent]) {
			swap(i, parent);
			i = parent;
			parent = parent(i);
		}
		
	}
	
	private void down(int i) {
		int left = leftChild(i);
		if(left>=count) {
			return;
		}
		if(left==count-1) {
			if(data[i]<data[left]) {
				swap(i, left);
			}
			return;
		}
		int right = rightChild(i);
		int largerChild = data[left] > data[right] ? left:right;
		if(data[i]<data[largerChild]) {
			swap(i, largerChild);
			down(largerChild);
		}
	}
	
	private void swap(int a, int b) {
		double tmp = data[a];
		data[a] = data[b];
		data[b] = tmp;
	}

	private static int parent(int i) {
		return (i - 1) / 2;
	}

	private static int leftChild(int i) {
		return 2*i+1;
	}

	private static int rightChild(int i) {
		return 2*i + 2;
	}

}
