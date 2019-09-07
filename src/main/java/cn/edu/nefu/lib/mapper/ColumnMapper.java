package cn.edu.nefu.lib.mapper;

import cn.edu.nefu.lib.domain.CabinetColumn;
import cn.edu.nefu.lib.mapper.provider.ColumnProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ColumnMapper {

    @SelectProvider(type = ColumnProvider.class, method = "getCabinetColumnListByFloor")
    List<CabinetColumn> getCabinetColumnListByFloor(@Param("floor") int floor);


    @Select("select * from `column`")
    List<CabinetColumn> getAll();
}
