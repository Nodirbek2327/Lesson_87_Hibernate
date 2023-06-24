package org.example.Library.repository;

import org.example.Library.Entity.BookEntity;
import org.example.Library.Entity.CategoryEntity;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.Entity.TakenBooks;
import org.example.Library.enums.BookStatus;
import org.example.Library.enums.ProfileRoles;
import org.example.Library.enums.ProfileStatus;
import org.example.Library.enums.TakenBookStatus;
import org.example.Library.mapper.BestsellerMapper;
import org.example.Library.mapper.BookMapper;
import org.example.Library.mapper.HistoryMapper;
import org.example.Library.mapper.ProfileMapper;
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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Repository
public class AdminRepository {
    public List<BookMapper> searchByName(String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BookMapper(b.id, b.title, b.author, b.category) FROM BookEntity as b where b.title = :fn ");
        query.setParameter("fn", name);
        List<BookMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public CategoryEntity getCategoryByName(String category) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        CategoryEntity categoryEntity = session.find(CategoryEntity.class, category);
//        Query query = session.createQuery("FROM CategoryEntity where name = :fn");
//        query.setParameter("fn", category);
//        List<CategoryEntity> list = query.getResultList();
        session.close();
        factory.close();
        return categoryEntity;
       // return list.isEmpty()? null : list.get(0);
    }

    public boolean addBook(BookEntity bookEntity) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Object object = session.save(bookEntity);
        t.commit();
        session.close();
        factory.close();
        return object != null;
    }

    public int removeBook(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from BookEntity  where  id =:id");
        query.setParameter("id", id);

        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows;
    }

    public List<BookMapper> booksOnHand() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BookMapper(b.id, b.title, b.author, b.category) FROM BookEntity as b where b.visible = :fn ");
        query.setParameter("fn", String.valueOf(BookStatus.NOT_VISIBLE));
        List<BookMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public List<HistoryMapper> bookHistory(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List<HistoryMapper> list = session.createQuery(" select new org.example.Library.mapper.HistoryMapper(t.taken_date, t.returned_date, s.name, s.surname, s.phone, t.note) " +
                " from  TakenBooks as t  inner join t.studentId as s where t.bookId = "+id).getResultList();

        t.commit();
        session.close();
        factory.close();
        return list;
    }

    public List<BestsellerMapper> best() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BestsellerMapper(b.id, b.title," +
                " b.author, b.category, count(*)) from TakenBooks as t  inner join t.bookId as b order by t.book_id desc ");
        List<BestsellerMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public void addCategory(CategoryEntity categoryEntity) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Object object = session.save(categoryEntity);
        t.commit();
        session.close();
        factory.close();
    }

    public int deleteCategory(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("delete from CategoryEntity  where  id =:id");
        query.setParameter("id", id);

        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows;
    }

    public List<ProfileMapper> profileList() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.ProfileMapper" +
                "( b.id, b.name, b.surname, b.login, b.phone, b.status, b.role, b.created_date ) FROM ProfileEntity as b  ");
        List<ProfileMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public List<ProfileMapper> searchProfile(String data) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.ProfileMapper" +
                "( b.id, b.name, b.surname, b.login, b.phone, b.status, b.role, b.created_date ) FROM ProfileEntity as b where b.id = :fn " +
                "or  b.name = :fn  or b.surname = :fn  or b.login = :fn or b.phone = :fn");
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        List<ProfileMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public int changeProfileStatus(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Update ProfileEntity  set status = :fn where  id =:id");
        query.setParameter("id", id);
        query.setParameter("fn", ProfileStatus.NOT_ACTIVE);

        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows;
    }

    public List<ProfileMapper> studentList() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.ProfileMapper" +
                "( b.id, b.name, b.surname, b.login, b.phone, b.status, b.role, b.created_date ) FROM ProfileEntity as b where role = :fn ");
        query.setParameter("fn", String.valueOf(ProfileRoles.STUDENT));
        List<ProfileMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public List<ProfileMapper> searchStudent(String data) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.ProfileMapper" +
                "( b.id, b.name, b.surname, b.login, b.phone, b.status, b.role, b.created_date ) FROM ProfileEntity as b where role = :fn and b.id = :fn " +
                "or  b.name = :fn  or b.surname = :fn  or b.login = :fn or b.phone = :fn  ");
        query.setParameter("fn", String.valueOf(ProfileRoles.STUDENT));
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        query.setParameter("fn", data);
        List<ProfileMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public int blockStudent(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Update ProfileEntity  set status = :fn where  id =:id");
        query.setParameter("id", id);
        query.setParameter("fn", ProfileStatus.BLOCK);

        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows;
    }

    public int activateStudent(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Update ProfileEntity  set status = :fn where  id =:id");
        query.setParameter("id", id);
        query.setParameter("fn", ProfileStatus.ACTIVE);

        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows;
    }

    public List<BookEntity> takeBook(ProfileEntity profileEntity, String name) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery(" FROM BookEntity where title = :fn and visible = :fn");
        query.setParameter("fn", name);
        query.setParameter("fn", BookStatus.VISIBLE.toString());
        List<BookEntity> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public boolean addTakenBook(ProfileEntity profileEntity, BookEntity bookEntity) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        TakenBooks takenBooks = new TakenBooks();
        takenBooks.setBookId(bookEntity);
        takenBooks.setStudentId(profileEntity);
        takenBooks.setTaken_date(LocalDateTime.now());
        takenBooks.setNote("i don't know");
        takenBooks.setStatus(TakenBookStatus.TOOK);

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Object object = session.save(takenBooks);
        Query query = session.createQuery("Update BookEntity  set visible = :fn where  id =:id");
        query.setParameter("fn", BookStatus.NOT_VISIBLE.toString());
        query.setParameter("id", bookEntity.getId());
        t.commit();
        session.close();
        factory.close();
        return object != null;
    }

    public List<TakenBooks> returnBook(ProfileEntity profileEntity, Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery(" FROM TakenBooks where book_id = :id and student_id = :id and status = :fn");
        query.setParameter("fn", TakenBookStatus.TOOK.toString());
        query.setParameter("id", id);
        query.setParameter("id", profileEntity.getId());
        List<TakenBooks> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }

    public boolean returnTakenBook(ProfileEntity profileEntity, TakenBooks takenBooks) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("Update BookEntity  set visible = :fn where  id =:id");
        query.setParameter("id", takenBooks.getBookId());
        query.setParameter("fn", BookStatus.VISIBLE);
        Query query2 = session.createQuery("Update TakenBooks  set status = :fn where  id =:id");
        query2.setParameter("fn", TakenBookStatus.RETURNED);
        query2.setParameter("id", takenBooks.getId());

        int effectedRows = query.executeUpdate();
        int effectedRows2 = query2.executeUpdate();
        transaction.commit();
        session.close();
        factory.close();
        return effectedRows2 > 0 && effectedRows > 0;
    }

    public List<BookMapper> booksOnStudent(ProfileEntity profileEntity) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT new org.example.Library.mapper.BookMapper(b.id, b.title, b.author, b.category) FROM BookEntity as b  b.visible = :fn ");
        query.setParameter("fn", String.valueOf(BookStatus.NOT_VISIBLE));
        List<BookMapper> list = query.getResultList();

        session.close();
        factory.close();
        return list;
    }
}
