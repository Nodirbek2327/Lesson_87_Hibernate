package org.example.Library.controller;

import lombok.Setter;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.enums.ProfileRoles;
import org.example.Library.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@Setter
public class AdminController {
    private ProfileEntity profileEntity;
    @Autowired
    private AdminBookController adminBookController;
    @Autowired
    private AdminCategoryController adminCategoryController;
    @Autowired
    private AdminProfileController adminProfileController;
    @Autowired
    private AdminStudentController adminStudentController;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 1->book();
                case 2->category();
                case 3->student();
                case 4->profile();
                case 0 ->t=false;
            }
        }
    }

    private ResponseEntity<?> profile() {
        if (profileEntity.getRole().equals(ProfileRoles.STAFF)){
//            System.out.println("profile is only for admin");
//            return;
              return ResponseEntity.badRequest().body("profile is only for admin");
        }
        adminProfileController.setProfileEntity(profileEntity);
        adminProfileController.start();
       // System.out.println("Welcome admin");
        return ResponseEntity.ok("Welcome admin");
    }

    private ResponseEntity<String> student() {
        adminStudentController.setProfileEntity(profileEntity);
        adminStudentController.start();
        //System.out.println("Welcome worker");
        return ResponseEntity.ok("Welcome worker");
    }

    private ResponseEntity<String> category() {
        if (profileEntity.getRole().equals(ProfileRoles.STAFF)){
//            System.out.println("category is only for admin");
//            return;
            return ResponseEntity.badRequest().body("category is only for admin");
        }
        adminCategoryController.setProfileEntity(profileEntity);
        adminCategoryController.start();
      //  System.out.println("Welcome admin");
        return ResponseEntity.ok("Welcome admin");
    }

    private ResponseEntity<String> book() {
        adminBookController.setProfileEntity(profileEntity);
        adminBookController.start();
       // System.out.println("Welcome worker");
        return ResponseEntity.ok("Welcome worker");
    }

    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Book");
        System.out.println("2. Category (only for admin)");
        System.out.println("3. Student");
        System.out.println("4. Profile (Only for admin)");
        System.out.println("0. Exit.");

    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity=profileEntity;
    }
}
