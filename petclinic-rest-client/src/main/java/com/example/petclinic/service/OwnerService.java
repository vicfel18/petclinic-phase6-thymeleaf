package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OwnerService {

    private static final Logger log = LoggerFactory.getLogger(OwnerService.class);

    private RestTemplate restTemplate;

    public OwnerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Owner saveOwner(Owner owner) {

        URI uri = URI.create("http://localhost:9191/ownerapi/owner/addOwner");

        Owner response = restTemplate.postForObject(uri, owner, Owner.class);

        log.info(new StringBuilder().append("Saved Owner: ").append(response.toString()).toString());
        return response;

    }

    public List<Owner> getAllOwners() {

        URI uri = URI.create("http://localhost:9191/ownerapi/owner/getAllOwners");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Owner> owners = mapper.convertValue(response, new TypeReference<List<Owner>>() {
        });

        log.info(new StringBuilder().append("Returning all owners.").toString());
        return owners;

    }

    public List<Owner> getOwnerByName(String name) {

        // string replacement needed to create proper URL
        String modifiedName = name.replaceAll(" ", "%20");
        URI uri = URI.create("http://localhost:9191/ownerapi/owner/getOwnerByName/" + modifiedName);

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Owner> owners = mapper.convertValue(response, new TypeReference<List<Owner>>() {
        });

        log.info(new StringBuilder().append("Returning owner [").append(name).append("].").toString());

        return owners;

    }

    public void modifyOwner(Owner owner) {

        URI uri = URI.create("http://localhost:9191/ownerapi/owner/updateOwner");

        restTemplate.put(uri, owner);

        log.info(new StringBuilder().append("Modified Owner: ").append(owner.toString()).toString());

    }

    public boolean deleteOwner(Owner owner) {

        String uri = "http://localhost:9191/ownerapi/owner/deleteOwner/{id}";

        Map<String, String> params = new HashMap<>();
        params.put("id", owner.getId().toString());

        restTemplate.delete(uri, params);

        return true;
    }
}
