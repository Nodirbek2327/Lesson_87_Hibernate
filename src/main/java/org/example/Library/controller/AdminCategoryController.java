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
public class AdminCategoryController {

    private ProfileEntity profileEntity;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ProfileService profileService;

    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){
                case 1->categryList();
                case 2->deleteCategory();
                case 0 ->t=false;
            }
        }
    }

    private void deleteCategory() {
        System.out.println("Enter category id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        adminService.deleteCategory(id);
    }

    private void categryList() {
        profileService.categories();
    }


    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. category List");
        System.out.println("2. delete category");
        System.out.println("0. Exit.");

    }







}
