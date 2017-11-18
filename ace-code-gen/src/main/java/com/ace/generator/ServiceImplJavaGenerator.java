package com.ace.generator;

import com.MBG;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

public class ServiceImplJavaGenerator extends AbstractJavaGenerator {

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();

        String fullModelName = introspectedTable.getBaseRecordType();
        String shortModelName = fullModelName.substring(fullModelName.lastIndexOf(".") + 1);

        String inter = MBG.SERVICE_INTER_PACKAGE+"."+shortModelName+ "Service";
        String impl = MBG.SERVICE_IMPL_PACKAGE+"."+shortModelName+ "ServiceImpl";

        //context.getProperty("");

        //com.xx.model.User
        String model = introspectedTable.getBaseRecordType();
        String mapper = introspectedTable.getMyBatis3JavaMapperType();


        FullyQualifiedJavaType type = new FullyQualifiedJavaType(impl);
        TopLevelClass topLevelClass = new TopLevelClass(type);

        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType(inter));
        topLevelClass.addAnnotation("@Service");

        topLevelClass.addImportedType(inter);
        topLevelClass.addImportedType(mapper);
        topLevelClass.addImportedType(model);
        topLevelClass.addImportedType("org.springframework.beans.factory.annotation.Autowired");
        topLevelClass.addImportedType("org.springframework.stereotype.Service");
        topLevelClass.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        topLevelClass.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));

        Field mapperF = new Field();
        mapperF.addAnnotation("@Autowired");
        mapperF.setVisibility(JavaVisibility.PRIVATE);
        mapperF.setType(new FullyQualifiedJavaType(mapper));
        mapperF.setName("mapper");
        topLevelClass.addField(mapperF);
        impl_method(topLevelClass);


        answer.add(topLevelClass);
        return answer;
    }



    private  void impl_method(TopLevelClass topLevelClass){
        String modelType = introspectedTable.getBaseRecordType();
        FullyQualifiedJavaType model = new FullyQualifiedJavaType(modelType);

        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
        IntrospectedColumn introspectedColumn = introspectedColumns.get(0);
        FullyQualifiedJavaType key = introspectedColumn.getFullyQualifiedJavaType();
        topLevelClass.addImportedType(key);

        //save
        Method save = new Method("save");
        save.setVisibility(JavaVisibility.PUBLIC);
        save.setReturnType(FullyQualifiedJavaType.getIntInstance());
        save.addParameter(new Parameter(model,"orderModel"));
        save.addBodyLine(" return mapper.insertSelective(orderModel);");
        topLevelClass.addMethod(save);


        //update
        Method update = new Method("update");
        update.setVisibility(JavaVisibility.PUBLIC);
        update.setReturnType(FullyQualifiedJavaType.getIntInstance());
        update.addParameter(new Parameter(model,"orderModel"));
        update.addBodyLine(" return mapper.updateByPrimaryKeySelective(orderModel);");
        topLevelClass.addMethod(update);

        //findById(Integer id);
        Method findById = new Method("findById");
        findById.setVisibility(JavaVisibility.PUBLIC);
        findById.setReturnType(model);
        findById.addParameter(new Parameter(key,"id"));
        findById.addBodyLine(" return mapper.selectByPrimaryKey(id);");
        topLevelClass.addMethod(findById);


        // int removeById(Integer id);
        Method removeById = new Method("removeById");
        removeById.setVisibility(JavaVisibility.PUBLIC);
        removeById.setReturnType(FullyQualifiedJavaType.getIntInstance());
        removeById.addParameter(new Parameter(key,"id"));
        removeById.addBodyLine(" return mapper.deleteByPrimaryKey(id);");
        topLevelClass.addMethod(removeById);

        //List<OrderModel> findPageByExample(OrderModelExample orderModelExample);
        Method findPageByExample = new Method("findPageByExample");
        findPageByExample.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType listmodel = FullyQualifiedJavaType.getNewListInstance();
        listmodel.addTypeArgument(model);
        findPageByExample.setReturnType(listmodel);
        findPageByExample.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()),"orderModelExample"));
        findPageByExample.addBodyLine(" return mapper.selectByExample(orderModelExample);");
        topLevelClass.addMethod(findPageByExample);

        //long countByByExample(OrderModelExample orderModelExample);
        Method countByByExample = new Method("countByByExample");
        countByByExample.setVisibility(JavaVisibility.PUBLIC);
        countByByExample.setReturnType(new FullyQualifiedJavaType("long"));
        countByByExample.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()),"orderModelExample"));
        countByByExample.addBodyLine(" return mapper.countByExample(orderModelExample);");
        topLevelClass.addMethod(countByByExample);
    }

}
