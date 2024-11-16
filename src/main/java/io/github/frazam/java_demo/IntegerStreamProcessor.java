package io.github.frazam.java_demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class IntegerStreamProcessor {

	private Stream<Integer> generateStream(int maxSize) {
		return Stream.iterate(0, n -> n + 1) //
				.limit(maxSize);
	}

	public List<Integer> list(int maxSize) {
		return generateStream(maxSize) //
				.collect(Collectors.toList());
	}

	public List<Integer> filterOdds(int maxSize) {
		return generateStream(maxSize) //
				.filter(num -> num % 2 != 0) //
				.collect(Collectors.toList());
	}

	public List<Integer> filterEvens(int maxSize) {
		return generateStream(maxSize) //
				.filter(num -> num % 2 == 0) //
				.collect(Collectors.toList());
	}

	public Long count(int maxSize) {
		return generateStream(maxSize) //
				.reduce(0L, (count, element) -> count + 1, Long::sum);
	}

}
