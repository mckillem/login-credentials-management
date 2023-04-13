package cz.dev.logincredentialsmanagement.credentials;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Credentials {

	@Id
	private UUID id;
	private String url;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	private UUID archiveConnectionId;

	public static Credentials addNew(Credentials credentials) {
		return Credentials
				.builder()
				.id(UUID.randomUUID())
				.url(credentials.getUrl())
				.email(credentials.getEmail())
				.password(credentials.getPassword())
				.createdAt(LocalDateTime.now())
				.build();
	}
}
