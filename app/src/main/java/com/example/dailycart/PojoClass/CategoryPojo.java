package com.example.dailycart.PojoClass;
/**
 *
 * @author Kerin  Shah
 */
public class CategoryPojo {

    String category_image_url;
    String category_name;
    Boolean Prescription;

    public Boolean getPrescription() {
        return Prescription;
    }

    public void setPrescription(Boolean prescription) {
        Prescription = prescription;
    }

    public String getCategory_image_url() {
        return category_image_url;
    }

    public void setCategory_image_url(String category_image_url) {
        this.category_image_url = category_image_url;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
