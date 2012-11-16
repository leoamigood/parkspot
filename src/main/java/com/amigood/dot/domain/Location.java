package com.amigood.dot.domain;

import com.amigood.domain.Coordinates;
import com.amigood.domain.LocationAddress;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 11:45 AM
 */

/**
 *  Additional queries:
 *
 *  Refresh street center coordinates
 *  UPDATE location SET center_lat = (from_lat + to_lat) / 2, center_lng = (from_lng + to_lng) / 2;
 *
 *  Refresh street distance
 *  UPDATE location SET length = sqrt(power(from_lng - to_lng, 2) + power(from_lat - to_lat, 2));
 */

@Entity
@Table(name = "location")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Location implements Serializable {

    public enum Borough {
        B ("Bronx"),
        K ("Brooklyn"),
        S ("Staten Island"),
        M ("Manhattan"),
        Q ("Queens");

        private String name;

        Borough(String borough) {
            this.name = borough;
        }
    }

    @EmbeddedId
    private LocationPk id;

    @OneToMany
    @JoinColumns({@JoinColumn(name = "borough"), @JoinColumn(name = "sign_number")})
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
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
        private String borough;

        @Column(name = "sign_number")
        private String sign;

        public LocationPk() {
        }

        public LocationPk(String borough, String sign) {
            this.borough = borough;
            this.sign = sign;
        }

        public String getBorough() {
            return borough;
        }

        public void setBorough(String borough) {
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
    @Enumerated(EnumType.STRING)
    private Borough borough;

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

    @Column(name = "from_lat")
    private Double fromLat;

    @Column(name = "from_lng")
    private Double fromLng;

    @Column(name = "to_lat")
    private Double toLat;

    @Column(name = "to_lng")
    private Double toLng;

    @Column(name = "center_lat")
    private Double centerLat;

    @Column(name = "center_lng")
    private Double centerLng;

    @Column (name = "center_geo")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point centerGeo;

    @Column
    private Double length;

    @Column
    private Boolean validated;

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }

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

    public void setCoordinates(Coordinates from, Coordinates to) {
        if (from != null) {
            this.fromLat = from.getLatitude();
            this.fromLng = from.getLongitude();
        }
        if (to != null) {
            this.toLat = to.getLatitude();
            this.toLng = to.getLongitude();
        }
    }

    public Double getFromLat() {
        return fromLat;
    }

    public void setFromLat(Double fromLat) {
        this.fromLat = fromLat;
    }

    public Double getFromLng() {
        return fromLng;
    }

    public void setFromLng(Double fromLng) {
        this.fromLng = fromLng;
    }

    public Double getToLat() {
        return toLat;
    }

    public void setToLat(Double toLat) {
        this.toLat = toLat;
    }

    public Double getToLng() {
        return toLng;
    }

    public void setToLng(Double toLng) {
        this.toLng = toLng;
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public Double getCenterLng() {
        return centerLng;
    }

    public void setCenter(Coordinates center, WKTReader wktReader) throws ParseException {
        this.centerLat = center.getLatitude();
        this.centerLng = center.getLongitude();
        this.centerGeo = (Point) wktReader.read(String.format("POINT(%.8f %.8f)", centerLat, centerLng));
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public LocationAddress getAddress(String street) {
        LocationAddress address = new LocationAddress();
        address.setStreet(street);
        address.setCity(borough.name);
        address.setState("NY");

        return address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "borough=" + borough +
                ", sign='" + sign + '\'' +
                ", mainStreet='" + mainStreet + '\'' +
                ", fromStreet='" + fromStreet + '\'' +
                ", toStreet='" + toStreet + '\'' +
                '}';
    }
}
