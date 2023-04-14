package cz.dev.logincredentialsmanagement.credentials;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/credentials")
public class CredentialsController {

	private final CredentialsService credentialsService;

	@GetMapping
	public List<Credentials> getAllCredentials() {
		return credentialsService.getAllCredentials();
	}

	@GetMapping("/{id}")
	public Optional<Credentials> getCredentials(@PathVariable("id") UUID id) {
		return credentialsService.getCredentials(id);
	}

	@GetMapping("/{id}/archive")
	public List<Credentials> getCredentialsArchive(@PathVariable("id") UUID id) {
		return credentialsService.getCredentialsArchive(id);
	}

	@PostMapping
	public void addCredentials(@RequestBody Credentials credentials) {
		credentialsService.addCredentials(credentials);
	}
}
