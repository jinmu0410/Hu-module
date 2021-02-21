package com.hjb.demo.spring.impor;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("----"+UserService.class.getName());
        return new String[]{UserService.class.getName()};
    }
}
