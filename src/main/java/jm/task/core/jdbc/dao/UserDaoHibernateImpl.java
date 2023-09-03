package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;
    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

        @Override

        public void createUsersTable () {
            Transaction transaction = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                String createTableQuerty = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
                session.createNativeQuery(createTableQuerty).executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            }
    }

        @Override
        public void dropUsersTable () {
            Transaction transaction = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

        @Override
        public void saveUser (String name, String lastName,byte age){
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

        @Override
        public void removeUserById ( long id){
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users WHERE id = ?")
                    .setParameter(1, id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

        @Override
        public List<User> getAllUsers () {
            Transaction transaction = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                List<User> users = session.createNativeQuery("SELECT * FROM users", User.class).list();
                transaction.commit();
                return users;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void cleanUsersTable () {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
