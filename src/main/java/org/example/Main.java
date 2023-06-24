package org.example;

import org.example.Library.Entity.ProfileEntity;
import org.example.entity.CarEntity;
import org.example.entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;


import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      get();
      //  save();
     //   getList();
      //  getListPartial();
     //   getListIdAndName();
       //   count();
    //    update(1, "Alishjon");
      //  updateSession(1, "Alish");
       // getListNative("Alish");
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
//
//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
//        Session session = factory.openSession();
//        Transaction t = session.beginTransaction();
//
//        EmployeeEntity e1 = new EmployeeEntity();
//        e1.setFirstName("Vali");
//        e1.setLastName("valiyev");
//        e1.setEmail("mazgi2@mail.ru");
//        session.save(e1);
//
////        CarEntity c1 = new CarEntity();
////        c1.setName("Matiz");
////        session.save(c1);
//
//        t.commit();
//
//        session.close();
//        factory.close();
    }

    public static void get(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("FROM ProfileEntity where  login = :fn ");
     //   query.setParameter("fn", "0000");
        query.setParameter("fn", "nodir07");
        List<ProfileEntity> list = query.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
    }

    public static void save(){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        EmployeeEntity e1 = new EmployeeEntity();
        e1.setFirstName("Valie");
        e1.setLastName("Valiyev");

        e1.setEmail("mazgi3@mail.ru");
        session.save(e1);

        t.commit();

        session.close();
        factory.close();
    }

    public static void getList() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("FROM EmployeeEntity where  firstName = :fn");
        query.setParameter("fn", "Ali");
        List<EmployeeEntity> list = query.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
    }

    public static void getListPartial() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new EmployeeEntity(e.id, e.firstName, e.lastName) FROM EmployeeEntity as e ");

        List<EmployeeEntity> list = query.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
    }

    public static void getListIdAndName() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.mapper.EmployeeMapper(e.id, e.firstName) FROM EmployeeEntity as e ");

        List<EmployeeEntity> list = query.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
    }

    public static long count() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("select count(e) from  EmployeeEntity e ");

        Long count = (Long) query.getSingleResult();
        System.out.println(count);
        session.close();
        factory.close();

        return count;
    }

    public static int update(Integer id, String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Update EmployeeEntity  set firstName = :fn where  id =:id");
        query.setParameter("id", id);
        query.setParameter("fn", name);

        int effectedRows = query.executeUpdate();
        System.out.println(effectedRows);
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows;
    }

    public static void updateSession(Integer id, String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from  EmployeeEntity where id =:id");
        query.setParameter("id", id);

        EmployeeEntity entity = (EmployeeEntity) query.getSingleResult();
        entity.setFirstName(name);

        session.update(entity); // update

        transaction.commit();
        session.close();
        factory.close();
    }

    public static void getListNative(String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        NativeQuery nativeQuery = session.createNativeQuery("Select * from employee where first_name = :fn ");
        nativeQuery.setParameter("fn", name);

        List<Object[]> list = nativeQuery.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
    }

    public static void getListNative2(String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        NativeQuery nativeQuery = session.createNativeQuery("Select * from employee where first_name = :fn ", EmployeeEntity.class);
        nativeQuery.setParameter("fn", name);

        List<Object[]> list = nativeQuery.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
    }
}