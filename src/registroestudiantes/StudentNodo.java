/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registroestudiantes;

/**
 *
 * @author JULIO
 */
public class StudentNodo {
    public String id;
    public String name;
    public String gender;
    public String tutorName;
    public String tutorPhone;
    public int age;
    public int grade;
    public StudentNodo sig;

    public StudentNodo(String id, String name, String gender, String tutorName, String tutorPhone, int age, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tutorName = tutorName;
        this.tutorPhone = tutorPhone;
        this.age = age;
        this.grade = grade;
        sig=null;
    }
    
}
