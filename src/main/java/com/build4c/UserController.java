package com.build4c;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
public class UserController {

	@GetMapping("/get-user")
	public List<User> getAllUsers() {
		return IntStream.range(1, 100).mapToObj(i -> new User(i, "First_" + i, "Last_" + i))
				.collect(Collectors.toList());
	}

	@GetMapping("/get-user-rest")
	public List<User> getAllUsersInRestTemplate() {
		ResponseEntity<List<User>> responseEntity = new RestTemplate()
				.exchange(
						"http://localhost:8081/get-user",
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<List<User>>() {}
				);
		List<User> users = responseEntity.getBody();
		return users;
	}

	
	
}
