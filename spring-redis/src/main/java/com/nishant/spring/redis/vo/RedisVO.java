package com.nishant.spring.redis.vo;

public class RedisVO {
	
	private String keyname;
	private String databasename;
	private String databaseversion;
	
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public String getDatabasename() {
		return databasename;
	}
	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}
	public String getDatabaseversion() {
		return databaseversion;
	}
	public void setDatabaseversion(String databaseversion) {
		this.databaseversion = databaseversion;
	}

}
