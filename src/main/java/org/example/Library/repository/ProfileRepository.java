package org.example.Library.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.Library.Entity.BookEntity;
import org.example.Library.Entity.CategoryEntity;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.mapper.BookMapper;
import org.example.Library.mapper.CategoryMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
@Component
@Setter
@Getter
public class ProfileRepository {

    public ProfileEntity getProfileByPhone(String phone) {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

            SessionFactory factory = meta.getSessionFactoryBuilder().build();
            Session session = factory.openSession();

            Query query = session.createQuery("FROM ProfileEntity where  phone = :fn");
            query.setParameter("fn", phone);
            List<ProfileEntity> list = query.getResultList();
            session.close();
            factory.close();
            return list.isEmpty()? null : list.get(0);
    }

    public boolean addProfile(ProfileEntity profileEntity) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Object object = session.save(profileEntity);
        t.commit();
        session.close();
        factory.close();
        return object != null;
    }

    public ProfileEntity login(String login, String password) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("FROM ProfileEntity where  login = :fn ");
        query.setParameter("fn", login);
        List<ProfileEntity> list = query.getResultList();
        System.out.println(list);
        session.close();
        factory.close();
        return list.isEmpty()? null : list.get(0);
    }

    public List<BookMapper> bookList() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BookMapper(b.id, b.title, b.author, b.category) FROM BookEntity as b ");

        List<BookMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public List<BookMapper> search(String data) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BookMapper(b.id, b.title, b.author, b.category) FROM BookEntity as b  " +
                "where b.title like :fn  or b.author like :fn or b.category like :fn");
        query.setParameter("fn", data+"%");
        query.setParameter("fn", data+"%");
        query.setParameter("fn", data+"%");

        List<BookMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public List<CategoryMapper> categories() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.CategoryMapper(b.id, b.name) FROM CategoryEntity as b");

        List<CategoryMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public List<BookMapper> bookListByCategory(String category) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BookMapper(b.id, b.title, b.author, b.category) FROM BookEntity as b where b.category = :fn ");
        query.setParameter("fn", category);
        List<BookMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }
}
