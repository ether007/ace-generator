package com.test;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;

public class TestGener {
    public static void main(String[] args) {

        TopLevelClass topLevelClass = new TopLevelClass(new FullyQualifiedJavaType("com.cc.Top"));

        topLevelClass.setVisibility(JavaVisibility.PUBLIC);

        String name = "id";
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);

        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setType(FullyQualifiedJavaType.getIntInstance());
        field.setName(name);
        field.setInitializationString("-1");


        Field mapper = new Field();
        mapper.addAnnotation("@Autowired");
        mapper.setVisibility(JavaVisibility.PRIVATE);
        mapper.setType(FullyQualifiedJavaType.getIntInstance());
        mapper.setName("mapper");

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
        method.addBodyLine("this." + name + "=" + name + ";");

        topLevelClass.addField(field);
        topLevelClass.addField(mapper);
        topLevelClass.addMethod(method);

        GeneratedJavaFile gif = new GeneratedJavaFile(topLevelClass,"com.cc","utf-8",new DefaultJavaFormatter());

        System.out.println(gif.getFormattedContent());
    }
}
