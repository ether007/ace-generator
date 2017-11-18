package com.ace.generator;

import com.MBG;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

public class ControllerJavaGenerator extends AbstractJavaGenerator {

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();

        String fullModelName = introspectedTable.getBaseRecordType();
        String shortModelName = fullModelName.substring(fullModelName.lastIndexOf(".") + 1);

        String inter = MBG.SERVICE_INTER_PACKAGE+"."+shortModelName+ "Service";
        String ctrname  = MBG.WEB_CONTROLLER_PACKAGE+"."+shortModelName+"Controller";

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(ctrname);

        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addImportedType("org.springframework.stereotype.Controller");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.RequestMapping");
        topLevelClass.addImportedType("org.springframework.beans.factory.annotation.Autowired");
        topLevelClass.addImportedType("org.springframework.web.bind.ServletRequestUtils");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.ResponseBody");
        topLevelClass.addImportedType("javax.servlet.http.HttpServletRequest");
        topLevelClass.addImportedType("com.ace.base.beans.Resp");
        topLevelClass.addImportedType("com.ace.base.beans.RespPage");
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        topLevelClass.addImportedType(inter);
        topLevelClass.addImportedType(fullModelName);
        topLevelClass.addImportedType(introspectedTable.getExampleType());

        topLevelClass.addAnnotation("@Controller");
        topLevelClass.addAnnotation("@RequestMapping(\"/"+shortModelName+"\")");


        Field service = new Field();
        service.addAnnotation("@Autowired");
        service.setVisibility(JavaVisibility.PRIVATE);
        service.setType(new FullyQualifiedJavaType(inter));
        service.setName("service");
        topLevelClass.addField(service);


        //list

        FullyQualifiedJavaType request = new FullyQualifiedJavaType("javax.servlet.http.HttpServletRequest");
        FullyQualifiedJavaType resp = new FullyQualifiedJavaType("com.ace.base.beans.Resp");

        Method mlist = new Method("list");
        mlist.addAnnotation("@RequestMapping(\"/list\")");
        mlist.addAnnotation("@ResponseBody");
        mlist.setVisibility(JavaVisibility.PUBLIC);
        mlist.addParameter(new Parameter(request,"request"));
        mlist.setReturnType(resp);
        mlist.addBodyLine("int pageNo = ServletRequestUtils.getIntParameter(request, \"pageNo\", 1);");
        mlist.addBodyLine("int pageSize = ServletRequestUtils.getIntParameter(request, \"pageSize\", 10);");

        String ex = introspectedTable.getExampleType();
        ex = ex.substring(ex.lastIndexOf(".")+1);

        mlist.addBodyLine(ex+" example = new "+ex+"();");
        mlist.addBodyLine("example.setLimitStart((pageNo - 1) * pageSize);");
        mlist.addBodyLine("example.setLimitLength(pageSize);");
        mlist.addBodyLine("long rowcount = service.countByByExample(example);");
        mlist.addBodyLine("List<"+shortModelName+"> list = service.findPageByExample(example);");

        mlist.addBodyLine("return new Resp(1, \"success\", new RespPage<"+shortModelName+">(pageNo, pageSize, rowcount, list));");
        topLevelClass.addMethod(mlist);


        Method findById = new Method("findById");
        findById.addAnnotation("@RequestMapping(\"/get\")");
        findById.addAnnotation("@ResponseBody");
        findById.setVisibility(JavaVisibility.PUBLIC);
        findById.addParameter(new Parameter(request,"request"));
        findById.addParameter(new Parameter(new FullyQualifiedJavaType("long"),"id"));
        findById.setReturnType(resp);
        findById.addBodyLine("return new Resp(1, \"save-success\", service.findById(id));");
        topLevelClass.addMethod(findById);

        //save
        Method save = new Method("save");

        save.addAnnotation("@RequestMapping(\"/save\")");
        save.addAnnotation("@ResponseBody");
        save.setVisibility(JavaVisibility.PUBLIC);
        save.addParameter(new Parameter(request,"request"));
        save.addParameter(new Parameter(new FullyQualifiedJavaType(fullModelName),"base"));
        save.addBodyLine("service.save(base);");
        save.addBodyLine("return new Resp(1, \"save-success\");");
        save.setReturnType(resp);
        topLevelClass.addMethod(save);

        //update
        Method update = new Method("update");
        update.addAnnotation("@RequestMapping(\"/update\")");
        update.addAnnotation("@ResponseBody");
        update.setVisibility(JavaVisibility.PUBLIC);
        update.addParameter(new Parameter(request,"request"));
        update.addParameter(new Parameter(new FullyQualifiedJavaType(fullModelName),"base"));
        update.addBodyLine("service.update(base);");
        update.addBodyLine("return new Resp(1, \"update-success\");");
        update.setReturnType(resp);
        topLevelClass.addMethod(update);

        //remove
        Method remove = new Method("remove");
        remove.addAnnotation("@RequestMapping(\"/remove\")");
        remove.addAnnotation("@ResponseBody");
        remove.setVisibility(JavaVisibility.PUBLIC);
        remove.addParameter(new Parameter(request,"request"));
        remove.addParameter(new Parameter(new FullyQualifiedJavaType("long"),"id"));
        remove.setReturnType(resp);
        remove.addBodyLine("service.removeById(id);");
        remove.addBodyLine("return new Resp(1, \"remove-success\");");
        topLevelClass.addMethod(remove);

        answer.add(topLevelClass);
        return answer;
    }
}
