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
@Builder
public class Credentials {

	@Id
	private UUID id = UUID.randomUUID();
	private String url;
	private String email;
	private String password;
	private LocalDateTime createdAt;
	//TODO: jaký typ tu bude? Potřebuju to tu teď?
//	private createdBy;
	private Long archiveConnectionId;
}
