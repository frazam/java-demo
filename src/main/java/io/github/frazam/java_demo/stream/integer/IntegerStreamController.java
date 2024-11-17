package io.github.frazam.java_demo.stream.integer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stream/")
public class IntegerStreamController {

	@Autowired
	private IntegerStreamProcessor processor;

	@GetMapping("/numbers")
	public List<Integer> numbers(@RequestParam int max) {
		return processor.list(max);
	}

	@GetMapping("/numbers/odds")
	public List<Integer> numbersOdds(@RequestParam int max) {
		return processor.filterOdds(max);
	}

	@GetMapping("/numbers/even")
	public List<Integer> numberEven(@RequestParam int max) {
		return processor.filterEvens(max);
	}

	@GetMapping("/numbers/count")
	public Long numberCount(@RequestParam int max) {
		return processor.count(max);
	}
}
