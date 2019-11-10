package es.uniovi.ips.hospital.service;

import es.uniovi.ips.hospital.domain.ICD10;
import es.uniovi.ips.hospital.repository.ICD10Repository;
import es.uniovi.ips.hospital.util.comunication.CSVLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("ICD10Service")
public class ICD10Service {

    @Autowired
    private ICD10Repository icd10Repository;

    public void load() {
        try {
            File codesFile = ResourceUtils.getFile("classpath:codes.csv");
            InputStream targetStream = new FileInputStream(codesFile);
            icd10Repository.saveAll(CSVLoader.read(ICD10.class, targetStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ICD10> findAll() {
        return icd10Repository.findAll();
    }

    public List<String> findAllCategories() {
        return icd10Repository.findCategories();
    }
}
