/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbiometricsrecord;

/**
 *
 * @author Eleazar
 */
public class Generate {

    public String feesTableName(String toBeGenerate){
        String tableName;
        String tempTableName = toBeGenerate + "_fees_" + Authentication.userCourse;
        String subTableName = tempTableName.replace(" ", "");
        tableName = subTableName.replace("-", "_");
        return tableName;
    }

    public String eventsTableName(String toBeGenerate){
        String tableName;
        String tempTableName = toBeGenerate + "_events_" + Authentication.userCourse;
        String subTableName = tempTableName.replace(" ", "");
        tableName = subTableName.replace("-", "_");
        return tableName;
    }


}

