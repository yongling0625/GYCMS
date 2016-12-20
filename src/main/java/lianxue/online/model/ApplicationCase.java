package lianxue.online.model;

import javax.persistence.*;

@Table(name = "application_case")
public class ApplicationCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "application_case_category")
    private Byte applicationCaseCategory;

    @Column(name = "application_case_name")
    private String applicationCaseName;

    @Column(name = "application_case_images")
    private String applicationCaseImages;

    @Column(name = "application_case_info")
    private String applicationCaseInfo;

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
     * @return application_case_category
     */
    public Byte getApplicationCaseCategory() {
        return applicationCaseCategory;
    }

    /**
     * @param applicationCaseCategory
     */
    public void setApplicationCaseCategory(Byte applicationCaseCategory) {
        this.applicationCaseCategory = applicationCaseCategory;
    }

    /**
     * @return application_case_name
     */
    public String getApplicationCaseName() {
        return applicationCaseName;
    }

    /**
     * @param applicationCaseName
     */
    public void setApplicationCaseName(String applicationCaseName) {
        this.applicationCaseName = applicationCaseName;
    }

    /**
     * @return application_case_images
     */
    public String getApplicationCaseImages() {
        return applicationCaseImages;
    }

    /**
     * @param applicationCaseImages
     */
    public void setApplicationCaseImages(String applicationCaseImages) {
        this.applicationCaseImages = applicationCaseImages;
    }

    /**
     * @return application_case_info
     */
    public String getApplicationCaseInfo() {
        return applicationCaseInfo;
    }

    /**
     * @param applicationCaseInfo
     */
    public void setApplicationCaseInfo(String applicationCaseInfo) {
        this.applicationCaseInfo = applicationCaseInfo;
    }
}