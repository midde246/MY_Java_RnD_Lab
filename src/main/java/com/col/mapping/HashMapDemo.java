package com.col.mapping;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
	public static void main(String[] args) {
		Map<Person, Integer> mapData = new HashMap<>();

		mapData.put(new Person("Santu Jana", "1"), 1);
		mapData.put(new Person("Surath Dey", "2"), 1);
		mapData.put(new Person("Sarada Prasad Midde", "3"), 1);
		mapData.put(new Person("Sarada Prasad Midde", "3"), 1);

		System.out.println(mapData.size());
	}
}
