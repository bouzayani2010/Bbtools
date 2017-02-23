package com.project.bbtools;

import org.xmlbeam.XBProjector;
import org.xmlbeam.annotation.XBDocURL;
import org.xmlbeam.annotation.XBRead;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bbouzaiene on 23/02/2017.
 */

public class Tetst {

    @XBDocURL("assets://detail.xml")
    public interface Projection {
        @XBRead("name()")
        String getName();

        @XBRead("./detail")
        List<String> getDetailStrings();

        @XBRead("/Details/*")
        List<Projection> getDetails();
    }

    public void xml2Hashmap() throws IOException {
        HashMap<String, List<String>> hashmap = new HashMap<String, List<String>>();
        for (Projection p : new XBProjector().io().fromURLAnnotation(Projection.class).getDetails()) {
            System.out.println(p.getName() + ": " + p.getDetailStrings());
            hashmap.put(p.getName(), p.getDetailStrings());
        }
    }
}
