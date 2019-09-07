package cn.edu.nefu.lib.mapper;

import cn.edu.nefu.lib.domain.BookCase;
import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.provider.BookCaseProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookCaseMapper {

    /**
     * 通过用户ID获取书包柜信息
     *
     * @param student 学生ID
     * @return bookCase 书包柜实体
     */
    @SelectProvider(type = BookCaseProvider.class, method = "selectByStudentId")
    List<BookCase> selectByStudentId(Student student);

    //@Select("select bc_system_id,bc_location,bc_number,bc_studentId,bc_status from bookcase where bc_location=#{location}")
    @SelectProvider(type = BookCaseProvider.class, method = "selectByLocation")
    List<BookCase> selectByLocation(@Param("location") String location);

    @Update("update bookcase set bc_student_id=#{student.studentId},bc_status=1 where bc_system_id=#{bookCase.systemId} and bc_status=0;")
    boolean updateOwnerAndState(@Param("bookCase") BookCase bookCase, @Param("student") Student student);
}
