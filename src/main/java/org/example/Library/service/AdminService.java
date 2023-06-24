package org.example.Library.service;

import org.example.Library.Entity.BookEntity;
import org.example.Library.Entity.CategoryEntity;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.Entity.TakenBooks;
import org.example.Library.enums.BookStatus;
import org.example.Library.mapper.BestsellerMapper;
import org.example.Library.mapper.BookMapper;
import org.example.Library.mapper.HistoryMapper;
import org.example.Library.mapper.ProfileMapper;
import org.example.Library.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void searchByName(String name) {
        List<BookMapper> list = adminRepository.searchByName(name);
        if (list.isEmpty()){
            System.out.println("There is no books like that");
            return;
        }
        System.out.println("*********************** Result *********************** \n");
        list.forEach(System.out::println);
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

    public void addBook(BookEntity bookEntity) {
        if (adminRepository.addBook(bookEntity)){
            System.out.println("book added");
            return;
        }
        System.out.println("book not added");
    }

    public void removeBook(Integer id) {
         if (adminRepository.removeBook(id) > 0){
             System.out.println("deleted successfully");
             return;
         }
         System.out.println("not deleted");
    }

    public void booksOnHand() {
        List<BookMapper> list = adminRepository.booksOnHand();
        if (list.isEmpty()){
            System.out.println("There is no books on hand");
            return;
        }
        System.out.println("*********************** Books on Hand *********************** \n");
        list.forEach(System.out::println);
    }

    public void getHistory(Integer id) {
        List<HistoryMapper> list = adminRepository.bookHistory(id);
        if (list.isEmpty()){
            System.out.println("There is no books history");
            return;
        }
        System.out.println("*********************** Books History *********************** \n");
        list.forEach(System.out::println);
    }

    public void best() {
        List<BestsellerMapper> list = adminRepository.best();
        if (list.isEmpty()){
            System.out.println("There is no books ");
            return;
        }
        System.out.println("*********************** Books are best taken *********************** \n");
        list.forEach(System.out::println);
    }

    public void deleteCategory(Integer id) {
        if (adminRepository.deleteCategory(id)>0){
            System.out.println("deleted successfully");
            return;
        }
        System.out.println("category not found");
    }

    public void changeStatus(Integer id) {
        if (adminRepository.changeProfileStatus(id)>0){
            System.out.println("changed successfully");
            return;
        }
        System.out.println("profile not found");
    }

    public void searchProfile(String data) {
        List<ProfileMapper> list = adminRepository.searchProfile(data);
        if (list.isEmpty()){
            System.out.println("There is no profile");
            return;
        }
        System.out.println("*********************** Result *********************** \n");
        list.forEach(System.out::println);
    }

    public void profileList() {
        List<ProfileMapper> list = adminRepository.profileList();
        if (list.isEmpty()){
            System.out.println("There is no profile ");
            return;
        }
        System.out.println("*********************** profiles *********************** \n");
        list.forEach(System.out::println);
    }

    public void studentList() {
        List<ProfileMapper> list = adminRepository.studentList();
        if (list.isEmpty()){
            System.out.println("There is no student ");
            return;
        }
        System.out.println("*********************** profiles *********************** \n");
        list.forEach(System.out::println);
    }

    public void searchStudent(String data) {
        List<ProfileMapper> list = adminRepository.searchStudent(data);
        if (list.isEmpty()){
            System.out.println("There is no student");
            return;
        }
        System.out.println("*********************** Result *********************** \n");
        list.forEach(System.out::println);
    }

    public void blockStudent(Integer id) {
        if (adminRepository.blockStudent(id)>0){
            System.out.println("blocked successfully");
            return;
        }
        System.out.println("student not found");
    }

    public void activateStudent(Integer id) {
        if (adminRepository.activateStudent(id)>0){
            System.out.println("activate student successfully");
            return;
        }
        System.out.println("student not found");
    }

    public void takeBook(ProfileEntity profileEntity, String name) {
        List<BookEntity> list = adminRepository.takeBook(profileEntity, name);
        if (list.isEmpty()){
            System.out.println("book not found");
            return;
        }
        if(adminRepository.addTakenBook(profileEntity, list.get(0))){
            System.out.println("book is taken");
            return;
        };
        System.out.println("wrong");
    }

    public void returnBook(ProfileEntity profileEntity, Integer id) {
        List<TakenBooks> list = adminRepository.returnBook(profileEntity, id);
        if (list.isEmpty()){
            System.out.println("TakenBook not found");
            return;
        }
        if(adminRepository.returnTakenBook(profileEntity, list.get(0))){
            System.out.println("book is returned");
            return;
        };
        System.out.println("wrong");
    }

    public void booksOnStudent(ProfileEntity profileEntity) {
        List<BookMapper> list = adminRepository.booksOnStudent(profileEntity);
        if (list.isEmpty()){
            System.out.println("There is no books on your hand");
            return;
        }
        System.out.println("*********************** Books on your Hand *********************** \n");
        list.forEach(System.out::println);
    }
}

