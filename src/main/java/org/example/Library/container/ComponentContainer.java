package org.example.Library.container;

import org.example.Library.repository.AdminRepository;
import org.example.Library.repository.ProfileRepository;
import org.example.Library.repository.StudentRepository;
import org.example.Library.service.ProfileService;
import org.example.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner stringScanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);
    public static Scanner longScanner = new Scanner(System.in);
//    public static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//    public static AdminRepository adminRepository = (AdminRepository) context.getBean("adminRepository");
//    public static ProfileRepository profileRepository = (ProfileRepository) context.getBean("profileRepository");
//    public  static StudentRepository studentRepository = (StudentRepository) context.getBean("studentRepository");
//    public static ProfileService profileService = (ProfileService) context.getBean("profileService");
}
