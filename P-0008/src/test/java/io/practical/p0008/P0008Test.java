package io.practical.p0008;

import org.junit.Test;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class P0008Test {
	 @Test
	 public void dotest() throws Exception {
	 // @formatter:off
	 Options opt = new
	 OptionsBuilder().include(Bench.class.getSimpleName()).forks(1).addProfiler(GCProfiler.class).build();
	 // @formatter:on
	
	 new Runner(opt).run();
	 }
//	public static void main(String[] args) {
//		// new SchemaValidator("xml/cl_Sesion56_2.xml", "xsd/akomantoso30.xsd");
//		// new SchemaValidator2("xml/cl_Sesion56_2.xml", "xsd/akomantoso30.xsd");
//		// new SchemaValidator3("xml/cl_Sesion56_2.xml", "xsd/akomantoso30.xsd");
//
//		new SchemaValidator("xml/cl_Sesion56_2.xml", "xsd/akomantoso30.xsd");
//		new SchemaValidator("xml/eu_COM(2013)0619_EN-8.xml", "xsd/akomantoso30.xsd");
//		new SchemaValidator("xml/it_senato_ddl_2013.xml", "xsd/akomantoso30.xsd");
//		new SchemaValidator("xml/us_Title9-Chap3-eng.xml", "xsd/akomantoso30.xsd");
//		new SchemaValidator("xml/za_Judgement_2008-11-26.xml", "xsd/akomantoso30.xsd");
//
//		
//		new SchemaValidator("xml/cl_Sesion56_2.xml", "xsd/xml.xsd");
//		new SchemaValidator("xml/eu_COM(2013)0619_EN-8.xml", "xsd/xml.xsd");
//		new SchemaValidator("xml/it_senato_ddl_2013.xml", "xsd/xml.xsd");
//		new SchemaValidator("xml/us_Title9-Chap3-eng.xml", "xsd/xml.xsd");
//		new SchemaValidator("xml/za_Judgement_2008-11-26.xml", "xsd/xml.xsd");
//	}

}
