package com.solvd.app.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {


    @Override
    public Date unmarshal(String date) throws Exception {
        return new SimpleDateFormat("MM - aa - yyyy").parse(date);
    }

    @Override
    public String marshal(Date d) throws Exception {
        return new SimpleDateFormat("MM-aa-yyyy").format(d);
    }
}
