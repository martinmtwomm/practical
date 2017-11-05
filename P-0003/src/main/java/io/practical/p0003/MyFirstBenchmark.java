package io.practical.p0003;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.openjdk.jmh.annotations.Benchmark;

public class MyFirstBenchmark {

	@Benchmark
	public void testMethod0() {

		List<Integer> list;
		list = new ArrayList<>(500_000);
		for (int i = 0; i < 500_000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 10_000; i++) {
			list.remove(i);
		}
		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			Integer number = iter.next();
			number++;
		}
	}

	@Benchmark
	public void testMethod1() {

		List<Integer> list;
		list = new LinkedList<Integer>();
		for (int i = 0; i < 500_000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 10_000; i++) {
			list.remove(i);
		}
		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			Integer number = iter.next();
			number++;
		}
	}
	@Benchmark
	public void testMethod2() {

		List<Integer> list;
		list = new Vector<Integer>(500_000);
		for (int i = 0; i < 500_000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 10_000; i++) {
			list.remove(i);
		}
		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			Integer number = iter.next();
			number++;
		}
	}
	@Benchmark
	public void testMethod3() {

		List<Integer> list;
		list = new Stack<Integer>();
		for (int i = 0; i < 500_000; i++) {
			list.add(i);
		}
		for (int i = 0; i < 10_000; i++) {
			list.remove(i);
		}
		for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
			Integer number = iter.next();
			number++;
		}
	}

}
