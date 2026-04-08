package com.example.backend.controller;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.User;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
private List<User> users=List.of(
		new User("1", "Rahul", "rahul@gmail.com", "active", LocalDate.of(2024, 10, 9)),
		new User("2", "Anjali", "anjali@gmail.com", "inactive", LocalDate.of(2022, 11, 12)),
		new User("3", "Ram", "ram@gmail.com", "active", LocalDate.of(2023, 8, 2)),
		new User("4", "Sita", "sita@gmail.com", "inactive", LocalDate.of(2024, 9, 06)),
		new User("5", "Gita", "gita@gmail.com", "active", LocalDate.of(2023, 3, 05)),
		new User("6", "Mihir", "mihir@gmail.com", "inactive", LocalDate.of(2022, 1, 07)),
		new User("7", "Usha", "usha@gmail.com", "active", LocalDate.of(2024, 12, 06)),
		new User("8", "Nisha", "nisha@gmail.com", "inactive", LocalDate.of(2022, 9, 12)),
		new User("9", "Shreya", "shreya@gmail.com", "active", LocalDate.of(2023, 7, 11)),
		new User("10", "Aditya", "aditya@gmail.com", "inactive", LocalDate.of(2024, 2, 7))
		);

@GetMapping
public List<User> getUsers(
        @RequestParam(required = false) String status,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String order
) {

    Stream<User> stream = users.stream();

    if (status != null) {
        if (!status.equals("active") && !status.equals("inactive")) {
            throw new RuntimeException("Invalid status (use active/inactive)");
        }
        stream = stream.filter(u -> u.getStatus().equals(status));
    }

    
    Comparator<User> comparator;

    switch (sortBy) {
        case "name":
            comparator = Comparator.comparing(User::getName);
            break;
        case "email":
            comparator = Comparator.comparing(User::getEmail);
            break;
        case "joinedAt":
            comparator = Comparator.comparing(User::getJoinedAt);
            break;
        default:
            comparator = Comparator.comparing(User::getId);
    }

    if (order.equals("desc")) {
        comparator = comparator.reversed();
    }

    return stream.sorted(comparator).toList();
}

@PostMapping("/tagSummary")
public Map<String, Object> tagSummary(@RequestBody Map<String, Object> body) {

    long start = System.currentTimeMillis();

    List<Map<String, Object>> inputUsers =
            (List<Map<String, Object>>) body.get("users");

    if (inputUsers == null) {
        throw new RuntimeException("Invalid input");
    }

    Map<String, Integer> tagCount = new HashMap<>();

    for (Map<String, Object> user : inputUsers) {
        List<String> tags = (List<String>) user.get("tags");

        if (tags == null) continue;

        for (String tag : tags) {
            tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
        }
    }

    List<Map<String, Object>> summary = tagCount.entrySet()
            .stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .map(e -> {
            	Map<String, Object>map = new HashMap<>();
            	map.put("tag", e.getKey());
            	map.put("count", e.getValue());
            	return map;
            }
          )
            .toList();

    long duration = System.currentTimeMillis() - start;

    return Map.of(
            "summary", summary,
            "processedCount", inputUsers.size(),
            "durationMs", duration
    );
    
}
}