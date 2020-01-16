package org.easySecurity.server.user.github;

public class GithubUserPlan implements java.io.Serializable {
    private static final long serialVersionUID = 4840806615903981692L;
    private Integer private_repos;
    private String name;
    private Integer collaborators;
    private Integer space;

    public Integer getPrivate_repos() {
        return this.private_repos;
    }

    public void setPrivate_repos(Integer private_repos) {
        this.private_repos = private_repos;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollaborators() {
        return this.collaborators;
    }

    public void setCollaborators(Integer collaborators) {
        this.collaborators = collaborators;
    }

    public Integer getSpace() {
        return this.space;
    }

    public void setSpace(Integer space) {
        this.space = space;
    }
}
