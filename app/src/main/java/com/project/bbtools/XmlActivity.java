package com.project.bbtools;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class XmlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        parsexml();
    }

    private void parsexml() {


        XStream xStream = new XStream(new DomDriver());
        AssetManager assetManager = getBaseContext().getAssets();

        try {

            // xStream.registerConverter((Converter) new MapEntryConverter());
            xStream.alias("Details", Details.class);
            xStream.alias("detaila", Detaila.class);
            xStream.alias("detailb", Detailb.class);
            xStream.alias("detail", String.class);
            xStream.registerConverter(new ToAttributedValueConverter(Detail.class, xStream.getMapper(),
                    xStream.getReflectionProvider(), xStream
                    .getConverterLookup(), "message"));
            xStream.addImplicitCollection(Detaila.class, "detail", Detail.class);
            xStream.addImplicitCollection(Detailb.class, "detail", Detail.class);
            //xStream.addImplicitCollection(Detail.class,"message", String.class);
            xStream.useAttributeFor(Detail.class, "code");

            InputStream inputStream = assetManager.open("detail.xml");
            String xml = readFully(inputStream);
            Toast.makeText(getApplicationContext(), xml, Toast.LENGTH_LONG).show();
            Details details = (Details) xStream.fromXML(xml);
            Log.d("detail", " : " + details.toString());
            Log.d("detail a", " : " + details.detaila.getDetail().toString());
            Log.d("detail b", " : " + details.detailb.getDetail().toString());

            List<Detail> details_of_a = details.detaila.getDetail();
            for (Detail det : details_of_a) {
                Log.d("detail a message", " : " + det.getMessage());
                Log.d("detail a code", " : " + det.getCode());
            }
            List<Detail> details_of_b = details.detailb.getDetail();
            for (Detail det : details_of_b) {
                Log.d("detail b message", " : " + det.getMessage());
                Log.d("detail b code", " : " + det.getCode());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String readFully(InputStream entityResponse) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = entityResponse.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString();
    }

    /*private void parsexml() {
        AssetManager assetManager = getBaseContext().getAssets();

        try {
            InputStream    is = assetManager.open("detail.xml");
            System.out.println("Output from our XML File: ");
            JAXBContext context = JAXBContext.newInstance(Details.class);
            Unmarshaller um = context.createUnmarshaller();
            Details details = (Details)um.unmarshal(is);
            List<String> detailA = details.getDetailA();
            List<String> detailB = details.getDetailB();

            Map<String, String[]> map = new HashMap<String, String[]>();
            map.put("detail-a", detailA.toArray(new String[detailA.size()]));
            map.put("detail-b", detailB.toArray(new String[detailB.size()]));


            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                //key "detail a" value={"attribute 1 of detail a","attribute 2 of detail a","attribute 3 of detail a"}
                System.out.print("Key \"" +entry.getKey()+"\" value={");
                for(int i=0;i<entry.getValue().length;i++){
                    if(i!=entry.getValue().length-1){
                        System.out.print("\""+entry.getValue()[i]+"\",");
                    }
                    else{
                        System.out.print("\""+entry.getValue()[i]+"\"}");
                    }
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }*/


}
