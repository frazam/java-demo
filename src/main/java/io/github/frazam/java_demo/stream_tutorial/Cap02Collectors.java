package io.github.frazam.java_demo.stream_tutorial;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

@Component
public class Cap02Collectors {

	// 3.1. Collectors.toList()
	public List<String> m3_1() {
		List<String> result = Arrays.asList("pippo", "pluto", "paperino") //
				.stream() //
				.collect(Collectors.toList());

		return result;
	}

	// 3.1.1. Collectors.toUnmodifiableList()
	public List<String> m3_1_1() {
		List<String> result = Arrays.asList("pippo", "pluto", "paperino") //
				.stream() //
				.collect(Collectors.toUnmodifiableList());

		// throws UnsupportedOperationException
		result.add("paperino2");

		return result;
	}

	// 3.2. Collectors.toSet()
	public Set<String> m3_2() {
		List<String> listWithDuplicates = Arrays.asList("a", "b", "c", "d", "b");
		Set<String> result = listWithDuplicates.stream() //
				.collect(Collectors.toSet());

		return result;
	}

	// 3.2.1. Collectors.toUnmodifiableSet()
	public Set<String> m3_2_1() {
		List<String> listWithDuplicates = Arrays.asList("a", "b", "c", "d", "b");
		Set<String> result = listWithDuplicates.stream() //
				.collect(Collectors.toUnmodifiableSet());

		// throws UnsupportedOperationException
		result.add("paperino2");

		return result;
	}

	// 3.3. Collectors.toCollection()
	public List<String> m3_3() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		List<String> result = givenList.stream() //
				.collect(Collectors.toCollection(LinkedList::new));
		return result;
	}

	// 3.4. Collectors.toMap()
	public Map<String, Integer> m3_4() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
//		Map<String, Integer> result = givenList.stream() //
//				.collect(Collectors.toMap(Function.identity(), String::length));
		Map<String, Integer> result = givenList.stream() //
				.collect(Collectors.toMap(i -> i, String::length));
		return result;
	}

	public Map<Integer, String> m3_4_v2() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Map<Integer, String> result = IntStream.range(0, givenList.size()) // Generate indices
				.boxed() //
				.collect(Collectors.toMap(Function.identity(), // or i -> i
						givenList::get));
		return result;
	}

	// 3.4.1. Collectors.toUnmodifiableMap()
	public Map<String, Integer> m3_4_1() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Map<String, Integer> result = givenList.stream() //
				.collect(Collectors.toUnmodifiableMap(Function.identity(), String::length));

		result.put("foo", 3);
		return result;
	}

	// 3.5. Collectors.collectingAndThen()
	public List<String> m3_5() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		List<String> result = givenList.stream() //
				.collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
		return result;
	}

	// 3.6. Collectors.joining()
	public String m3_6() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		String result = givenList.stream() //
//				.collect(Collectors.joining());
//				.collect(Collectors.joining(" "));
				.collect(Collectors.joining(" ", "PRE-", "-POST"));

		return result;
	}

	// 3.7. Collectors.counting()
	public Long m3_7() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Long result = givenList.stream() //
				.collect(Collectors.counting());

		return result;
	}

	// 3.8. Collectors.summarizingDouble/Long/Int()
	public DoubleSummaryStatistics m3_8() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		DoubleSummaryStatistics result = givenList.stream() //
				.collect(Collectors.summarizingDouble(String::length));

		return result;
	}

	// 3.9. Collectors.averagingDouble/Long/Int()
	public Double m3_9() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Double result = givenList.stream() //
				.collect(Collectors.averagingDouble(String::length));

		return result;
	}

	// 3.10. Collectors.summingDouble/Long/Int()
	public Double m3_10() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Double result = givenList.stream() //
				.collect(Collectors.summingDouble(String::length));

		return result;
	}

	// 3.11. Collectors.maxBy()/minBy()
	public Optional<String> m3_11() {
//		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino", "zzzz");
		List<String> givenList = Arrays.asList();

		Optional<String> result = givenList.stream() //
				.collect(Collectors.maxBy(Comparator.naturalOrder()));

		return result;
	}

	// 3.12. Collectors.groupingBy()
	public Map<Integer, Set<String>> m3_12() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Map<Integer, Set<String>> result = givenList.stream() //
				.collect(Collectors.groupingBy(String::length, Collectors.toSet()));

		return result;
	}

	// 3.13. Collectors.partitioningBy()
	public Map<Boolean, Set<String>> m3_13() {
		List<String> givenList = Arrays.asList("pippo", "pluto", "paperino");
		Map<Boolean, Set<String>> result = givenList.stream() //
				.collect(Collectors.partitioningBy(s -> s.length() % 2 == 0, Collectors.toSet()));

		return result;
	}

	// 3.14. Collectors.teeing()
	public List<Integer> m3_14() {
		List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
		List<Integer> results = numbers.stream() //
				.collect(Collectors.teeing( //
						Collectors.minBy(Integer::compareTo), //
						Collectors.maxBy(Integer::compareTo), //
						(min, max) -> Arrays.asList(min.orElseThrow(), max.orElseThrow()) //
				));

		return results;

	}

}
