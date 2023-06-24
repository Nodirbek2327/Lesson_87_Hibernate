package org.example.Library.controller;

import lombok.Setter;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.container.ComponentContainer;
import org.example.Library.enums.ProfileRoles;
import org.example.Library.enums.ProfileStatus;
import org.example.Library.service.ProfileService;
import org.example.Library.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;


@Component
@Setter
@Controller
public class MainController {
    @Autowired
    private ProfileService profileService;
    public void start(){
        boolean t=true;
        while (t){
            show();
        //    registrationAdmin();
            switch (GetAction.getAction()){
                case 1->bookList();
                case 2->searchBook();
                case 3->categories();
                case 4->login();
                case 5->registration();
                case 0 ->t=false;
            }
        }
    }

    public void registrationAdmin(){
        ProfileEntity profileEntity= new ProfileEntity();
        profileEntity.setName("Nodirbek");
        profileEntity.setSurname("Hasanov");
        profileEntity.setPhone("+998900000000");
        profileEntity.setLogin("nodir07");
        profileEntity.setPassword("0000");
        profileEntity.setCreated_date(LocalDateTime.now());
        profileEntity.setRole(ProfileRoles.ADMIN);
        profileEntity.setStatus(ProfileStatus.ACTIVE);

        profileService.register(profileEntity);
    }


    private void categories() {
        profileService.categories();
        System.out.println("Enter category: ");
        String category = ComponentContainer.stringScanner.nextLine();
        profileService.searchBooks(category);
    }

    private void searchBook() {
        System.out.println("Enter something to search: ");
        String data = ComponentContainer.stringScanner.nextLine();
        profileService.search(data);
    }

    private void bookList() {
        profileService.bookList();
    }

    public void registration(){
        System.out.println("Enter your name: ");
        String name= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your surname:");
        String surname= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your phone:");
        String phone= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your login:");
        String login= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password:");
        String password= ComponentContainer.stringScanner.nextLine();

        ProfileEntity profileEntity= new ProfileEntity();
        profileEntity.setName(name);
        profileEntity.setSurname(surname);
        profileEntity.setPhone(phone);
        profileEntity.setLogin(login);
        profileEntity.setPassword(password);
        profileEntity.setCreated_date(LocalDateTime.now());
        profileEntity.setRole(ProfileRoles.STUDENT);
        profileEntity.setStatus(ProfileStatus.NOT_ACTIVE);

        profileService.register(profileEntity);
    }

    public void login(){
        System.out.println("Enter your login: ");
        String login=ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password");
        String password=ComponentContainer.stringScanner.nextLine();
        profileService.login(login, password);
    }


    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Books");
        System.out.println("2. search");
        System.out.println("3. categories");
        System.out.println("4. Login");
        System.out.println("5. Registration");
        System.out.println("0. Exit.");

    }


}
