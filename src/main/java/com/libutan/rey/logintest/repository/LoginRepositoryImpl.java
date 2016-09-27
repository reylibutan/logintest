package com.libutan.rey.logintest.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.libutan.rey.logintest.entity.Login;

public class LoginRepositoryImpl implements LoginCustomRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<String> findUniqueUsers(Date start, Date end) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Login> root = cq.from(Login.class);

		List<Predicate> predicates = new ArrayList<>();
		if (start != null) {
			predicates.add(cb.greaterThanOrEqualTo(cb.function("date", Date.class, root.get("loginTime")), start));
		}

		if (end != null) {
			predicates.add(cb.lessThanOrEqualTo(cb.function("date", Date.class, root.get("loginTime")), end));
		}

		cq.select(root.get("user")).distinct(true)
		.where(predicates.toArray(new Predicate[] {}))
		.orderBy(cb.asc(root.get("user")));

		TypedQuery<String> query = em.createQuery(cq);

		return query.getResultList();
	}

	@Override
	public List<Map<String, Long>> findUserLogins(Date start, Date end, String attribute1, String attribute2, String attribute3, String attribute4) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		Root<Login> root = cq.from(Login.class);

		List<Predicate> predicates = new ArrayList<>();
		if (start != null) {
			predicates.add(cb.greaterThanOrEqualTo(cb.function("date", Date.class, root.get("loginTime")), start));
		}

		if (end != null) {
			predicates.add(cb.lessThanOrEqualTo(cb.function("date", Date.class, root.get("loginTime")), end));
		}

		if (attribute1 != null) {
			predicates.add(cb.equal(root.get("attribute1"), attribute1));
		}

		if (attribute2 != null) {
			predicates.add(cb.equal(root.get("attribute2"), attribute2));
		}

		if (attribute3 != null) {
			predicates.add(cb.equal(root.get("attribute3"), attribute3));
		}

		if (attribute4 != null) {
			predicates.add(cb.equal(root.get("attribute4"), attribute4));
		}

		cq.select(cb.tuple(root.get("user"), cb.count(root.get("user"))))
		.where(predicates.toArray(new Predicate[] {}))
		.groupBy(root.get("user"))
		.orderBy(cb.asc(root.get("user")));

		List<Map<String, Long>> userLogins = null;
		List<Tuple> tupleList = em.createQuery(cq).getResultList();

		if (!(tupleList == null || tupleList.isEmpty())) {
			userLogins = new ArrayList<>();

			for (Tuple tuple : tupleList) {
				Map<String, Long> obj = new HashMap<>();
				obj.put((String)tuple.get(0), (Long)tuple.get(1));

				userLogins.add(obj);
			}
		}

		return userLogins;
	}
}