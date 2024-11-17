package io.github.frazam.java_demo.stream.tutorial;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stream/tutorial")
public class DynamicController {

	private final Map<String, Object> processors = new HashMap<>();

	@Autowired
	public DynamicController(ApplicationContext context) {
		processors.put("cap01", context.getBean(Cap01Intro.class));
		processors.put("cap02", context.getBean(Cap02Collectors.class));
	}

	@GetMapping("/{processorName}/{methodName}")
	public ResponseEntity<?> invokeMethod(@PathVariable String processorName, @PathVariable String methodName) {
		Object processor = processors.get(processorName);
		if (processor == null) {
			return ResponseEntity.badRequest().body("No such processor: " + processorName);
		}

		try {
			Method method = processor.getClass().getMethod(methodName);
			Object result = method.invoke(processor);
			return ResponseEntity.ok(result);
		} catch (NoSuchMethodException e) {
			return ResponseEntity.badRequest()
					.body("No such method: " + methodName + " in processor: " + processorName);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error invoking method<br>" //
					+ "exception: " + e + "<br>" //
					+ "cause:" + e.getCause());
		}
	}
}
