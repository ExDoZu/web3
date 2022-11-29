package zuev.nikita.beans.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import zuev.nikita.beans.Hit;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Database implements Serializable {
    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Hit.class).buildSessionFactory();

    public void addHit(Hit hit) {
//        int newId = getFreeId();
//        hit.setId(newId);
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(hit);
        session.getTransaction().commit();
    }

    public void clear() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from Hit").executeUpdate();
        session.getTransaction().commit();
    }

    public List<Hit> getHits() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Hit> data = session.createQuery("From Hit ").list();
        session.getTransaction().commit();
        data.sort(Comparator.comparing(Hit::getCurrentTime).reversed());
        return data;
    }

    private int getFreeId() {
        List<Hit> data = getHits();
        data.sort(Comparator.comparingInt(Hit::getId));
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() != i) {
                return i;
            }
        }
        return data.size();
    }

}
