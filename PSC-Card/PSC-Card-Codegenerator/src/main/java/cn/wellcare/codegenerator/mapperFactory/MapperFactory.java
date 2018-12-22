package cn.wellcare.codegenerator.mapperFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import cn.wellcare.codegenerator.mapperFactory.pojo.Column;
import cn.wellcare.codegenerator.mapperFactory.pojo.Table;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 代码生成器
 * 
 * @author zhaihl
 *
 */
@SuppressWarnings("unused")
public class MapperFactory {

	private static Map<String, String> javaSqlTypeTransferMap;
	private static Properties prop;
	private static String currentLocation;

	static {
		javaSqlTypeTransferMap = new HashMap<String, String>();
		javaSqlTypeTransferMap.put("integer", "java.lang.Integer");
		javaSqlTypeTransferMap.put("float", "java.lang.Float");
		javaSqlTypeTransferMap.put("varchar", "java.lang.String");
		javaSqlTypeTransferMap.put("character varying", "java.lang.String");
		javaSqlTypeTransferMap.put("character", "java.lang.String");
		javaSqlTypeTransferMap.put("char", "java.lang.String");
		javaSqlTypeTransferMap.put("decimal", "java.math.BigDecimal");
		javaSqlTypeTransferMap.put("numeric", "java.math.BigDecimal");
		javaSqlTypeTransferMap.put("datetime", "java.util.Date");
		javaSqlTypeTransferMap.put("timestamp", "java.util.Date");
		javaSqlTypeTransferMap.put("timestamp with time zone", "java.util.Date");
		javaSqlTypeTransferMap.put("timestamp without time zone", "java.util.Date");
		javaSqlTypeTransferMap.put("date", "java.util.Date");
		javaSqlTypeTransferMap.put("text", "java.lang.String");
		javaSqlTypeTransferMap.put("bit", "java.lang.Integer");
		javaSqlTypeTransferMap.put("tinyint", "java.lang.Integer");
		javaSqlTypeTransferMap.put("longtext", "java.lang.String");
		javaSqlTypeTransferMap.put("bigint", "java.lang.Long");
		javaSqlTypeTransferMap.put("mediumtext", "java.lang.String");
		javaSqlTypeTransferMap.put("smallint", "java.lang.Integer");
		javaSqlTypeTransferMap.put("text", "java.lang.String");
		prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("config.properties");
		try {
			prop.load(stream);
			currentLocation = System.getProperty("user.dir").replaceAll("\\\\", "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mapper() {
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("pass");
		String basePackage = prop.getProperty("package.base");
		String modularname = prop.getProperty("package.modular");

		String controllerPackage = basePackage + ".controller." + modularname;
		String entityPackage = basePackage + ".entity." + modularname;
		String daoPackage = basePackage + ".dao." + modularname;
		String modelPackage = basePackage + ".model." + modularname;
		String servicePackage = basePackage + ".service." + modularname;
		String sessionFactoryBeanName = prop.getProperty("sql.session.factory");
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet dbNameRs = st.executeQuery(
					"SELECT  tablename   FROM   pg_tables  WHERE   tablename   NOT   LIKE   'pg%'  AND tablename NOT LIKE 'sql_%'ORDER   BY   tablename;");
			List<String> includeTables = this.getIncludeTables(prop.getProperty("include.tables"));
			while (dbNameRs.next()) {
				if ((includeTables != null && includeTables.contains(dbNameRs.getString(1))) || includeTables == null) {
					this.mapperTable(dbNameRs.getString(1), con, daoPackage, entityPackage, sessionFactoryBeanName,
							modelPackage, servicePackage, controllerPackage);
				}
			}

			dbNameRs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		}
	}

	private String upcaseFirstLetter(String source) {
		String upcase = source.substring(0, 1).toUpperCase();
		return upcase + source.substring(1);
	}

	private String lowercaseFirstLetter(String source) {
		String lowercase = source.substring(0, 1).toLowerCase();
		return lowercase + source.substring(1);
	}

	private List<String> getIncludeTables(String property) {
		List<String> ret = null;
		if (property != null && property.length() > 0) {
			ret = Arrays.asList(property.split(","));
		}
		return ret;
	}

	private void mapperTable(String tableName, Connection con, String namespacePac, String entityPac,
			String sessionFactoryBeanName, String modelPackage, String servicePackage, String controllerPackage) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT DISTINCT c.column_name, c.DATA_TYPE, (SELECT col_description(a.attrelid,a.attnum) as comment FROM pg_class as pgc,pg_attribute as a where pgc.relname = c.TABLE_NAME and a.attrelid = pgc.oid and a.attnum>0 and a.attname=C.COLUMN_NAME),c.IS_NULLABLE,c.CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS c WHERE c.table_name='"
							+ tableName + "'");
			System.out.println("========================" + tableName + "===============================");
			List<Column> columns = new ArrayList<Column>();
			while (rs.next()) {
				columns.add(this.getColumn(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5)));
			}
			ResultSet keyRs = st.executeQuery(
					"SELECT pg_attribute.attname AS colname FROM pg_constraint INNER JOIN pg_class ON pg_constraint.conrelid = pg_class.oid INNER JOIN pg_attribute ON pg_attribute.attrelid = pg_class.oid AND pg_attribute.attnum = pg_constraint.conkey [ 1 ] INNER JOIN pg_type ON pg_type.oid = pg_attribute.atttypid WHERE pg_class.relname = '"
							+ tableName + "' AND pg_constraint.contype = 'p'");
			String keyColumn = null;
			if (keyRs.next()) {
				keyColumn = keyRs.getString(1).toLowerCase();
			}

			ResultSet tableCommentRs = st.executeQuery(
					"select cast(obj_description(relfilenode,'pg_class') as varchar) as tbcomment from pg_class c where  relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%'  and relname ='"
							+ tableName + "'");
			String tableComment = null;
			if (tableCommentRs.next()) {
				tableComment = tableCommentRs.getString(1);
			}

			Table table = this.createTable(namespacePac, entityPac, tableName, columns, keyColumn, tableComment,
					sessionFactoryBeanName, modelPackage, servicePackage, controllerPackage);

			this.createMapperFile(table);
			this.createEntityFile(table);
			this.createDaoFile(table);
			this.createModelFile(table);
			this.createServicelFile(table);
			this.createInterfaceFile(table);
			this.createControllerFile(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createInterfaceFile(Table table) {
		try {
			String templateFileName = "interfaceTemplate.ftl";
			Template template = this.getTemplateCfg().getTemplate(templateFileName);
			File xmlFile = new File(getPath(prop.getProperty("interface.location")) + "/"
					+ prop.getProperty("package.modular") + "/" + "I" + table.getServiceName() + ".java");
			if (!xmlFile.exists())
				xmlFile.getParentFile().mkdirs();
			Writer out = new FileWriter(xmlFile);
			template.process(table, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPath(String module) {
		File currentlocation = new File(currentLocation);
		String parentName = currentlocation.getParent() + "/" + module + "/";
		parentName = parentName.replaceAll("\\\\", "/");
		System.out.println("输出至：" + parentName);
		return parentName;
	}

	private void createControllerFile(Table table) {
		try {
			String templateFileName = "controllerTemplate.ftl";
			Template template = this.getTemplateCfg().getTemplate(templateFileName);
			File xmlFile = new File(getPath(prop.getProperty("controller.location")) + "/"
					+ prop.getProperty("package.modular") + "/" + table.getControllerName() + ".java");
			if (!xmlFile.exists())
				xmlFile.getParentFile().mkdirs();
			Writer out = new FileWriter(xmlFile);
			template.process(table, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createServicelFile(Table table) {
		try {
			String templateFileName = "serviceTemplate.ftl";
			Template template = this.getTemplateCfg().getTemplate(templateFileName);
			File xmlFile = new File(getPath(prop.getProperty("service.location")) + "/"
					+ prop.getProperty("package.modular") + "/" + table.getServiceName() + ".java");
			if (!xmlFile.exists())
				xmlFile.getParentFile().mkdirs();
			Writer out = new FileWriter(xmlFile);
			template.process(table, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createModelFile(Table table) {
		try {
			String templateFileName = "modelTemplate.ftl";
			Template template = this.getTemplateCfg().getTemplate(templateFileName);
			File file = new File(getPath(prop.getProperty("model.location")) + "/" + prop.getProperty("package.modular")
					+ "/" + table.getModelName() + ".java");
			if (!file.exists())
				file.getParentFile().mkdirs();
			Writer out = new FileWriter(file);
			template.process(table, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createDaoFile(Table table) {
		try {
			// 读的dao
			String readDao = "daoTemplate.ftl";
			Template template1 = this.getTemplateCfg().getTemplate(readDao);
			File javaFile = new File(getPath(prop.getProperty("dao.location")) + "/"
					+ prop.getProperty("package.modular") + "/" + table.getDaoName() + ".java");
			if (!javaFile.exists())
				javaFile.getParentFile().mkdirs();
			Writer out1 = new FileWriter(javaFile);
			template1.process(table, out1);
			out1.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createEntityFile(Table table) {
		try {
			String templateFileName = "entityTemplate.ftl";
			Template template = this.getTemplateCfg().getTemplate(templateFileName);
			File javaFile = new File(getPath(prop.getProperty("entity.location")) + "/"
					+ prop.getProperty("package.modular") + "/" + table.getEntityName() + ".java");
			if (!javaFile.exists())
				javaFile.getParentFile().mkdirs();
			Writer out = new FileWriter(javaFile);
			template.process(table, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Table createTable(String namespacePac, String entityPac, String tableName, List<Column> columns,
			String keyColumn, String tableComment, String sessionFactoryBeanName, String modelPackage,
			String servicePackage, String controllerPackage) {
		Table table = new Table();
		table.setColumns(columns);
		if (keyColumn != null) {
			table.setKeyColumn(keyColumn);
		}

		table.setNamespacePackage(namespacePac);
		table.setDaoName(this.getBeanNameFromTalbeName(tableName) + "Dao");
		table.setResultMapId(this.getBeanNameFromTalbeName(tableName) + "Result");
		table.setEntityPackage(entityPac);
		table.setEntityName(this.getBeanNameFromTalbeName(tableName));
		table.setTableName(tableName);
		table.setTableComment(tableComment);
		table.setModelName(this.getBeanNameFromTalbeName(tableName) + "Model");
		table.setModelPackage(modelPackage);
		table.setServiceName(this.getBeanNameFromTalbeName(tableName) + "Service");
		table.setControllerName(this.getBeanNameFromTalbeName(tableName) + "Controller");
		table.setControllerPackage(controllerPackage);
		table.setServicePackage(servicePackage);
		return table;
	}

	private Configuration getTemplateCfg() {
		try {
			// Initialize configuration;
			Configuration cfg = new Configuration();

			cfg.setDirectoryForTemplateLoading(new File(this.getTemplateLocation()));
			cfg.setTemplateUpdateDelay(0);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			// Use beans wrapper (recommmended for most applications)
			cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
			cfg.setDefaultEncoding("UTF-8");
			// charset of the output
			cfg.setOutputEncoding("UTF-8");
			// default locale
			cfg.setLocale(Locale.US);
			return cfg;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getTemplateLocation() {
		try {
			return this.getClass().getResource("/").getPath() + "template";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * mapper file
	 * 
	 * @param table
	 */
	private void createMapperFile(Table table) {
		try {
			// readMapper
			String templateFileName = "sqlmapperTemplate.ftl";
			Template template = this.getTemplateCfg().getTemplate(templateFileName);

			File mapperFile = new File(
					getPath(prop.getProperty("db.sqlmapper.location") + "/" + prop.getProperty("package.modular"))
							+ this.getBeanNameFromTalbeName(table.getTableName() + "Mapper.xml"));
			if (!mapperFile.exists())
				mapperFile.getParentFile().mkdirs();
			Writer out = new FileWriter(mapperFile);
			template.process(table, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Column getColumn(String dbColumn, String colType, String colComment, String isNullable,
			String characterMaximumLength) {
		Column col = new Column();
		Integer max = 255;
		col.setDbColumn(this.getDbColumn(dbColumn));
		col.setBeanField(this.getBeanFieldFromDbColumn(dbColumn));
		if (javaSqlTypeTransferMap.get(colType) == null || "".equals(javaSqlTypeTransferMap.get(colType))) {
			System.out.println("colType=----------------------------" + colType + "--------------" + dbColumn);
		}
		col.setColJavaType(javaSqlTypeTransferMap.get(colType));
		col.setColDbType(colType);
		col.setColComment(colComment);
		col.setIsNullable(isNullable);
		try {
			max = Integer.valueOf((characterMaximumLength == null || "".equals(characterMaximumLength)) ? "0"
					: characterMaximumLength);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		col.setCharacterMaximumLength(max);
		return col;
	}

	private String getDbColumn(String dbColumn) {
		String ret = null;
		if (dbColumn.indexOf("_") != -1) {
			ret = dbColumn.toLowerCase();
		} else {
			ret = dbColumn;
		}
		return ret;
	}

	private String getBeanFieldFromDbColumn(String dbColumn) {
		String ret = null;
		if (dbColumn.indexOf("_") != -1) {
			String[] pieces = dbColumn.toLowerCase().split("_");
			StringBuffer sb = new StringBuffer(pieces[0]);
			if (pieces.length > 1) {
				for (int i = 1; i < pieces.length; i++) {
					sb.append(String.valueOf(pieces[i].charAt(0)).toUpperCase()).append(pieces[i].substring(1));
				}
			}
			ret = sb.toString();
		} else {
			ret = dbColumn;
		}
		return ret;
	}

	private String getBeanNameFromTalbeName(String tableName) {
		String[] pieces = tableName.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < pieces.length; i++) {
			sb.append(String.valueOf(pieces[i].charAt(0)).toUpperCase()).append(pieces[i].substring(1));
		}
		return sb.toString();
	}

}
