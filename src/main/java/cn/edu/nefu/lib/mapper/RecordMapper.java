package cn.edu.nefu.lib.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Classname RecordMapper
 * @Description TODO
 * @auther daijiankun laptop
 * @create 2019-09-06 5:10 PM
 */

@Mapper
@Repository
public interface RecordMapper {
    @Insert("insert into record(studentid,`column`,floor) values(#{studentID},#{column},#{floor});")
    public void saveRecord(@Param("studentID") int studentID, @Param("column") int column, @Param("floor") int floor);


}
