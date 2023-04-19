package cz.dev.logincredentialsmanagement.mock.credentials;

import cz.dev.logincredentialsmanagement.credentials.Credentials;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CredentialsMock {

//	todo: doplnit údaje
	private static final String URL = "";
	private static final String EMAIL = "";
	private static final String PASSWORD = "";

	public static List<Credentials> getAll() {

		List<Credentials> all = new ArrayList<>();

		Credentials one = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 10, 14), null);
		Credentials two = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 11, 14), null);
		Credentials three = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 12, 14), one.getId());
		Credentials four = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 13, 14), null);
		Credentials five = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 14, 14), three.getId());
		Credentials six = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 15, 14), two.getId());
		Credentials seven = getOne(UUID.randomUUID(), URL, EMAIL, PASSWORD, LocalDateTime.of(2023, 3, 18, 16, 14), null);

		all.add(one);
		all.add(two);
		all.add(three);
		all.add(four);
		all.add(five);
		all.add(six);
		all.add(seven);

		return all;
	}

	private static Credentials getOne(UUID id, String url, String email, String password, LocalDateTime createdAt, UUID archiveConnectionId) {
		return Credentials.builder()
				.id(id)
				.url(url)
				.email(email)
				.password(password)
				.createdAt(createdAt)
				.archiveConnectionId(archiveConnectionId)
				.build();
	}
}
