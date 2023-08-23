package jm.task.core.jdbc.dao;

import com.mysql.cj.protocol.ServerSessionStateController;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;
    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

        @Override

        public void createUsersTable () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)").executeUpdate();
        session.getTransaction().commit();
    }

        @Override
        public void dropUsersTable () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.getTransaction().commit();
    }

        @Override
        public void saveUser (String name, String lastName,byte age){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")
                .setParameter(1, name)
                .setParameter(2, lastName)
                .setParameter(3, age)
                .executeUpdate();
        session.getTransaction().commit();
    }

        @Override
        public void removeUserById ( long id){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DELETE FROM users WHERE id = ?")
                .setParameter(1, id)
                .executeUpdate();
        session.getTransaction().commit();
    }

        @Override
        public List<User> getAllUsers () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createNativeQuery("SELECT * FROM users", User.class).list();
        session.getTransaction().commit();
        return users;
    }

        @Override
        public void cleanUsersTable () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DELETE FROM users").executeUpdate();
        session.getTransaction().commit();

    }
}
