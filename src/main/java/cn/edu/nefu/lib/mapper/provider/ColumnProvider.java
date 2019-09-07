package cn.edu.nefu.lib.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Classname ColumnProvider
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-04 9:05 PM
 */
public class ColumnProvider {

    public String getCabinetColumnListByFloor(@Param("floor") int floor) {
        return new SQL(){
            {
                SELECT("id, floor, col, count");
                FROM("`column`");
                WHERE("floor=#{floor}");
            }
        }.toString();
    }
}
