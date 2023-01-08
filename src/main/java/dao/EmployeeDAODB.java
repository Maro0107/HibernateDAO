package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class EmployeeDAODB implements AbstractDAOInterface<Employee> {


    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private SessionFactory getSessionFactory() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    private Session openSession() {
        if(sessionFactory == null) sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        return session;
    }

    private Session openSessionWithTransaction() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    private void closeSession() {
        if(session != null) session.close();
    }

    private void closeSessionWithTransaction() {
        if(transaction != null) transaction.commit();
        closeSession();
    }

    public void persist(Employee entity) {
        openSessionWithTransaction().save(entity);
        closeSessionWithTransaction();
    }

    @Override
    public void update(Employee entity) {
        openSessionWithTransaction().update(entity);
        closeSessionWithTransaction();
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = (Employee) openSession().get(Employee.class, id);
        closeSession();
        return employee;
    }

    @Override
    public void delete(Employee entity) {
        openSessionWithTransaction().delete(entity);
        closeSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        List<Employee> projects = findAll();
        for( Employee p: projects) delete(p);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = (List<Employee>) openSession().createQuery("FROM Employee").list();
        closeSession();
        return employees;
    }
}
