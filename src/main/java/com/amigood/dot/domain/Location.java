package com.amigood.dot.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 11:45 AM
 */
@Entity
@Table(name="location")
public class Location implements Serializable {

    @EmbeddedId
    private LocationPk id;

    @OneToMany
    @JoinColumns({@JoinColumn(name = "borough"), @JoinColumn(name = "sign_number")})
    private List<ParkingSign> signs;

    public List<ParkingSign> getSigns() {
        return signs;
    }

    public void setSigns(List<ParkingSign> signs) {
        this.signs = signs;
    }

    @Embeddable
    static public class LocationPk implements Serializable {
        @Column
        private Character borough;

        @Column(name = "sign_number")
        private String sign;

        public LocationPk() {
        }

        public LocationPk(Character borough, String sign) {
            this.borough = borough;
            this.sign = sign;
        }

        public Character getBorough() {
            return borough;
        }

        public void setBorough(Character borough) {
            this.borough = borough;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LocationPk that = (LocationPk) o;

            if (borough != null ? !borough.equals(that.borough) : that.borough != null) return false;
            if (sign != null ? !sign.equals(that.sign) : that.sign != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = borough != null ? borough.hashCode() : 0;
            result = 31 * result + (sign != null ? sign.hashCode() : 0);
            return result;
        }
    }

    @Column(insertable=false, updatable=false)
    private Character borough;

    @Column(name = "sign_number", insertable=false, updatable=false)
    private String sign;

    @Column(name = "main_street")
    private String mainStreet;

    @Column(name = "from_street")
    private String fromStreet;

    @Column(name = "to_street")
    private String toStreet;

    @Column
    private String orientation;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMainStreet() {
        return mainStreet;
    }

    public void setMainStreet(String mainStreet) {
        this.mainStreet = mainStreet;
    }

    public String getFromStreet() {
        return fromStreet;
    }

    public void setFromStreet(String fromStreet) {
        this.fromStreet = fromStreet;
    }

    public String getToStreet() {
        return toStreet;
    }

    public void setToStreet(String toStreet) {
        this.toStreet = toStreet;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

}
