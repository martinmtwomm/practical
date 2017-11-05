package io.practical.p0007;

import org.junit.Test;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class P0007Test {
	@Test
	public void dotest() throws Exception {
		// @formatter:off
		Options opt = new OptionsBuilder().include(Bench.class.getSimpleName()).forks(1).addProfiler(GCProfiler.class).build();
		// @formatter:on

		new Runner(opt).run();
	}
	// public static void main(String[] args) {
	// new XmlParserSax("lineitem.xml");
	// new XmlDomParser("lineitem.xml");
	// new XmlStaxParser("lineitem.xml");
	//
	// new XmlParserSax("styles.xml");
	// new XmlDomParser("styles.xml");
	// new XmlStaxParser("styles.xml");
	// }
	// XML WITHOUT NAMESPACE :

	// nation.xml -> 4.5ko
	// reed.xml -> 285Ko
	// ordres.xml -> 5,378Ko
	// lineitems.xml -> 32,295 Ko

	// XML WITH NAMESPACE :

	// slide3.xml -> 11ko
	// document.xml -> 6,731ko
	// styles.xml -> 162.6 kb

}
