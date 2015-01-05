package cn.originstar.yourjob.system.user.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import cn.originstar.yourjob.base.statable.StatableModel;
import cn.originstar.yourjob.system.organization.models.Organization;
import cn.originstar.yourjob.system.permission.models.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends StatableModel {

    private static final long serialVersionUID = -9197991642378721660L;

    // Basic info
    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String username;

    @ManyToOne(optional = false)
    @NotNull
    private Organization organization;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnore
    private Role systemRole;

    private String firstName;

    private String lastName;

    private String title;

    private String phone;

    private String mobile;

    @org.hibernate.annotations.Type(type = "text")
    private String description;

    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    // Reset password related fields
    @JsonIgnore
    private String passwordResetKey;

    private Date passwordResetRequestOn;

    private Date lastActivityTime;

    private Date lastLoginTime;

    // applicable related fields
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Organization> applicableOrganizations = new ArrayList<Organization>();

    // ---------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Role getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(Role systemRole) {
        this.systemRole = systemRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordResetKey() {
        return passwordResetKey;
    }

    public void setPasswordResetKey(String passwordResetKey) {
        this.passwordResetKey = passwordResetKey;
    }

    public Date getPasswordResetRequestOn() {
        return passwordResetRequestOn;
    }

    public void setPasswordResetRequestOn(Date passwordResetRequestOn) {
        this.passwordResetRequestOn = passwordResetRequestOn;
    }

    public Date getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(Date lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<Organization> getApplicableOrganizations() {
        return applicableOrganizations;
    }

    public void setApplicableOrganizations(List<Organization> applicableOrganizations) {
        this.applicableOrganizations = applicableOrganizations;
    }

    // ---------------------------------------------------
    // Uncategorized Methods
    // ---------------------------------------------------

    /**
     * 列出可供选择的ims的type并封装成map
     * 
     * @return
     */
    public static Map<String, String> mapSocials() {
        HashMap<String, String> ims = new HashMap<String, String>();
        ims.put("Facebook", "Facebook");
        ims.put("GooglePlus", "GooglePlus");
        ims.put("Behance", "Behance");
        ims.put("Blogger", "Blogger");
        ims.put("LinkedIn", "LinkedIn");
        ims.put("MySpace", "MySpace");
        ims.put("Pinterest", "Pinterest");
        ims.put("Path", "Path");
        ims.put("Skype", "Skype");
        ims.put("Spotify", "Spotify");
        ims.put("StumbleUpon", "StumbleUpon");
        ims.put("Twitter", "Twitter");
        ims.put("Vimeo", "Vimeo");
        ims.put("YouTube", "YouTube");
        ims.put("Dribbble", "Dribbble");
        return ims;
    }

}
