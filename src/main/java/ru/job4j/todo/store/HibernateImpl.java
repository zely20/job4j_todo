package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;

import java.util.List;
import java.util.function.Function;

public class HibernateImpl implements Store {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateImpl.class.getName());

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    private final SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    public static Store instOf() {
        return Lazy.INST;
    }

    private static final class Lazy {
        private static final Store INST = new HibernateImpl();
    }

    @Override
    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("select distinct i from Item i join fetch i.categories").list()
        );
    }

    @Override
    public List<Item> findAllCurrentTask() {
        return this.tx(
                session -> session.createQuery("select distinct i from Item i join fetch i.categories where is_done = 'true'").list()
        );
    }

    @Override
    public void create(Item item, String [] categories) {
        this.tx(session -> {
        for (String id : categories) {
            item.addCategory(session.load(Category.class, Integer.parseInt(id)));
        }
                session.save(item);
                return null;
        });
    }

    @Override
    public Item findById(Integer id) {
        return this.tx(
                session -> session.get(Item.class, id)
        );
    }

    @Override
    public void update(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public User findByName(String name){
        return this.tx(
                session -> {
                    Query<User> query = session.createQuery("FROM User u where u.name=:name", User.class);
                    query.setParameter("name", name);
                    return query.uniqueResult();
                }
        );
    }

    @Override
    public List<Category> getCategories() {
        return this.tx(
            session -> session.createQuery("from Category").list());
    }

    @Override
    public void saveUser(User user) {
        this.tx(
                session -> session.save(user)
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
