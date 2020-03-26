package com.xwy.poi.service;

import com.xwy.poi.entity.Reqbase;

import java.io.File;
import java.util.List;

public interface IPoiService {

    List<Reqbase> readWord07(File wordFile);

    List<Reqbase> readWord03(File wordFile);
}
