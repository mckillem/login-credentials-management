package cz.dev.logincredentialsmanagement.credentials;

import cz.dev.logincredentialsmanagement.mock.credentials.CredentialsMock;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CredentialsServiceTest {

	@InjectMocks
	private CredentialsService credentialsService;
	@Mock
	private CredentialsRepository credentialsRepository;

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

	@Test
	void getCredentials() {
		// Given
		Credentials one = CredentialsMock.getAll().stream().findFirst().orElse(null);
		assertThat(one, is(not(nullValue())));

		// When
		Optional<Credentials> credentialsById = credentialsService.getCredentials(one.getId());

		// Then
		assertThat(credentialsById, is(not(nullValue())));
		verify(credentialsRepository).findById(one.getId());
	}

//	todo: přidá záznam. Pokud nebude id, tak vytvoří nový jinak dle id najde původní záznam addCredentials()
//	todo: nesouhlasí expected a actual
	@Test
	void addCredentialsWithoutId() {
		// Given
		Credentials one = new Credentials(
				null,
				"url",
				"email",
				"password",
				LocalDateTime.now(),
				null
		);

		assertThat(one, is(not(nullValue())));
		
		// When
		credentialsService.addCredentials(one);
		
		// Then
		ArgumentCaptor<Credentials> credentialsArgumentCaptor = ArgumentCaptor.forClass(Credentials.class);

		verify(credentialsRepository).save(credentialsArgumentCaptor.capture());

		Credentials capturedCredentials = credentialsArgumentCaptor.getValue();

		AssertionsForClassTypes.assertThat(capturedCredentials).isEqualTo(one);
	}

//	todo: nesouhlasí expected a actual
	@Test
	void addCredentialsWithId() {
		// Given
		Credentials one = new Credentials(
				UUID.randomUUID(),
				"url",
				"email",
				"password",
				LocalDateTime.now(),
				null
		);

		assertThat(one, is(not(nullValue())));

		// When
		credentialsService.addCredentials(one);

		// Then
		ArgumentCaptor<Credentials> credentialsArgumentCaptor = ArgumentCaptor.forClass(Credentials.class);

		verify(credentialsRepository).save(credentialsArgumentCaptor.capture());

		Credentials capturedCredentials = credentialsArgumentCaptor.getValue();

		AssertionsForClassTypes.assertThat(capturedCredentials).isEqualTo(one);
	}

//	todo: zobrazí archivovaný záznam dle getCredentialsArchive()
//	todo: nevím jak vyřešit
	@Test
	void getCredentialsArchive() {
		// given
//		List<Credentials> all = CredentialsMock.getAll();
//
//		when(credentialsRepository.findByArchiveConnectionId(CredentialsMock.getAll().stream().filter()));
//
//		// When
//		List<Credentials> foundCredentials = credentialsService.getAllCredentials();
//
//		// Then
//		assertThat(foundCredentials, is(not(nullValue())));
//		verify(credentialsRepository).findAllWhereArchiveConnectionIdIsNull();

		UUID id = UUID.randomUUID();
		UUID archiveConnectionId = UUID.randomUUID();
		Credentials credentials = new Credentials (
				id,
				"www.tam.tu",
				"email",
				"pass",
				LocalDateTime.now(),
				archiveConnectionId
		);

		credentialsRepository.save(credentials);

		given(credentialsService.getCredentialsArchive(id)).willReturn(true);

		// when
//		snažím se z db načíst záznam, který tam není?
		List<Credentials> expected = credentialsService.findByArchiveConnectionId(archiveConnectionId);

		// then
		assertThat(archiveConnectionId).isEqualTo(expected);
	}
}