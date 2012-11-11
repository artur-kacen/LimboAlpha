package com.limbo.app.dao;

import java.util.ArrayList;
import java.util.List;

import com.limbo.app.domain.SystemUser;
import com.limbo.app.domain.UserRoles;
import com.limbo.app.web.ClientController;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class SystemUserDAOImpl implements SystemUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(ClientController.class);

	public void addUser(SystemUser user) {
		// if (repair.get)
		user.setEnabled(true);
		UserRoles ur = new UserRoles();
		ur.setRoleId(1);
		OneWayEncryptor encryptor = new OneWayEncryptor();
		user.setPassword(encryptor.encrypt(user.getPassword(), user.getUsername()));
		sessionFactory.getCurrentSession().save(user);
		ur.setUserId(user.getId());
		sessionFactory.getCurrentSession().save(ur);
	}

	@SuppressWarnings("unchecked")
	public List<SystemUser> listUser() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from SystemUser").list();
	}

	public void removeUser(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		SystemUser user = (SystemUser) session.load(SystemUser.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
			session.flush();
		}

	}

	public SystemUser getUser(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		SystemUser user = (SystemUser) session.load(SystemUser.class, id);
		return user;
	}

	public void updateUser(SystemUser user) {
		Session session = sessionFactory.getCurrentSession();
		OneWayEncryptor encryptor = new OneWayEncryptor();
		user.setPassword(encryptor.encrypt(user.getPassword(), user.getUsername()));
		session.update(user);
		session.flush();
	}

	public SystemUser getUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from SystemUser where username = :username");
		query.setParameter("username", username);
		SystemUser user = (SystemUser) query.list().get(0);
		return user;

	}

	public List<SimpleGrantedAuthority> listUserAuthority(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createSQLQuery(
						"select r.authority from roles r inner join user_roles ur ON r.role_id = ur.role_id	inner join users u ON ur.user_id = u.user_id where u.user_id = :user_id")

				.setParameter("user_id", id);
		logger.info("List: " + query.list());
		List<SimpleGrantedAuthority> grants = new ArrayList<SimpleGrantedAuthority>();
		for (Object grant : query.list()) {
			grants.add(new SimpleGrantedAuthority(grant.toString()));
		}
		return grants;
	}

	@SuppressWarnings("unchecked")
	public void encryptPasswords() {
		Session session = sessionFactory.getCurrentSession();
		OneWayEncryptor encryptor = new OneWayEncryptor();

		String encrypted = "";

		List<SystemUser> users = session.createQuery("from SystemUser").list();
		for (SystemUser user : users) {
			encrypted = encryptor.encrypt(user.getPassword(),
					user.getUsername());
			user.setPassword(encrypted);
			updateUser(user);
		}
	}

}