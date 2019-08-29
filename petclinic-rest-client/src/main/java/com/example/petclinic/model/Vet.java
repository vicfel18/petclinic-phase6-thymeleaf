package com.example.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vet {

    private Long id;
    private String name;
    private List<Speciality> specialities = new ArrayList<>();

    @JsonIgnoreProperties({"pet","vets"})
    private List<Visit> visits = new ArrayList<>();

    protected Vet() {

    }

    public Vet(String name, List<Speciality> specialities) {
        this.name = name;
        this.specialities = specialities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void addSpeciality(Speciality speciality) {
        this.specialities.add(speciality);
    }

    public void addVisit(Visit visit) {
        this.visits.add(visit);
        visit.getVets().add(this);
    }

    public void removeVisit(Visit visit) {
        this.visits.remove(visit);
        visit.getVets().remove(this);
    }

    public List<Visit> getVisits() {
        return this.visits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vet vet = (Vet) o;
        return Objects.equals(id, vet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vet{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        //sb.append(", specialities=").append(specialities);
        sb.append('}');
        return sb.toString();
    }

    public static VetBuilder builder() {
        return new VetBuilder();
    }

    public static final class VetBuilder {

        private Vet vet;

        private VetBuilder() {
            vet = new Vet();
        }

        public VetBuilder withName(String name) {
            vet.setName(name);
            return this;
        }

        public VetBuilder withSpeciality(Speciality speciality) {
            vet.addSpeciality(speciality);
            return this;
        }

        public VetBuilder withVisit(Visit visit) {
            vet.addVisit(visit);
            visit.getVets().add(vet);
            return this;
        }

        public Vet build() {
            return vet;
        }
    }
}
