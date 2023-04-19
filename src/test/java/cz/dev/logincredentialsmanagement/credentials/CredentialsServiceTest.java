package cz.dev.logincredentialsmanagement.credentials;

import cz.dev.logincredentialsmanagement.mock.credentials.CredentialsMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialsServiceTest {

	@InjectMocks
	private CredentialsService credentialsService;
	@Mock
	private CredentialsRepository credentialsRepository;

// todo: zobrazí všechny záznamy, které nejsou archiv getAllCredentials()
	@Test
	void getAllCredentialsWithArchiveConnectionIdIsNull() {
		// Given
		List<Credentials> all = CredentialsMock.getAll();

		when(credentialsRepository.findAllWhereArchiveConnectionIdIsNull())
				.thenReturn(all.stream().filter(a -> a.getArchiveConnectionId() == null).collect(Collectors.toList()));

		// When
		List<Credentials> foundCredentials = credentialsService.getAllCredentials();

		// Then
		assertThat(foundCredentials, is(not(nullValue())));
		verify(credentialsRepository).findAllWhereArchiveConnectionIdIsNull();
	}

//	todo: zobrazí záznam dle id getCredentials()
	@Test
	void getCredentials() {
		// Given
		Credentials all = CredentialsMock.getAll().stream().findFirst().orElse(null);
		assertThat(all, is(not(nullValue())));

		// When
		// Then
	}

//	todo: přidá záznam. Pokud nebude id, tak vytvoří nový jinak dle id najde původní záznam addCredentials()
	@Test
	void itShouldAddCredentials() {
		// Given
		// When
		// Then
	}

//	todo: zobrazí archivovaný záznam dle getCredentialsArchive()
//	@Test
//	void itShouldGetCredentialsArchive() {
//		// given
//		UUID id = UUID.randomUUID();
//		UUID archiveConnectionId = UUID.randomUUID();
//		Credentials credentials = new Credentials (
//				id,
//				"www.tam.tu",
//				"email",
//				"pass",
//				LocalDateTime.now(),
//				archiveConnectionId
//		);
//
////		credentialsRepository.save(credentials);
//
//		given(credentialsService.getCredentialsArchive(id)).willReturn(true);
//
//		// when
////		snažím se z db načíst záznam, který tam není?
//		List<Credentials> expected = credentialsService.findByArchiveConnectionId(archiveConnectionId);
//
//		// then
//		assertThat(archiveConnectionId).isEqualTo(expected);
//	}
}