package cz.dev.logincredentialsmanagement.credentials;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CredentialsService {

	private final CredentialsRepository credentialsRepository;

	public List<Credentials> getAllCredentials() {
		return credentialsRepository.findAll();
	}
}
