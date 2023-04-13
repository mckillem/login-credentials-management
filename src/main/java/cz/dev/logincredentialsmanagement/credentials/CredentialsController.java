package cz.dev.logincredentialsmanagement.credentials;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/credentials")
public class CredentialsController {

	private final CredentialsService credentialsService;

	@GetMapping
	public List<Credentials> getAllCredentials() {
		return credentialsService.getAllCredentials();
	}

	@PostMapping
	public void addCredentials(@RequestBody Credentials credentials) {
		credentialsService.addCredentials(credentials);
	}
}
