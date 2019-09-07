package cn.edu.nefu.lib.mapper;

import cn.edu.nefu.lib.domain.Student;
import cn.edu.nefu.lib.mapper.provider.StudentProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    @SelectProvider(type = StudentProvider.class, method = "selectByCondition")
    List<Student> selectByCondition(Student student);

    @Update("update student set type=1 where studentid=#{studentId} and type=0")
    void complete(Student student);

    @Update("update student set token=#{token} where studentid=#{studentId}")
    int updateTokenByStudentId(Student student);
}
