package at.jku.ssw.ciTest;

public class QuickSortS {

	public static void sort(double[] data) {
		sort(data, 0, data.length - 1);
	}

	private static void sort(double[] data, int low, int high) {
		if (low == high) {
			return;
		}
		if (data[low] > data[high]) {
			swap(data, low, high);
		}
		if (low + 1 == high) {
			return;
		}
		int k = (low + high) / 2;
		if (data[low] > data[k]) {
			swap(data, low, k);
		}
		if (data[k] > data[high]) {
			swap(data, k, high);
		}
		if (low + 2 == high) {
			return;
		}
		double pivot = data[k];
		int i = low + 1;
		int j = high - 1;
		while (i <= j) {
			while (i < high && data[i] <= pivot) {
				i++;
			}
			while (j > low && data[j] >= pivot) {
				j--;
			}
			if (i < j) {
				swap(data, i, j);
				i++;
				j--;
			}
		}
		sort(data, low, j);
		sort(data, i, high);
	}

	private static void swap(double[] data, int a, int b) {
		double tmp = data[a];
		data[a] = data[b];
		data[b] = tmp;
	}

}
