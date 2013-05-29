package com.limbo.app.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.limbo.app.domain.DataTablesRequest;
import com.limbo.app.domain.DataTablesResponse;
import com.limbo.app.domain.SystemUser;
import com.limbo.app.util.OneWayEncryptor;
import com.limbo.app.web.ClientController;

@Repository
public class SystemUserDAOImpl implements SystemUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	public void addUser(SystemUser user) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();		
		session.save(user);
		session.flush();
		session.getTransaction().commit();
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
		logger.info(user.getRoles().toString());
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
			updateUser(user);
		}
	}
	
	public DataTablesResponse<SystemUser> getDataTableResponse(DataTablesRequest dtReq) {
		List<String> columnList =  dtReq.dataProp;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(SystemUser.class);
		
		criteria.setFirstResult(dtReq.displayStart);
		criteria.setMaxResults(dtReq.displayLength);
		
		if (dtReq.sortDirections.get(0).equals("asc")) {
			criteria.addOrder(Order.asc(columnList.get(dtReq.sortedColumns.get(0))));
		} else {
			criteria.addOrder(Order.desc(columnList.get(dtReq.sortedColumns.get(0))));
		}	
		
		List<SystemUser> users = criteria.list();
		DataTablesResponse<SystemUser> dataTableResponse = new DataTablesResponse<SystemUser>();
		dataTableResponse.data = users;
		
		int foundRows = ((BigInteger)session.createSQLQuery("select found_rows();").uniqueResult()).intValue();
		int rowCount = ((Long)session.createQuery("select count(*) from SystemUser").uniqueResult()).intValue();
		dataTableResponse.totalDisplayRecords = rowCount;
		dataTableResponse.totalRecords = rowCount;
		dataTableResponse.echo = dtReq.echo;
		
		return dataTableResponse;
	}
}