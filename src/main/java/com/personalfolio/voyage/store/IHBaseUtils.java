package com.personalfolio.voyage.store;

public interface IHBaseUtils {
    public  void put(String rowKey, String value, String columnFamily, String column) throws Exception;
    public  byte[] get(String rowKey,String columnFamily, String column)throws  Exception;
    public void scan(String columnFamily,String column) throws  Exception;
    public  void delete(String rowKey,String columnFamily,String column) throws  Exception;
}
