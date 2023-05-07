package com.targetis.gradrole.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Userextra.
 */
@Entity
@Table(name = "userextra")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Userextra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "jobrole")
    private String jobrole;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Userextra id(Long id) {
        this.id = id;
        return this;
    }

    public String getMiddlename() {
        return this.middlename;
    }

    public Userextra middlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getJobrole() {
        return this.jobrole;
    }

    public Userextra jobrole(String jobrole) {
        this.jobrole = jobrole;
        return this;
    }

    public void setJobrole(String jobrole) {
        this.jobrole = jobrole;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Userextra dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public User getUser() {
        return this.user;
    }

    public Userextra user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Userextra)) {
            return false;
        }
        return id != null && id.equals(((Userextra) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Userextra{" +
            "id=" + getId() +
            ", middlename='" + getMiddlename() + "'" +
            ", jobrole='" + getJobrole() + "'" +
            ", dob='" + getDob() + "'" +
            "}";
    }
}
