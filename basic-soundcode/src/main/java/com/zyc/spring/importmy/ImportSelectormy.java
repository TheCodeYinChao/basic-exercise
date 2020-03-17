package com.zyc.spring.importmy;

import com.zyc.spring.A;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by Admin on 2020/3/5.
 */
public class ImportSelectormy implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{ImPortBena.class.getName()};
    }
}
