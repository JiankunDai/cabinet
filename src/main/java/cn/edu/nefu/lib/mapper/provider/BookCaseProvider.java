package cn.edu.nefu.lib.mapper.provider;

import cn.edu.nefu.lib.domain.BookCase;
import cn.edu.nefu.lib.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class BookCaseProvider {

    public String selectByStudentId(Student student) {
        return new SQL() {
            {
                SELECT("bc_system_id as systemId , bc_location as location, bc_number as number," +
                        "bc_student_id as studentId, bc_status as status");
                FROM("bookcase");
                WHERE("bc_student_id=#{studentId}");
            }
        }.toString();
    }

    public String selectByLocation(@Param("location") String location) {
        return new SQL() {
            {
                SELECT("bc_system_id as systemId , bc_location as location, bc_number as number," +
                        "bc_student_id as studentId, bc_status as status");
                FROM("bookcase");
                WHERE("bc_location=#{location}");
            }
        }.toString();
    }

}


