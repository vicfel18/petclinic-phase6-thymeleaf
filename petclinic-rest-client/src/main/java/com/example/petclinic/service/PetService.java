package com.example.petclinic.service;

import com.example.petclinic.model.Pet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private RestTemplate restTemplate;

    public PetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pet savePet(Pet pet) {

        URI uri = URI.create("http://localhost:9192/petapi/pet/addPet");

        Pet response = restTemplate.postForObject(uri, pet, Pet.class);

        log.info(new StringBuilder().append("Saved Pet: ").append(response.toString()).toString());
        return response;

    }

    public List<Pet> getAllPets() {

        URI uri = URI.create("http://localhost:9192/petapi/pet/getAllPets");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Pet> pets = mapper.convertValue(response, new TypeReference<List<Pet>>() {
        });

        log.info(new StringBuilder().append("Returning all pets.").toString());
        return pets;

    }

    public List<Pet> getPetByName(String name) {

        // string replacement needed to create proper URL
        String modifiedName = name.replaceAll(" ", "%20");
        URI uri = URI.create("http://localhost:9192/petapi/pet/getPetByName/" + modifiedName);

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Pet> pets = mapper.convertValue(response, new TypeReference<List<Pet>>() {
        });

        log.info(new StringBuilder().append("Returning pet [").append(name).append("].").toString());

        return pets;

    }
}
