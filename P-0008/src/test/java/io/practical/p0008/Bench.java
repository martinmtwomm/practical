package io.practical.p0008;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class Bench {

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench001() {
		new SchemaValidator("xml/cl_Sesion56_2.xml", "xsd/akomantoso30.xsd");
	}
	
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench002() {
		new SchemaValidator("xml/eu_COM(2013)0619_EN-8.xml", "xsd/akomantoso30.xsd");
	}
	
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench003() {
		new SchemaValidator("xml/it_senato_ddl_2013.xml", "xsd/akomantoso30.xsd");
	}
	
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench004() {
		new SchemaValidator("xml/us_Title9-Chap3-eng.xml", "xsd/akomantoso30.xsd");
	}
	
	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void bench005() {
		new SchemaValidator("xml/za_Judgement_2008-11-26.xml", "xsd/akomantoso30.xsd");
	}

}
