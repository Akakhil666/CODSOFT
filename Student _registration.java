import java.util.ArrayList;
import java.util.Scanner;

// Course class to store course information
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;
    String schedule;

    Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    // Display course details
    void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Students: " + enrolledStudents);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + (capacity - enrolledStudents));
    }

    boolean hasAvailableSlots() {
        return enrolledStudents < capacity;
    }

    void enrollStudent() {
        enrolledStudents++;
    }

    void dropStudent() {
        enrolledStudents--;
    }
}

// Student class to store student information
class Student {
    String studentID;
    String name;
    ArrayList<Course> registeredCourses;

    Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        if (course.hasAvailableSlots()) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course is full. Cannot register.");
        }
    }

    void dropCourse(String courseCode) {
        boolean found = false;
        for (int i = 0; i < registeredCourses.size(); i++) {
            if (registeredCourses.get(i).courseCode.equals(courseCode)) {
                registeredCourses.get(i).dropStudent();
                registeredCourses.remove(i);
                System.out.println("Successfully dropped the course.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Course not found in your registered list.");
        }
    }

    void listRegisteredCourses() {
        System.out.println("Courses registered by " + name + ":");
        for (Course course : registeredCourses) {
            System.out.println("- " + course.title);
        }
    }
}

// Main class to handle operations
public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample course data
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Programming", "Basics of programming", 30, "Mon/Wed 10:00-11:30 AM"));
        courses.add(new Course("CS102", "Data Structures", "Learn about data structures", 25, "Tue/Thu 12:00-1:30 PM"));
        courses.add(new Course("CS103", "Operating Systems", "Understanding OS concepts", 20, "Wed/Fri 2:00-3:30 PM"));

        // Sample student data
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("S1001", "John Doe"));
        students.add(new Student("S1002", "Jane Smith"));

        // Main program loop
        while (true) {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. List Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. List Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    // List available courses
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        course.displayCourseDetails();
                        System.out.println();
                    }
                    break;

                case 2:
                    // Register for a course
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = findStudentByID(students, studentID);
                    if (student != null) {
                        System.out.print("Enter course code to register: ");
                        String courseCode = scanner.nextLine();
                        Course courseToRegister = findCourseByCode(courses, courseCode);
                        if (courseToRegister != null) {
                            student.registerCourse(courseToRegister);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    // Drop a course
                    System.out.print("Enter your student ID: ");
                    studentID = scanner.nextLine();
                    student = findStudentByID(students, studentID);
                    if (student != null) {
                        System.out.print("Enter course code to drop: ");
                        String courseCode = scanner.nextLine();
                        student.dropCourse(courseCode);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    // List registered courses
                    System.out.print("Enter your student ID: ");
                    studentID = scanner.nextLine();
                    student = findStudentByID(students, studentID);
                    if (student != null) {
                        student.listRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Helper method to find a course by code
    public static Course findCourseByCode(ArrayList<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    // Helper method to find a student by ID
    public static Student findStudentByID(ArrayList<Student> students, String studentID) {
        for (Student student : students) {
            if (student.studentID.equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }
}
