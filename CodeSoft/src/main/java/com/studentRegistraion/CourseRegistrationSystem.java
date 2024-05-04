package com.studentRegistraion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentId;
    private String studentName;
    private List<Course> registeredCourses;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }
}


class Course {
    private String courseId;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int enrolledStudents;

    public Course(String courseId, String title, String description, int capacity, String schedule) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public void enrollStudent() {
        enrolledStudents++;
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Course findCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void listAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getCourseId());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Populate the course database
        system.addCourse(new Course("CSE101", "Introduction to Computer Science", "An introductory course to computer science concepts.", 30, "Mon/Wed/Fri 10:00 AM - 11:30 AM"));
        system.addCourse(new Course("MTH201", "Calculus", "A course covering calculus topics.", 25, "Tue/Thu 9:00 AM - 10:30 AM"));

        // Add more courses as needed...

        // Add students to the system
        system.addStudent(new Student("S001", "John Doe"));
        system.addStudent(new Student("S002", "Jane Smith"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. List Available Courses");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = system.findStudent(studentId);
                    if (student != null) {
                        system.listAvailableCourses();
                        System.out.print("Enter Course ID to register: ");
                        String courseId = scanner.nextLine();
                        Course course = system.findCourse(courseId);
                        if (course != null) {
                            if (course.getAvailableSlots() > 0) {
                                course.enrollStudent();
                                student.registerCourse(course);
                                System.out.println("Student enrolled in course: " + course.getTitle());
                            } else {
                                System.out.println("Course is full. Enrollment failed.");
                            }
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 2:
                    system.listAvailableCourses();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
