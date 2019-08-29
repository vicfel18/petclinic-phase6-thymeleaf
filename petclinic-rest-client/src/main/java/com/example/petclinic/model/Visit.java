package com.example.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Visit {

    private Long id;
    private Date dateOfVisit;
    private String description;

    @JsonIgnoreProperties({"owner","visits"})
    private Pet pet;

    @JsonIgnoreProperties("visits")
    private List<Vet> vets = new ArrayList<>();

    protected Visit() {

    }

    public Visit(Date dateOfVisit, String description) {
        this.dateOfVisit = dateOfVisit;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addVet(Vet vet) {
        this.vets.add(vet);
        vet.getVisits().add(this);
    }

    public void removeVet(Vet vet) {
        this.vets.remove(vet);
        vet.getVisits().remove(this);
    }

    public List<Vet> getVets() {
        return this.vets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(id, visit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visit{");
        sb.append("id=").append(id);
        sb.append(", dateOfVisit=").append(dateOfVisit);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static VisitBuilder builder() {
        return new VisitBuilder();
    }

    public static final class VisitBuilder {

        private Visit visit;

        private VisitBuilder() {
            visit = new Visit();
        }

        public VisitBuilder withDateOfVisit(Date dateOfVisit) {
            visit.setDateOfVisit(dateOfVisit);
            return this;
        }

        public VisitBuilder withDescription(String description) {
            visit.setDescription(description);
            return this;
        }

        public VisitBuilder withPet(Pet pet) {
            visit.setPet(pet);
            return this;
        }

        public VisitBuilder withVet(Vet vet) {
            visit.addVet(vet);
            vet.getVisits().add(visit);
            return this;
        }

        public Visit build() {
            return visit;
        }
    }
}
