package com.example.makalu.domain.pk;

import com.example.makalu.domain.Project;
import com.example.makalu.domain.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ParticipantPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public ParticipantPK() {
    }

    public ParticipantPK(User user,
                         Project project) {
        this.user = user;
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantPK that = (ParticipantPK) o;
        return user.equals(that.user) && project.equals(that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, project);
    }

}
