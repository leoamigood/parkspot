package com.amigood.dot.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author leo@amigood.com | Leo Amigood, Chain Tale LLC
 *         Date: 9/7/12
 *         Time: 11:08 AM
 */
@Entity
@Table(name="signs")
public class ParkingSign implements Serializable {

    @EmbeddedId
    private ParkingSignPk id;

    static public class ParkingSignPk implements Serializable {
        @Column
        private String borough;

        @Column(name = "sign_number")
        private String sign;

        @Column
        private Integer order;

        public ParkingSignPk() {
        }

        public ParkingSignPk(String borough, String sign, Integer order) {
            this.borough = borough;
            this.sign = sign;
            this.order = order;
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

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ParkingSignPk that = (ParkingSignPk) o;

            if (borough != that.borough) return false;
            if (order != null ? !order.equals(that.order) : that.order != null) return false;
            if (sign != null ? !sign.equals(that.sign) : that.sign != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = borough != null ? borough.hashCode() : 0;
            result = 31 * result + (sign != null ? sign.hashCode() : 0);
            result = 31 * result + (order != null ? order.hashCode() : 0);
            return result;
        }
    }

    @Column(insertable=false, updatable=false)
    private Character borough;

    @Column(name="sign_number", insertable=false, updatable=false)
    private String sign;

    @Column(insertable=false, updatable=false)
    private Integer order;

    @Column
    private String description;

    @Column
    private Long distance;

    @Column
    private String direction;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
