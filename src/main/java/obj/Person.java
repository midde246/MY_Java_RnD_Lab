/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.util.Objects;

/**
 *
 * @author saradam
 */
class Person {
    private String name;
    private String age;

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
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }
    
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            
        }
        Person person = (Person) obj;
       
        if((this.name == null) ? (person.name != null) : (!this.name.equals(person.name))){
            return false;
        }
        if((this.age == null) ? (person.age != null) : (!this.age.equals(person.age))){
            return false;
        }
        
        return true;
    } 

    @Override
    public int hashCode() {
        int hash = 0;
        hash = 75 * hash + Objects.hashCode(this.name);
        hash = 75 * hash + Objects.hashCode(this.age);
        return hash;
    }
}
