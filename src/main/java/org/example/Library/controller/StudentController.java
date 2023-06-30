package org.example.Library.controller;

import lombok.Setter;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.container.ComponentContainer;
import org.example.Library.service.AdminService;
import org.example.Library.service.ProfileService;
import org.example.Library.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Setter
@Controller
public class StudentController {
    private ProfileEntity profileEntity;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AdminService adminService;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 1->bookList();
                case 2->searchBook();
                case 3->take();
                case 4->returnBook();
                case 5->booksOnHand();
                case 6->history();
                case 0 ->t=false;
            }
        }
    }

    private void history() {

    }

    private ResponseEntity<?> booksOnHand() {
        return ResponseEntity.ok(adminService.booksOnStudent(profileEntity));
        //adminService.booksOnStudent(profileEntity);
    }

    private ResponseEntity<String> take() {
        System.out.println("Enter book name to take: ");
        String name = ComponentContainer.stringScanner.nextLine();
        return ResponseEntity.ok(adminService.takeBook(profileEntity, name));
       // adminService.takeBook(profileEntity, name);
    }

    private ResponseEntity<String> returnBook() {
        System.out.println("Enter book id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        return ResponseEntity.ok(adminService.returnBook(profileEntity, id));
      //  adminService.returnBook(profileEntity, id);
    }

    private ResponseEntity<?> searchBook() {
        System.out.println("Enter something to search: ");
        String data = ComponentContainer.stringScanner.nextLine();
        return ResponseEntity.ok( profileService.search(data));
       // profileService.search(data);
    }

    private ResponseEntity<?> bookList() {
        return ResponseEntity.ok( profileService.bookList());
       // profileService.bookList();
    }


    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Book List");
        System.out.println("2. search");
        System.out.println("3. Take Book");
        System.out.println("4. Return Book");
        System.out.println("5. Books on hand");
        System.out.println("6. Take book history");
        System.out.println("0. Exit.");

    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity=profileEntity;
    }
}
