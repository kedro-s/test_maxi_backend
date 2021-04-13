package com.maxi.test.components;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

@Component
public class XmlFileNameFilter implements FilenameFilter {


    public XmlFileNameFilter() {
    }

    @Override
    public boolean accept(File file, String name) {
        return name.toLowerCase().endsWith(".xml");
    }
}
