package com.example.petclinic;

import com.example.petclinic.business.PetClinicBusinessWorkflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PetClinicClient {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(PetClinicClient.class, args);

        PetClinicBusinessWorkflow business = (PetClinicBusinessWorkflow) context.getBean("petClinicBusinessWorkflow");

        business.runBusiness();

    }
}
