package org.example.Library.controller;

import lombok.Setter;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.container.ComponentContainer;
import org.example.Library.service.AdminService;
import org.example.Library.service.ProfileService;
import org.example.Library.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
@Setter
public class AdminProfileController {
    private ProfileEntity profileEntity;
    @Autowired
    private AdminService adminService;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 1->profileList();
                case 2->searchProfile();
                case 3->changeStatus();
                case 0 ->t=false;
            }
        }
    }

    private void changeStatus() {
        System.out.println("Enter id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        adminService.changeStatus(id);
    }

    private void searchProfile() {
        System.out.println("Enter something to search: ");
        String data = ComponentContainer.stringScanner.nextLine();
        adminService.searchProfile(data);
    }

    private void profileList() {
        adminService.profileList();
    }

    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Profile List");
        System.out.println("2. search profile");
        System.out.println("3. change profile status");
        System.out.println("0. Exit.");

    }





}
