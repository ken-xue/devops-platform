package io.kenxue.cicd.infrastructure.util;

import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper.CodeGeneratorMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午7:58
 * @Desc: 代码生成器
 */
@Component
@Slf4j
public class CodeGenHandler {

    @Resource
    private CodeGeneratorMapper codeGeneratorMapper;

    public void execute(Configuration configuration) {
        String[] tableNames = configuration.getStringArray("tableName");
        Arrays.stream(tableNames).forEach(v-> {
            Assert.notNull(v,"tableName must not null");
            //2.查询表结构
            Map<String, String> table = codeGeneratorMapper.queryTable(v);
            //3.查询列信息
            List<Map<String, String>> columns = codeGeneratorMapper.queryColumns(v);
            //4.排除字段
            List<String> exclude = Arrays.asList(configuration.getStringArray("exclude"));
            columns.removeIf(map -> exclude.contains(map.get("columnName")));
            //5.生成文件
            generatorCode(table, columns, configuration);
        });
    }


    public void generatorCode(Map<String, String> table, List<Map<String, String>> columns, Configuration config) {
        //配置信息
        boolean hasBigDecimal = false;
        boolean hasList = false;
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment"));
        //表名转换成Java类名
        String className = table2Java(tableEntity.getTableName(), config.getStringArray("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));
        tableEntity.setAllLowName(StringUtils.lowerCase(className));
        //列信息
        List<ColumnEntity> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));
            //列名转换成Java属性名
            String attrName = column2Java(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), column2Java(columnEntity.getDataType()));
            columnEntity.setAttrType(attrType);

            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasList && "array".equals(columnEntity.getExtra())) {
                hasList = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("allLowName", tableEntity.getAllLowName());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasList", hasList);
        map.put("package", config.getString("package"));
        map.put("moduleName", config.getString("moduleName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        Map<String, String> templates = getTemplates(tableEntity.getClassName(), config.getString("moduleName"));
        List fileNames = new ArrayList<String>();
        for (String template : templates.keySet()) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate("template/" + template, "UTF-8");
            tpl.merge(context, sw);
            try {
                String file = templates.get(template);
                //判断文件夹是否存在,不存在则创建
                String dir = file.substring(0, file.lastIndexOf("/"));
                File folder = new File(dir);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                String fileName = templates.get(template) + template.replace(".vm", "").replace("Domain", tableEntity.getClassName());
                fileNames.add(fileName);
                IOUtils.write(sw.toString(), new FileOutputStream(fileName), "UTF-8");
                IOUtils.closeQuietly(sw);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        storeFileNames(fileNames);
    }

    /**
     * 获取模板
     *
     * @return
     */
    public Map<String, String> getTemplates(String className, String moduleName) {
        //key:模板路径,value:生成后的路径
        Map<String, String> templates = new HashMap<>();
        //view
        templates.put("Domain.js.vm", "../doc/view/" + moduleName + "/");
        templates.put("index.vue.vm", "../doc/view/" + moduleName + "/");
        //sql
        templates.put("DomainMenu.sql.vm", "../doc/sql/" + moduleName + "/");
        templates.put("Domain.java.vm", "../core/domain/src/main/java/io/kenxue/cicd/domain/domain/" + moduleName + "/");
        //exe
        templates.put("DomainAddCmdExe.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/command/");
        templates.put("DomainDeleteCmdExe.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/command/");
        templates.put("DomainGetQryExe.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/command/query/");
        templates.put("DomainListQryExe.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/command/query/");
        templates.put("DomainUpdateCmdExe.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/command/");
        templates.put("DomainPageQryExe.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/command/query/");
        //service
        templates.put("DomainAppAppServiceImpl.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/" + className.toLowerCase() + "/service/");
        templates.put("DomainAppService.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/api/"+moduleName+"/");
        //dto
        templates.put("DomainDTO.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/"+className.toLowerCase()+"/");
        //do
        templates.put("DomainDO.java.vm", "../core/infrastructure/src/main/java/io/kenxue/cicd/infrastructure/repositoryimpl/"+moduleName+"/database/dataobject/");
        //Repository
        templates.put("DomainRepository.java.vm", "../core/domain/src/main/java/io/kenxue/cicd/domain/repository/"+moduleName+"/");
        templates.put("DomainRepositoryImpl.java.vm", "../core/infrastructure/src/main/java/io/kenxue/cicd/infrastructure/repositoryimpl/"+moduleName+"/");
        //mapper
        templates.put("DomainMapper.java.vm", "../core/infrastructure/src/main/java/io/kenxue/cicd/infrastructure/repositoryimpl/"+moduleName+"/database/mapper/");
        templates.put("DomainMapper.xml.vm", "../core/infrastructure/src/main/resources/mybatis/");
        //controller
//        templates.put("DomainController.java.vm", "../core/adapter/src/main/java/io/kenxue/cicd/adapter/rest/" + moduleName + "/");
        //cmd
        templates.put("DomainAddCmd.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/" + className.toLowerCase() + "/");
        templates.put("DomainDeleteCmd.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/"  + className.toLowerCase() + "/");
        templates.put("DomainUpdateCmd.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/"  + className.toLowerCase() + "/");
        templates.put("DomainPageQry.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/" +moduleName+"/" + className.toLowerCase() + "/");
        templates.put("DomainListQry.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/"  + className.toLowerCase() + "/");
        templates.put("DomainGetQry.java.vm", "../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/"  + className.toLowerCase() + "/");
        //domain factory
        templates.put("DomainFactory.java.vm", "../core/domain/src/main/java/io/kenxue/cicd/domain/factory/"+moduleName+"/");
        //do convert
        templates.put("Domain2DOConvector.java.vm", "../core/infrastructure/src/main/java/io/kenxue/cicd/infrastructure/repositoryimpl/"+moduleName+"/database/convertor/");
        templates.put("Domain2DOMapStruct.java.vm", "../core/infrastructure/src/main/java/io/kenxue/cicd/infrastructure/repositoryimpl/"+moduleName+"/database/convertor/");
        //dto assembler
        templates.put("Domain2DTOAssembler.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/"+ className.toLowerCase() + "/assembler/");
        templates.put("Domain2DTOMapStruct.java.vm", "../core/application/src/main/java/io/kenxue/cicd/application/" +moduleName+"/"+ className.toLowerCase() + "/assembler/");
        //event handler
        templates.put("DomainDeleteEventHandler.java.vm","../core/application/src/main/java/io/kenxue/cicd/application/"+moduleName+"/"+className.toLowerCase()+"/handler/");
        templates.put("DomainUpdateEventHandler.java.vm","../core/application/src/main/java/io/kenxue/cicd/application/"+moduleName+"/"+className.toLowerCase()+"/handler/");
        templates.put("DomainCreateEventHandler.java.vm","../core/application/src/main/java/io/kenxue/cicd/application/"+moduleName+"/"+className.toLowerCase()+"/handler/");
        //event
        templates.put("DomainCreateEvent.java.vm","../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/" +className.toLowerCase()+"/event/");
        templates.put("DomainDeleteEvent.java.vm","../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/" +className.toLowerCase()+"/event/");
        templates.put("DomainUpdateEvent.java.vm","../core/core-client/src/main/java/io/kenxue/cicd/coreclient/dto/"+moduleName+"/" +className.toLowerCase()+"/event/");
        return templates;
    }

    /**
     * 列名转换成Java属性名
     */
    public String column2Java(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public String table2Java(String tableName, String[] tablePrefixArray) {
        if (null != tablePrefixArray && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                if (tableName.startsWith(tablePrefix)) {
                    tableName = tableName.replaceFirst(tablePrefix, "");
                }
            }
        }
        return column2Java(tableName);
    }


    private void storeFileNames(List<String> fileNames) {
        try {
            FileWriter fw = new FileWriter("/tmp/fileNameList.txt");
            for (String s : fileNames) {
                s="/Users/biaoyang/IdeaProjects/cicd-platform"+s.replace("..","");
                fw.write(s+"\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除上次生成的文件
     *
     * @return
     */
    public void deleteGenFile() {
        try {
            FileInputStream inputStream = new FileInputStream("/tmp/fileNameList.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String fileName = null;
            while ((fileName = bufferedReader.readLine()) != null) {
                File file = new File(fileName);
                if (!file.exists()) {
                    log.error("删除文件失败：{} 文件不存在", fileName);
                } else {
                    file.delete();
                }
            }
            inputStream.close();
            bufferedReader.close();
        }catch (Exception e){
            log.error("执行删除异常：{} ", e.getMessage());
        }
    }
}

