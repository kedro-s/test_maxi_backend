package com.maxi.test.components;

import com.maxi.test.dao.check.Check;
import com.maxi.test.dao.salelines.SaleLine;

public interface XmlProcessor {
    void process(Check check, Iterable<SaleLine> lines);
}
