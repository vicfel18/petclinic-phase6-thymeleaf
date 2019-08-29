package com.example.petclinic.service;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Vet;
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
public class VetService {

    private static final Logger log = LoggerFactory.getLogger(VetService.class);

    private RestTemplate restTemplate;

    public VetService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Vet saveVet(Vet vet) {

        URI uri = URI.create("http://localhost:9193/vetapi/vet/addVet");

        Vet response = restTemplate.postForObject(uri, vet, Vet.class);

        log.info(new StringBuilder().append("Saved Vet: ").append(response.toString()).toString());
        return response;

    }

    public List<Vet> getAllVets() {

        URI uri = URI.create("http://localhost:9193/vetapi/vet/getAllVets");

        List<LinkedHashMap<String, String>> response = restTemplate.getForObject(uri, List.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Vet> vets = mapper.convertValue(response, new TypeReference<List<Vet>>() {
        });

        log.info(new StringBuilder().append("Returning all vets.").toString());
        return vets;

    }
}
