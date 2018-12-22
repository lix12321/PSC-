package cn.wellcare.codegenerator.mapperFactory.pojo;

import java.util.List;

public class Table {

	private String namespacePackage;
	private String entityPackage;
	private String daoName;
	private String entityName;
	private String resultMapId;
	private String keyColumn;
	private String tableName;
	private List<Column> columns;
	private String tableComment;
	private String modelName;
	private String modelPackage;
	private String serviceName;
	private String servicePackage;
	private String controllerPackage;
	private String controllerName;

	public String getNamespacePackage() {
		return this.namespacePackage;
	}

	public void setNamespacePackage(String namespacePackage) {
		this.namespacePackage = namespacePackage;
	}

	public String getEntityPackage() {
		return this.entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getDaoName() {
		return this.daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getResultMapId() {
		return this.resultMapId;
	}

	public void setResultMapId(String resultMapId) {
		this.resultMapId = resultMapId;
	}

	public String getKeyColumn() {
		return this.keyColumn;
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Column> getColumns() {
		return this.columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getTableComment() {
		return this.tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelPackage() {
		return this.modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getControllerPackage() {
		return this.controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getControllerName() {
		return this.controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

}
