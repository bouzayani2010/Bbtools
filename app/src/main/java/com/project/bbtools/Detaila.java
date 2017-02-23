package com.project.bbtools;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bbouzaiene on 23/02/2017.
 */
@XStreamAlias("detaila")
public class Detaila {
    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    @XStreamImplicit(itemFieldName="detail")
    private List<String> detail= new ArrayList();;
}
