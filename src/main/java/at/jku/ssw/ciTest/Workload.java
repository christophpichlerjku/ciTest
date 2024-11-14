package at.jku.ssw.ciTest;

import java.util.Random;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public class Workload {
	static final int ARRAY_SIZE = 100_000;
	int[] data;
	private final Random random = new Random(1);

	@Setup
	public void init() {
		data = new int[ARRAY_SIZE];
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt();
		}
	}

	@TearDown
	public void check() {
		for (int i = 1; i < data.length; i++) {
			if (data[i] < data[i - 1]) {
				throw new IllegalArgumentException("Arrays not sorted at indices %d/%d".formatted(i - 1, i));
			}
		}
	}
}