package cz.dev.logincredentialsmanagement.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, UUID> {

	@Query("select c from Credentials c where c.archiveConnectionId IS NULL")
	List<Credentials> findAllWhereArchiveConnectionIdIsNull();

	List<Credentials> findByArchiveConnectionId(UUID id);
}
