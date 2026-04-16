package com.example;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class InsertCustomerExample {

    public static void main(String[] args) {
        
        Customer customer = new Customer(); // Entity object
        customer.setId(1);
        customer.setName("A");
        customer.setGender(Gender.MALE);
        customer.setBirthDate(new java.util.Date());
        customer.setProfile("This is A's profile");
        customer.setProfilePicture(new byte[] {1, 2, 3, 4, 5});

        Address address1 = new Address("123 Main St", "Anytown", "Anystate", "12345");
        Address address2 = new Address("456 Elm St", "Othertown", "Otherstate", "67890");
        customer.setAddressList(List.of(address1, address2));

        // em <-- emf <-- persistence.xml

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); // Start transaction
        em.persist(customer); // Insert into DB
        em.getTransaction().commit(); // Commit transaction
        em.close();
        emf.close();

    }
    
}
