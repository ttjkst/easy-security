package org.easySecurity.server.user.github;

import org.easySecurity.server.user.UniqueAccessor;

import java.io.Serializable;

public class GithubUser implements Serializable, UniqueAccessor {
    private static final long serialVersionUID = 7281148584651833497L;
    private String gists_url;
    private String repos_url;
    private Boolean two_factor_authentication;
    private String following_url;
    private String bio;
    private String created_at;
    private String login;
    private String type;
    private String blog;
    private Integer private_gists;
    private Integer total_private_repos;
    private String subscriptions_url;
    private String updated_at;
    private Boolean site_admin;
    private Integer disk_usage;
    private Integer collaborators;
    private String company;
    private Integer owned_private_repos;
    private Integer id;
    private Integer public_repos;
    private String gravatar_id;
    private GithubUserPlan plan;
    private String email;
    private String organizations_url;
    private Object hireable;
    private String starred_url;
    private String followers_url;
    private Integer public_gists;
    private String url;
    private String received_events_url;
    private Integer followers;
    private String avatar_url;
    private String events_url;
    private String html_url;
    private Integer following;
    private String name;
    private String location;
    private String node_id;

    public String getGists_url() {
        return this.gists_url;
    }

    public void setGists_url(final String gists_url) {
        this.gists_url = gists_url;
    }

    public String getRepos_url() {
        return this.repos_url;
    }

    public void setRepos_url(final String repos_url) {
        this.repos_url = repos_url;
    }

    public Boolean getTwo_factor_authentication() {
        return this.two_factor_authentication;
    }

    public void setTwo_factor_authentication(final Boolean two_factor_authentication) {
        this.two_factor_authentication = two_factor_authentication;
    }

    public String getFollowing_url() {
        return this.following_url;
    }

    public void setFollowing_url(final String following_url) {
        this.following_url = following_url;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(final String created_at) {
        this.created_at = created_at;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getBlog() {
        return this.blog;
    }

    public void setBlog(final String blog) {
        this.blog = blog;
    }

    public Integer getPrivate_gists() {
        return this.private_gists;
    }

    public void setPrivate_gists( Integer private_gists) {
        this.private_gists = private_gists;
    }

    public Integer getTotal_private_repos() {
        return this.total_private_repos;
    }

    public void setTotal_private_repos(final Integer total_private_repos) {
        this.total_private_repos = total_private_repos;
    }

    public String getSubscriptions_url() {
        return this.subscriptions_url;
    }

    public void setSubscriptions_url(final String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(final String updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getSite_admin() {
        return this.site_admin;
    }

    public void setSite_admin(final Boolean site_admin) {
        this.site_admin = site_admin;
    }

    public Integer getDisk_usage() {
        return this.disk_usage;
    }

    public void setDisk_usage(final Integer disk_usage) {
        this.disk_usage = disk_usage;
    }

    public Integer getCollaborators() {
        return this.collaborators;
    }

    public void setCollaborators(final Integer collaborators) {
        this.collaborators = collaborators;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public Integer getOwned_private_repos() {
        return this.owned_private_repos;
    }

    public void setOwned_private_repos(final Integer owned_private_repos) {
        this.owned_private_repos = owned_private_repos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getPublic_repos() {
        return this.public_repos;
    }

    public void setPublic_repos(final Integer public_repos) {
        this.public_repos = public_repos;
    }

    public String getGravatar_id() {
        return this.gravatar_id;
    }

    public void setGravatar_id(final String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public GithubUserPlan getPlan() {
        return this.plan;
    }

    public void setPlan(final GithubUserPlan plan) {
        this.plan = plan;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getOrganizations_url() {
        return this.organizations_url;
    }

    public void setOrganizations_url(final String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public Object getHireable() {
        return this.hireable;
    }

    public void setHireable(final Object hireable) {
        this.hireable = hireable;
    }

    public String getStarred_url() {
        return this.starred_url;
    }

    public void setStarred_url(final String starred_url) {
        this.starred_url = starred_url;
    }

    public String getFollowers_url() {
        return this.followers_url;
    }

    public void setFollowers_url(final String followers_url) {
        this.followers_url = followers_url;
    }

    public Integer getPublic_gists() {
        return this.public_gists;
    }

    public void setPublic_gists(final Integer public_gists) {
        this.public_gists = public_gists;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getReceived_events_url() {
        return this.received_events_url;
    }

    public void setReceived_events_url(final String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public Integer getFollowers() {
        return this.followers;
    }

    public void setFollowers(final Integer followers) {
        this.followers = followers;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    public void setAvatar_url(final String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getEvents_url() {
        return this.events_url;
    }

    public void setEvents_url(final String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return this.html_url;
    }

    public void setHtml_url(final String html_url) {
        this.html_url = html_url;
    }

    public Integer getFollowing() {
        return this.following;
    }

    public void setFollowing(final Integer following) {
        this.following = following;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getNode_id() {
        return this.node_id;
    }

    public void setNode_id(final String node_id) {
        this.node_id = node_id;
    }

    @Override
    public String uniqueId() {
        return id+"";
    }

    @Override
    public String type() {
        return "Github";
    }
}
