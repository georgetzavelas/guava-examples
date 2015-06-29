package com.google.guava.example;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;

public class CollectionNewTypes {

	@SuppressWarnings("unused")
    public static void usingHashMapsAndSets() {
	    // same as: new HashMap<String, Integer>()
		Map<String, Integer> myMap = Maps.newHashMap();
		// same as: new HashSet<Integer>();
		Set<Integer> mySet = Sets.newHashSet();
		// same as: new ArrayList<ConcurrentHashMap<String, Integer>>();
		List<ConcurrentHashMap<String, Integer>> myList = Lists.newArrayList();

		// threadsafe, constant and more memory efficient
		ImmutableMap<String, Integer> numbersMap = new ImmutableMap.Builder<String,Integer>()
				.put("one", 1)
				.put("two", 2)
				.put("three", 3)
				.put("four", 4)
				.build();
		ImmutableSet<String> colours = ImmutableSet.of("blue", "orange", "yellow", "green", "red", "purple");
		// avoid copying data
		ImmutableList<String> listOfColours = ImmutableList.copyOf(colours);

	}

	public static void usingMultiset() {
	    // set where muliple items can appear more than once
		Multiset<String> multiset = HashMultiset.create();
		multiset.add("nothing");
		multiset.add("nothing");
		multiset.add("nothing", 2);
		System.out.println("count = " + multiset.count("nothing") + ", should be = 4");
		multiset.remove("nothing", 3);
		System.out.println("count = " + multiset.count("nothing") + ", should be = 1");
		multiset.setCount("nothing", 5);
		System.out.println("count = " + multiset.count("nothing") + ", should be = 5");
	}

	@SuppressWarnings("unused")
	public static void usingMultimap() {
	    // map where key can have mulitple values
		Multimap<String, String> multimap = ArrayListMultimap.create();
		multimap.put("Poland", "Warsaw");
		multimap.put("Poland", "Cracow");
		multimap.put("Poland", "Plock");
		multimap.put("Poland", "Gdansk");
		multimap.put("Germany", "Berlin");
		multimap.put("Germany", "Brenen");
		multimap.put("Germany", "Dortmund");
		// return a view, not a copy!
		Collection<String> germanCities = multimap.get("Germany");
		// return a view with two keys "Poland" and "Germany" and a collection of cities associated with each
		Map<String, Collection<String>> countryCities = multimap.asMap();
		// return a view with all the values as a flattened collection
		Collection<String> cities = multimap.values();
	}

	@SuppressWarnings("unused")
    public static void usingBiMap() {
	    // bi-directional map instead of maintaining two maps, values must also be unique
	    BiMap<String, Integer> userId = HashBiMap.create();
	    userId.put("Bob", 42);
	    String user = userId.inverse().get("Bob");
	}

    public static void usingTable() {
	    String v1 = "0";
	    String v2 = "0";
	    String v3 = "1";

	    // map with two keys
	    Table<String, String, Integer> weightedGraph = HashBasedTable.create();
	    weightedGraph.put(v1, v2, 4);
	    weightedGraph.put(v1, v3, 20);
	    weightedGraph.put(v2, v3, 5);

	    weightedGraph.row(v1); // returns a Map mapping v2 to 4, v3 to 20
	    weightedGraph.column(v3); // returns a Map mapping v1 to 20, v2 to 5
	}

    public void usingRange() {
        Range<Integer> range = Range.closed(2,20);
        Range<Integer> rangeWithRightOpen = Range.closedOpen(2,20);
        Range<Integer> smallerRange = Range.closed(4,10);

        range.contains(20); // true
        rangeWithRightOpen.contains(20); // false
        range.encloses(smallerRange); // true
        range.intersection(smallerRange); // true
        range.span(smallerRange); // true
        range.containsAll(Lists.newArrayList(3,4,5,6,7,8)); // true
    }
}
