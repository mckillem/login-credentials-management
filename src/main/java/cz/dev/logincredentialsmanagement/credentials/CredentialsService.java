package cz.dev.logincredentialsmanagement.credentials;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CredentialsService {

	private final CredentialsRepository credentialsRepository;

	public List<Credentials> getAllCredentials() {
		return credentialsRepository.findAllWhereArchiveConnectionIdIsNull();
	}

	public Optional<Credentials> getCredentials(UUID id) {
		return credentialsRepository.findById(id);
	}

	public void addCredentials(Credentials credentials) {
		if (credentials.getId() == null) {
			credentialsRepository.save(Credentials.addNew(credentials));
		} else {
			UUID oldId = credentials.getId();
			Credentials newCredentials = Credentials.addNew(credentials);
			UUID newId = newCredentials.getId();
			credentialsRepository.save(newCredentials);

			Optional<Credentials> oldCredentials = getCredentials(oldId);

			if (oldCredentials.isPresent()) {
//				todo: jak přesně řádky pod tímhle fungují?
				credentialsRepository.save(oldCredentials.get()
						.toBuilder()
						.archiveConnectionId(newId)
						.build());
			}
		}

	}
}
