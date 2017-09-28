package com.sjsu.architects;

import java.util.Date;

public class Promotion {

    private String promotionID = null;
    private Date promotionStartDate = new Date();
    private Date promotionEndDate = new Date();
    private boolean promotionValidity;
    private double discountRatio;

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public Date getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(Date promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public Date getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(Date promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    public boolean getPromotionValidity() {
        return promotionValidity;
    }

    public void setPromotionValidity(boolean promotionValidity) {
        this.promotionValidity = promotionValidity;
    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }




}
