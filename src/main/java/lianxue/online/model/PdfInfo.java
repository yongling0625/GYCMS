package lianxue.online.model;

import javax.persistence.*;

@Table(name = "pdf_info")
public class PdfInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "pdf_address")
    private String pdfAddress;

    @Column(name = "picture_address")
    private String pictureAddress;

    private String type;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return pdf_address
     */
    public String getPdfAddress() {
        return pdfAddress;
    }

    /**
     * @param pdfAddress
     */
    public void setPdfAddress(String pdfAddress) {
        this.pdfAddress = pdfAddress;
    }

    /**
     * @return picture_address
     */
    public String getPictureAddress() {
        return pictureAddress;
    }

    /**
     * @param pictureAddress
     */
    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}