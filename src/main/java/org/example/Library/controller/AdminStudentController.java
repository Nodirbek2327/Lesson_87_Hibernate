package org.example.Library.controller;

import lombok.Setter;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.container.ComponentContainer;
import org.example.Library.service.AdminService;
import org.example.Library.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@Setter
public class AdminStudentController {
    private ProfileEntity profileEntity;
    @Autowired
    private AdminService adminService;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 1->profileList();
                case 2->searchStudent();
                case 3->blockStudent();
                case 4->activateStudent();
                case 5->studentByBook();
                case 0 ->t=false;
            }
        }
    }

    private void studentByBook() {

    }

    private ResponseEntity<String> activateStudent() {
        System.out.println("Enter id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        return ResponseEntity.ok(adminService.activateStudent(id));
       // adminService.activateStudent(id);
    }

    private ResponseEntity<String> blockStudent() {
        System.out.println("Enter id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        return ResponseEntity.ok(adminService.blockStudent(id));
      //  adminService.blockStudent(id);
    }

    private ResponseEntity<?> searchStudent() {
        System.out.println("Enter something to search: ");
        String data = ComponentContainer.stringScanner.nextLine();
        return ResponseEntity.ok(adminService.searchStudent(data));
       // adminService.searchStudent(data);
    }

    private ResponseEntity<?> profileList() {
        return ResponseEntity.ok(adminService.studentList());
        //adminService.studentList();
    }

    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Student List");
        System.out.println("2. search student");
        System.out.println("3. block student");
        System.out.println("4. activate Student");
        System.out.println("5. student by book");
        System.out.println("0. Exit.");

    }


}
