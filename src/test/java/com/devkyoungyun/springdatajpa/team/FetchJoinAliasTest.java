package com.devkyoungyun.springdatajpa.team;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class FetchJoinAliasTest {

	@Autowired
	private EntityManager em;

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

		List<Team> result = em.createQuery(
				"select t from Team t join fetch t.members m where m.username = 'user1'", Team.class)
			.getResultList();

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getMembers()).hasSize(1);
	}

}
