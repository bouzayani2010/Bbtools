package com.project.bbtools;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbouzaiene on 23/02/2017.
 */
@XStreamAlias("detailb")
public class Detailb {
    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    @XStreamImplicit(itemFieldName="detail")
    private List<Detail> detail= new ArrayList();;

}
