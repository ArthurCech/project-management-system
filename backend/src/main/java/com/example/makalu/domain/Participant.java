package com.example.makalu.domain;

import com.example.makalu.domain.enums.ProjectRole;
import com.example.makalu.domain.pk.ParticipantPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_participant")
// TODO: add createdAt and updatedAt
public class Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ParticipantPK id = new ParticipantPK();
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectRole role;

    public Participant() {
    }

    public Participant(User user,
                       Project project,
                       ProjectRole role) {
        this.id.setUser(user);
        this.id.setProject(project);
        this.role = role;
    }

    public User getUser() {
        return this.id.getUser();
    }

    public void setUser(User user) {
        this.id.setUser(user);
    }

    public Project getProject() {
        return this.id.getProject();
    }

    public void setProject(Project project) {
        this.id.setProject(project);
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
