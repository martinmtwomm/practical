package io.practical.p0007;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class Bench {

	private static final int ITERATIONS = 100_000;
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench001() {

		for (int i = 0; i < ITERATIONS; i++) {
			new XmlParserSax("nation.xml");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench002() {

		for (int i = 0; i < ITERATIONS; i++) {
			new XmlDomParser("nation.xml");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench003() {

		for (int i = 0; i < ITERATIONS; i++) {
			new XmlStaxParser("nation.xml");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench004() {

		for (int i = 0; i < ITERATIONS; i++) {
			new XmlParserSax("styles.xml");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench005() {

		for (int i = 0; i < ITERATIONS; i++) {
			new XmlDomParser("styles.xml");
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench006() {

		for (int i = 0; i < ITERATIONS; i++) {
			new XmlStaxParser("styles.xml");
		}
	}

}
