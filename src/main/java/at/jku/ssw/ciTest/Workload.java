package at.jku.ssw.ciTest;

import java.util.Random;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Benchmark)
public class Workload {
	@Param(value = {"10000","100000","1000000"})
	private int arraySize;

	private final Random random = new Random(1);
	double[] data;

	@Setup
	public void init() {
		data = new double[arraySize];
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextDouble();
		}
	}

	@TearDown
	public void check() {
		for (int i = 1; i < data.length; i++) {
			if (data[i]<data[i-1]) {
				throw new IllegalArgumentException("Arrays not sorted at indices %d/%d".formatted(i - 1, i));
			}
		}
	}
}