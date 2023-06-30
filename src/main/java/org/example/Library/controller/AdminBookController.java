package org.example.Library.controller;

import lombok.Setter;
import org.example.Library.Entity.BookEntity;
import org.example.Library.Entity.CategoryEntity;
import org.example.Library.Entity.ProfileEntity;
import org.example.Library.container.ComponentContainer;
import org.example.Library.enums.BookStatus;
import org.example.Library.service.AdminService;
import org.example.Library.service.ProfileService;
import org.example.Library.util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Setter
@Controller
public class AdminBookController {
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
                case 1->bookList();
                case 2->searchBook();
                case 3->addBook();
                case 4->removeBook();
                case 5->bookOnHand();
                case 6->history();
                case 7->bestBooks();
                case 0 ->t=false;
            }
        }
    }

    private ResponseEntity<?> bestBooks() {
        return ResponseEntity.ok(adminService.best());
      //  adminService.best();
    }

    private ResponseEntity<?> history() {
        System.out.println("Enter book id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        return ResponseEntity.ok(adminService.getHistory(id));
      //  adminService.getHistory(id);
    }

    private ResponseEntity<?> bookOnHand() {
        return ResponseEntity.ok(adminService.booksOnHand());
      //  adminService.booksOnHand();
    }

    private ResponseEntity<String> removeBook() {
        System.out.println("Enter id: ");
        Integer id = ComponentContainer.intScanner.nextInt();
        return ResponseEntity.ok( adminService.removeBook(id));
       // adminService.removeBook(id);
    }

    private ResponseEntity<String> addBook() {
        System.out.println("Enter title: ");
        String title = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter author: ");
        String author = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter category: ");
        String category = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter published_date: ");
        String publishDate = ComponentContainer.stringScanner.nextLine();

        CategoryEntity categoryEntity = adminService.getCategoryByName(category);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(author);
        bookEntity.setCategory(categoryEntity);
        bookEntity.setTitle(title);
        bookEntity.setPublishDate(LocalDate.parse(publishDate));
        bookEntity.setAvailableDay(LocalDate.parse("2026-07-27"));
        bookEntity.setVisible(BookStatus.VISIBLE);
        bookEntity.setCreated_date(LocalDateTime.now());
        return ResponseEntity.ok(adminService.addBook(bookEntity));
        //adminService.addBook(bookEntity);
    }


    private ResponseEntity<?> searchBook() {
        System.out.println("Enter book name: ");
        String name = ComponentContainer.stringScanner.nextLine();
        return ResponseEntity.ok(adminService.searchByName(name));
      //  adminService.searchByName(name);
    }

    private ResponseEntity<?> bookList() {
        return  ResponseEntity.ok(profileService.bookList());
       // profileService.bookList();
    }


    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Book List");
        System.out.println("2. searchByName");
        System.out.println("3. add book");
        System.out.println("4. remove book");
        System.out.println("5. Books on hand");
        System.out.println("6. book history");
        System.out.println("7. best books");
        System.out.println("0. Exit.");
    }







}
