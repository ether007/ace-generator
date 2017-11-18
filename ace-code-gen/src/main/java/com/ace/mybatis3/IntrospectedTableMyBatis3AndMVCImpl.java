package com.ace.mybatis3;

import com.MBG;
import com.ace.generator.ControllerJavaGenerator;
import com.ace.generator.ServiceImplJavaGenerator;
import com.ace.generator.ServiceInterfaceJavaGenerator;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

public class IntrospectedTableMyBatis3AndMVCImpl extends IntrospectedTableMyBatis3Impl {

    protected List<AbstractJavaGenerator> serviceImplGenerators;

    protected List<AbstractJavaGenerator> serverInterGenerators;

    protected List<AbstractJavaGenerator> controllerGenerators;

    public IntrospectedTableMyBatis3AndMVCImpl() {
        super();
        serviceImplGenerators = new ArrayList<AbstractJavaGenerator>();
        serverInterGenerators = new ArrayList<AbstractJavaGenerator>();
        controllerGenerators = new ArrayList<AbstractJavaGenerator>();
    }

    @Override
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {

        //model mapper
        List<GeneratedJavaFile> answer = super.getGeneratedJavaFiles();

        //inter
        if (MBG.GENERATOR_SERVICE) {
            for (AbstractJavaGenerator javaGenerator : serverInterGenerators) {
                List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
                for (CompilationUnit compilationUnit : compilationUnits) {
                    GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                            MBG.SERVICE_INTER_PROJECT,
                            context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                            context.getJavaFormatter());
                    answer.add(gjf);
                }
            }

            //service
            for (AbstractJavaGenerator javaGenerator : serviceImplGenerators) {
                List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
                for (CompilationUnit compilationUnit : compilationUnits) {
                    GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                            MBG.SERVICE_IMPL_PROJECT,
                            context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                            context.getJavaFormatter());
                    answer.add(gjf);
                }
            }
        }

        //controller
        if (MBG.GENERATOR_CONTROLLER) {
            for (AbstractJavaGenerator javaGenerator : controllerGenerators) {
                List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
                for (CompilationUnit compilationUnit : compilationUnits) {
                    GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                            MBG.WEB_CONTROLLER_PROJECT,
                            context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                            context.getJavaFormatter());
                    answer.add(gjf);
                }
            }
        }
        return answer;
    }

    @Override
    public void calculateGenerators(List<String> warnings, ProgressCallback progressCallback) {

        super.calculateGenerators(warnings, progressCallback);

        ServiceInterfaceJavaGenerator serviceimplGenerator = new ServiceInterfaceJavaGenerator();
        initializeAbstractGenerator(serviceimplGenerator, warnings, progressCallback);
        serviceImplGenerators.add(serviceimplGenerator);

        ServiceImplJavaGenerator serviceinterGenerator = new ServiceImplJavaGenerator();
        initializeAbstractGenerator(serviceinterGenerator, warnings, progressCallback);
        serverInterGenerators.add(serviceinterGenerator);

        ControllerJavaGenerator controllerGenerator = new ControllerJavaGenerator();
        initializeAbstractGenerator(controllerGenerator, warnings, progressCallback);
        controllerGenerators.add(controllerGenerator);

    }
}
