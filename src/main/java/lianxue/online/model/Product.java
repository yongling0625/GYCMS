package lianxue.online.model;

import javax.persistence.*;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category_id")
    private Integer categoryId;

    private String overview;

    @Column(name = "core_parameter")
    private String coreParameter;

    @Column(name = "image_address")
    private String imageAddress;

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
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return category_id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * @return core_parameter
     */
    public String getCoreParameter() {
        return coreParameter;
    }

    /**
     * @param coreParameter
     */
    public void setCoreParameter(String coreParameter) {
        this.coreParameter = coreParameter;
    }

    /**
     * @return image_address
     */
    public String getImageAddress() {
        return imageAddress;
    }

    /**
     * @param imageAddress
     */
    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}