package com.project.bbtools;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by bbouzaiene on 23/02/2017.
 */

@XStreamAlias("detail")
public class Detail {
    public void setMessage(String message) {
        this.message = message;
    }

    String message;

    public void setCode(String code) {
        this.code = code;
    }

    @XStreamAlias("code")
    String code;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
