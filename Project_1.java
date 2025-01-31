import java.util.*;

interface GradingOperations {
    void addStudent(String name);
    void addGrade(int studentID, double grade);
    void viewGrades(int studentID);
    void calculateAverage(int studentID);
}

class Student {
    private int studentID;
    private String name;
    private List<Double> grades;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }
}

class GradingSystem implements GradingOperations {
    private List<Student> students = new ArrayList<>();
    private int studentCounter = 1001;

    @Override
    public void addStudent(String name) {
        Student newStudent = new Student(studentCounter, name);
        students.add(newStudent);
        System.out.println("Student Added: " + name + " (ID: " + studentCounter + ")");
        studentCounter++;
    }

    @Override
    public void addGrade(int studentID, double grade) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                student.getGrades().add(grade);
                System.out.println("Grade Added: " + grade + " for Student ID " + studentID);
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    @Override
    public void viewGrades(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                System.out.println("\nGrades for " + student.getName() + " (ID: " + studentID + "): " + student.getGrades());
                return;
            }
        }
        System.out.println("Student ID not found!");
    }

    @Override
    public void calculateAverage(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                List<Double> grades = student.getGrades();
                if (grades.isEmpty()) {
                    System.out.println("No grades available for " + student.getName());
                    return;
                }
                double sum = 0;
                for (double grade : grades) {
                    sum += grade;
                }
                double average = sum / grades.size();
                System.out.printf("\nAverage Grade for %s (ID: %d): %.2f\n", student.getName(), studentID, average);
                return;
            }
        }
        System.out.println("Student ID not found!");
    }
}

public class GradingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem system = new GradingSystem();

        while (true) {
            System.out.println("\nSTUDENT GRADING SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Grades");
            System.out.println("4. Calculate Average");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();
                    system.addStudent(name);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    system.addGrade(studentID, grade);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    int viewID = scanner.nextInt();
                    system.viewGrades(viewID);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    int avgID = scanner.nextInt();
                    system.calculateAverage(avgID);
                    break;
                case 5:
                    System.out.println("Exiting... Thank You!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Choice! Try again.");
            }
        }
    }
}