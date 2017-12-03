package io.practical.p0005;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;



public class P0005Test {
	@Test
	public void test() throws RunnerException  {
		
		// @formatter:off
		Options opt = new OptionsBuilder().include(P0005.class.getSimpleName()).forks(1).build();
		// @formatter:on

		new Runner(opt).run();
	}
}
