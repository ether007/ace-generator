package com.ace.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;


public class InnerClassSerializablePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
        topLevelClass.addImportedType(serializable);
        topLevelClass.addSuperInterface(serializable);
        List<InnerClass> innerClasses = topLevelClass.getInnerClasses();
        for(InnerClass innerClass:innerClasses){
            innerClass.addSuperInterface(serializable);
        }
        return super.modelExampleClassGenerated(topLevelClass,introspectedTable);
    }
}
