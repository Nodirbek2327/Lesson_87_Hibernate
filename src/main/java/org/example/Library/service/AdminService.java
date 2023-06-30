package org.example.Library.service;

import org.example.Library.Entity.BookEntity;
import org.example.Library.Entity.CategoryEntity;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.Entity.TakenBooks;
import org.example.Library.enums.BookStatus;
import org.example.Library.exp.AppBadRequestException;
import org.example.Library.exp.ItemNotFoundException;
import org.example.Library.mapper.BestsellerMapper;
import org.example.Library.mapper.BookMapper;
import org.example.Library.mapper.HistoryMapper;
import org.example.Library.mapper.ProfileMapper;
import org.example.Library.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<BookMapper> searchByName(String name) {
        List<BookMapper> list = adminRepository.searchByName(name);
        if (list.isEmpty()){
//            System.out.println("There is no books like that");
//            return;
            throw new ItemNotFoundException("There is no books like that");
        }
        System.out.println("*********************** Result *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }


    public CategoryEntity getCategoryByName(String category) {
        CategoryEntity categoryEntity = adminRepository.getCategoryByName(category);
        if (categoryEntity==null){
            CategoryEntity categoryEntity1 = new CategoryEntity();
            categoryEntity1.setName(category);
            categoryEntity1.setCreated_date(LocalDateTime.now());
            categoryEntity1.setVisible(BookStatus.VISIBLE);
            adminRepository.addCategory(categoryEntity1);
            return adminRepository.getCategoryByName(category);
        }
        return categoryEntity;
    }

    public String addBook(BookEntity bookEntity) {
        if (adminRepository.addBook(bookEntity)){
//            System.out.println("book added");
//            return;
            return "book added";
        }
     //   System.out.println("book not added");
        return "book not added";
    }

    public String  removeBook(Integer id) {
         if (adminRepository.removeBook(id) > 0){
//             System.out.println("deleted successfully");
//             return;
             return "deleted successfully";
         }
       //  System.out.println("not deleted");
        return "not deleted";
    }

    public List<BookMapper> booksOnHand() {
        List<BookMapper> list = adminRepository.booksOnHand();
        if (list.isEmpty()){
//            System.out.println("There is no books on hand");
//            return;
            throw new ItemNotFoundException("There is no books on hand");
        }
        System.out.println("*********************** Books on Hand *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }

    public List<HistoryMapper> getHistory(Integer id) {
        List<HistoryMapper> list = adminRepository.bookHistory(id);
        if (list.isEmpty()){
//            System.out.println("There is no books history");
//            return;
            throw new ItemNotFoundException("There is no books history");
        }
        System.out.println("*********************** Books History *********************** \n");
        //list.forEach(System.out::println);
        return list;
    }

    public List<BestsellerMapper> best() {
        List<BestsellerMapper> list = adminRepository.best();
        if (list.isEmpty()){
//            System.out.println("There is no books ");
//            return;
            throw new ItemNotFoundException("There is no books ");
        }
        System.out.println("*********************** Books are best taken *********************** \n");
      //  list.forEach(System.out::println);
        return list;
    }

    public String deleteCategory(Integer id) {
        if (adminRepository.deleteCategory(id)>0){
//            System.out.println("deleted successfully");
//            return;
            return "deleted successfully";
        }
       // System.out.println("category not found");
        return "category not found";
    }

    public String changeStatus(Integer id) {
        if (adminRepository.changeProfileStatus(id)>0){
//            System.out.println("changed successfully");
//            return;
            return "changed successfully";
        }
       // System.out.println("profile not found");
        throw new ItemNotFoundException("profile not found");
    }

    public List<ProfileMapper> searchProfile(String data) {
        List<ProfileMapper> list = adminRepository.searchProfile(data);
        if (list.isEmpty()){
//            System.out.println("There is no profile");
//            return;
            throw new ItemNotFoundException("There is no profile");
        }
        System.out.println("*********************** Result *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }

    public List<ProfileMapper> profileList() {
        List<ProfileMapper> list = adminRepository.profileList();
        if (list.isEmpty()){
//            System.out.println("There is no profile ");
//            return;
            throw new ItemNotFoundException("There is no profile ");
        }
        System.out.println("*********************** profiles *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }

    public List<ProfileMapper> studentList() {
        List<ProfileMapper> list = adminRepository.studentList();
        if (list.isEmpty()){
//            System.out.println("There is no student ");
//            return;
            throw new ItemNotFoundException("There is no student");
        }
        System.out.println("*********************** profiles *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }

    public List<ProfileMapper> searchStudent(String data) {
        List<ProfileMapper> list = adminRepository.searchStudent(data);
        if (list.isEmpty()){
//            System.out.println("There is no student");
//            return;
           throw new ItemNotFoundException("There is no student");
        }
        System.out.println("*********************** Result *********************** \n");
        //list.forEach(System.out::println);
        return list;
    }

    public String  blockStudent(Integer id) {
        if (adminRepository.blockStudent(id)>0){
//            System.out.println("blocked successfully");
//            return;
            return "blocked successfully";
        }
       // System.out.println("student not found");
        throw new ItemNotFoundException("student not found");
    }

    public String activateStudent(Integer id) {
        if (adminRepository.activateStudent(id)>0){
//            System.out.println("activate student successfully");
//            return;
            return "activate student successfully";
        }
        throw new ItemNotFoundException("student not found");
    }

    public String takeBook(ProfileEntity profileEntity, String name) {
        List<BookEntity> list = adminRepository.takeBook(profileEntity, name);
        if (list.isEmpty()){
//            System.out.println("book not found");
//            return;
            throw new ItemNotFoundException("book not found");
        }
        if(adminRepository.addTakenBook(profileEntity, list.get(0))){
//            System.out.println("book is taken");
//            return;
            return "book is taken";
        };
       // System.out.println("wrong");
        throw new AppBadRequestException("something wrong");
    }

    public String returnBook(ProfileEntity profileEntity, Integer id) {
        List<TakenBooks> list = adminRepository.returnBook(profileEntity, id);
        if (list.isEmpty()){
//            System.out.println("TakenBook not found");
//            return;
            throw new ItemNotFoundException("TakenBook not found");
        }
        if(adminRepository.returnTakenBook(profileEntity, list.get(0))){
//            System.out.println("book is returned");
//            return;
            return "book is returned";
        };
        //System.out.println("wrong");
        throw new AppBadRequestException("wrong");
    }

    public List<BookMapper> booksOnStudent(ProfileEntity profileEntity) {
        List<BookMapper> list = adminRepository.booksOnStudent(profileEntity);
        if (list.isEmpty()){
//            System.out.println("There is no books on your hand");
//            return;
            throw new ItemNotFoundException("There is no books on your hand");
        }
        System.out.println("*********************** Books on your Hand *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }
}

