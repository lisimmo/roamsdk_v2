package ng.shoppi.roamsdk.model;

import java.util.ArrayList;

/**
 * Created by User on 2/17/2017.
 */
public class FieldForSubmission {

    private double lat;
    private double lng;
    private ArrayList<FormData> formData;
    private int formId;
    private Location location;

    public FieldForSubmission() {
        formData = new ArrayList<>();
        location = new Location();
    }

    public ArrayList<FormData> getFormData() {
        return formData;
    }

    public void setFormData(ArrayList<FormData> formData) {
        this.formData = formData;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public class FormData {
        private int pageId;
        private ArrayList<PageData> pageData;

        public FormData() {
            pageData = new ArrayList<>();
        }

        public ArrayList<PageData> getPageData() {
            return pageData;
        }

        public void setPageData(ArrayList<PageData> pageData) {
            this.pageData = pageData;
        }

        public int getPageId() {
            return pageId;
        }

        public void setPageId(int pageId) {
            this.pageId = pageId;
        }
    }

    public class PageData {
        private int fieldId;
        private String fieldValue = "";

        public PageData() {
        }

        public int getFieldId() {
            return fieldId;
        }

        public void setFieldId(int fieldId) {
            this.fieldId = fieldId;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
    }

    public class Location {
        private double lat;
        private double lng;

        public Location() {
        }

        public Location(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
