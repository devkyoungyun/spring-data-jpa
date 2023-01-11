package com.devkyoungyun.springdatajpa.team;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class FetchJoinAliasTest {

	@Autowired
	private EntityManager em;

	@DisplayName("fetch join 시에 fetch 대상에 조건을 걸면 의도하지 않은 결과 반환")
	@Test
	void fetchJoinTest() {
		Team team = new Team();
		team.setName("teamA");
		em.persist(team);

		Member member1 = new Member();
		member1.setUsername("user1");
		member1.setTeam(team);
		em.persist(member1);

		Member member2 = new Member();
		member2.setUsername("user2");
		member2.setTeam(team);
		em.persist(member2);

		em.flush();
		em.clear();

		List<Team> resultUsingAlias = em.createQuery(
				"select t from Team t join fetch t.members m where m.username = 'user1'", Team.class)
			.getResultList();

		assertThat(resultUsingAlias.get(0).getMembers())
			.hasSize(1)
			.extracting("username").containsExactly("user1");

		List<Team> result = em.createQuery(
				"select distinct t from Team t join fetch t.members", Team.class)
			.getResultList();

		assertThat(result.get(0).getMembers())
			.hasSize(1)
			.extracting("username").containsExactly("user1");
	}

}
