package cz.dev.logincredentialsmanagement.credentials;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
