package com.project.bbtools;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by bbouzaiene on 23/02/2017.
 */
@XStreamAlias("Details")
public class Details {
    public Detaila detaila;

    public Detailb getDetailb() {
        return detailb;
    }

    public void setDetailb(Detailb detailb) {
        this.detailb = detailb;
    }

    public Detailb detailb;

    public Detaila getDetaila() {
        return detaila;
    }

    public void setDetaila(Detaila detaila) {
        this.detaila = detaila;
    }
}
