package lianxue.online.model;

import javax.persistence.*;

public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "corporate_name")
    private String corporateName;

    private String address;

    private String contacts;

    private String city;

    private String country;

    @Column(name = "company_website")
    private String companyWebsite;

    private String email;

    private String tel;

    private String fax;

    private String problem;

    @Column(name = "problem_description")
    private String problemDescription;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return corporate_name
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     * @param corporateName
     */
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return company_website
     */
    public String getCompanyWebsite() {
        return companyWebsite;
    }

    /**
     * @param companyWebsite
     */
    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return problem
     */
    public String getProblem() {
        return problem;
    }

    /**
     * @param problem
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * @return problem_description
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @param problemDescription
     */
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }
}