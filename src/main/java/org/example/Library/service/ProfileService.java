package org.example.Library.service;

import lombok.Getter;
import lombok.Setter;
import org.example.Library.Entity.BookEntity;
import org.example.Library.Entity.CategoryEntity;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.controller.AdminController;
import org.example.Library.controller.StudentController;
import org.example.Library.enums.ProfileRoles;
import org.example.Library.enums.ProfileStatus;
import org.example.Library.exp.AppBadRequestException;
import org.example.Library.exp.ItemNotFoundException;
import org.example.Library.mapper.BookMapper;
import org.example.Library.mapper.CategoryMapper;
import org.example.Library.repository.ProfileRepository;
import org.example.Library.util.PhoneValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@Service
@Setter
@Getter
public class ProfileService {
    @Autowired
    private StudentController studentController;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AdminController adminController;

    public  String register(ProfileEntity profileEntity) {
        String phone = profileEntity.getPhone();
        // validate phone
        if (!PhoneValidationUtil.isValidPhone(phone)) {
//           System.out.println("This phone is invalid");
//           return;
            throw new AppBadRequestException("This phone is invalid");
        }
        // check phone unique
        ProfileEntity existProfile = profileRepository.getProfileByPhone(phone);
        if (existProfile != null) {
//            System.out.println("This phone is already registered");
//            return;
           throw new ItemNotFoundException("This phone is already registered");
        }
        boolean result = profileRepository.addProfile(profileEntity);
        if (result) {
            return "you have successfully registered \n"+
                    "Login to your account!";
        } else {
           // System.out.println("Something went wrong");
            throw new AppBadRequestException("Something went wrong");
        }
    }


    public String login(String login, String password) {
       // System.out.println(profileRepository.getProfileByPhone("+998915254475"));
        ProfileEntity profileEntity = profileRepository.login(login, password);
        if (profileEntity == null) {
//            System.out.println("login or password is incorrect!");
//            return;
            throw new AppBadRequestException("login or password is incorrect!");
        } else if (profileEntity.getStatus().equals(ProfileStatus.BLOCK)) {
//            System.out.println("your account is blocked!");
//            return;
            throw new ItemNotFoundException("your account is blocked!");
        }

        System.out.println("you have logged in successfully!");

        if (profileEntity.getRole().equals(ProfileRoles.STUDENT)) {
            studentController.setProfileEntity(profileEntity);
            studentController.start();
            return "Welcome Student";
        } else {
            adminController.setProfileEntity(profileEntity);
            adminController.start();
            return "Welcome worker";
        }

    }

    public List<BookMapper> bookList() {
        List<BookMapper> list = profileRepository.bookList();
        if (list.isEmpty()){
//            System.out.println("There is no books");
//            return;
            throw new ItemNotFoundException("there is no books");
        }
        System.out.println("*********************** BOOKS *********************** \n");
        //list.forEach(System.out::println);
        return list;
    }

    public List<BookMapper> search(String data) {
        List<BookMapper> list = profileRepository.search(data);
        if (list.isEmpty()){
//            System.out.println("There is no books like that");
//            return;
            throw new ItemNotFoundException("There is no books like that");
        }
        System.out.println("*********************** Result *********************** \n");
        //list.forEach(System.out::println);
        return list;
    }

    public List<CategoryMapper> categories() {
        List<CategoryMapper> list = profileRepository.categories();
        if (list.isEmpty()){
//            System.out.println("There is no category");
//            return;
            throw new ItemNotFoundException("There is no category");
        }
        System.out.println("*********************** category *********************** \n");
       // list.forEach(System.out::println);
        return list;
    }

    public List<BookMapper> searchBooks(String category) {
        List<BookMapper> list = profileRepository.bookListByCategory(category);
        if (list.isEmpty()){
//            System.out.println("There is no books in this category");
//            return;
            throw new ItemNotFoundException("There is no books in this category") ;
        }
        System.out.println("*********************** BOOKS *********************** \n");
        return list;
    }
}
