/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.java;

/**
 *
 * @author sangdn
 */
public class User {
    private String id;
    private String name;
    private int age;
    private long dob;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the dob
     */
    public long getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(long dob) {
        this.dob = dob;
    }
    
}
