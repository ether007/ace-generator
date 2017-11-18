package com.ace.generator;

import com.MBG;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.ArrayList;
import java.util.List;

public class ServiceInterfaceJavaGenerator extends AbstractJavaGenerator {

    @Override
    public List<CompilationUnit> getCompilationUnits() {

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();

        String fullModelName = introspectedTable.getBaseRecordType();
        String shortModelName = fullModelName.substring(fullModelName.lastIndexOf(".") + 1);

        String inter = MBG.SERVICE_INTER_PACKAGE+"."+shortModelName+ "Service";


        Interface interfaze = new Interface(inter);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        interfaze.addImportedType(new FullyQualifiedJavaType(fullModelName));
        interfaze.addImportedType(FullyQualifiedJavaType.getNewListInstance());
        interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
        interface_method(interfaze);
        answer.add(interfaze);

        return answer;
    }

    private void interface_method(Interface interfaze) {
        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
        IntrospectedColumn introspectedColumn = introspectedColumns.get(0);
        FullyQualifiedJavaType key = introspectedColumn.getFullyQualifiedJavaType();
        interfaze.addImportedType(key);

        String modelType = introspectedTable.getBaseRecordType();
        FullyQualifiedJavaType model = new FullyQualifiedJavaType(modelType);

        //save
        Method save = new Method("save");
        save.setReturnType(FullyQualifiedJavaType.getIntInstance());
        save.addParameter(new Parameter(model, "data"));
        interfaze.addMethod(save);


        //update
        Method update = new Method("update");
        update.setReturnType(FullyQualifiedJavaType.getIntInstance());
        update.addParameter(new Parameter(model, "data"));
        interfaze.addMethod(update);

        //findById(Integer id);
        Method findById = new Method("findById");
        findById.setReturnType(model);
        findById.addParameter(new Parameter(key, "id"));
        interfaze.addMethod(findById);


        // int removeById(Integer id);
        Method removeById = new Method("removeById");
        removeById.setReturnType(FullyQualifiedJavaType.getIntInstance());
        ;
        removeById.addParameter(new Parameter(key, "id"));
        interfaze.addMethod(removeById);

        //List<OrderModel> findPageByExample(OrderModelExample orderModelExample);
        Method findPageByExample = new Method("findPageByExample");
        FullyQualifiedJavaType listmodel = FullyQualifiedJavaType.getNewListInstance();
        listmodel.addTypeArgument(model);
        findPageByExample.setReturnType(listmodel);
        findPageByExample.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example"));
        interfaze.addMethod(findPageByExample);


        //long countByByExample(OrderModelExample orderModelExample);
        Method countByByExample = new Method("countByByExample");
        countByByExample.setReturnType(new FullyQualifiedJavaType("long"));
        countByByExample.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example"));
        interfaze.addMethod(countByByExample);
    }
}
