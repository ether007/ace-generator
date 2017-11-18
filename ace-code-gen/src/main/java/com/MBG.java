package com;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MBG {

    private final static String PROJECT_ROOT = "/Users/xcl/ace/ace-generator/ace-base-web/";
    private final static String PROJECT_JAVA = PROJECT_ROOT + "src/main/java";
    private final static String PROJECT_RESOURCE = PROJECT_ROOT + "src/main/resources";


    public final static String MAPPER_PACKAGE = "com.ace.base.mapper";
    public final static String MODEL_PACKAGE = "com.ace.base.model";
    public final static String DAO_PACKAGE = "com.ace.base.dao";

    public final static boolean GENERATOR_SERVICE = true;
    public final static boolean GENERATOR_CONTROLLER = true;

    public final static String SERVICE_INTER_PACKAGE = "com.ace.base.service";
    public final static String SERVICE_INTER_PROJECT = PROJECT_JAVA;

    public final static String SERVICE_IMPL_PACKAGE = "com.ace.base.service.impl";
    public final static String SERVICE_IMPL_PROJECT = PROJECT_JAVA;

    public final static String WEB_CONTROLLER_PACKAGE = "com.ace.base.controller";
    public final static String WEB_CONTROLLER_PROJECT = PROJECT_JAVA;


    public static void main(String[] args) throws InvalidConfigurationException, InterruptedException, SQLException, IOException {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        Context context = new Context(ModelType.FLAT);
        context.setId("MBG-1");
        //context.setTargetRuntime("MyBatis3");
        context.setTargetRuntime("com.ace.mybatis3.IntrospectedTableMyBatis3AndMVCImpl");

        //注释
        CommentGeneratorConfiguration comment = new CommentGeneratorConfiguration();
        comment.addProperty("suppressDate", "false");
        comment.addProperty("suppressAllComments", "true");
        comment.setConfigurationType("org.mybatis.generator.internal.DefaultCommentGenerator");
        context.setCommentGeneratorConfiguration(comment);


        //字段类型
        JavaTypeResolverConfiguration javatype = new JavaTypeResolverConfiguration();
        javatype.setConfigurationType("org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl");
        javatype.addProperty("forceBigDecimals", "false");
        context.setJavaTypeResolverConfiguration(javatype);


        //插件
        PluginConfiguration pc1 = new PluginConfiguration();
        PluginConfiguration pc2 = new PluginConfiguration();
        PluginConfiguration pc3 = new PluginConfiguration();
        pc1.setConfigurationType("com.ace.plugin.InnerClassSerializablePlugin");
        pc1.setConfigurationType("com.ace.plugin.PaginalPlugin");
        pc2.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
        pc3.setConfigurationType("org.mybatis.generator.plugins.EqualsHashCodePlugin");
        pc3.setConfigurationType("org.mybatis.generator.plugins.ToStringPlugin");
        context.addPluginConfiguration(pc1);
        context.addPluginConfiguration(pc2);
        context.addPluginConfiguration(pc3);

        //数据库链接
        JDBCConnectionConfiguration kjdbc = new JDBCConnectionConfiguration();
        kjdbc.setDriverClass("com.mysql.jdbc.Driver");
        kjdbc.setConnectionURL("jdbc:mysql://127.0.0.1:3306/base");
        kjdbc.setUserId("root");
        kjdbc.setPassword("toor");
        context.setJdbcConnectionConfiguration(kjdbc);



        //mapper
        JavaClientGeneratorConfiguration javaclient = new JavaClientGeneratorConfiguration();
        javaclient.setTargetPackage(DAO_PACKAGE);
        javaclient.setTargetProject(PROJECT_JAVA);
        //XMLMAPPER ANNOTATEDMAPPER  MIXEDMAPPER
        javaclient.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaclient);

        //model
        JavaModelGeneratorConfiguration javamodel = new JavaModelGeneratorConfiguration();
        javamodel.setTargetPackage(MODEL_PACKAGE);
        javamodel.setTargetProject(PROJECT_JAVA);
        context.setJavaModelGeneratorConfiguration(javamodel);

        //xml
        SqlMapGeneratorConfiguration sqlmap = new SqlMapGeneratorConfiguration();
        sqlmap.setTargetPackage(MAPPER_PACKAGE);
        sqlmap.setTargetProject(PROJECT_RESOURCE);
        context.setSqlMapGeneratorConfiguration(sqlmap);


        //数据库表
        TableConfiguration tbc1 = new TableConfiguration(context);
        tbc1.setTableName("t_user");
        tbc1.setDomainObjectName("User");
        GeneratedKey gk = new GeneratedKey("id", "MySql", true, "");
        tbc1.setGeneratedKey(gk);
        context.addTableConfiguration(tbc1);


        Configuration config = new Configuration();
        config.addContext(context);

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

        //todo
        List<GeneratedJavaFile> gjf = myBatisGenerator.getGeneratedJavaFiles();
        for (GeneratedJavaFile j : gjf) {
            System.out.println(j.getFileName());
        }

    }
}
