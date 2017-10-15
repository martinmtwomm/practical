package io.practical.p0003;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ListTypeFour {
	List<Integer> list;

	public ListTypeFour() {
		list = new Stack<>();
	}

	public void process() {

		insert();
		remove();
		iterator();

	}

	private void insert() {
		for (int i = 0; i < 500_000; i++) {
			list.add(i);
		}
	}

	private void remove() {
		for (int i = 0; i < 10_000; i++) {
			list.remove(i);
		}

	}

	private void iterator() {
		Iterator<Integer> iteratorList = list.iterator();
		while (iteratorList.hasNext()) {
		}
	}

	public static void main(String[] args) {

	}
}
