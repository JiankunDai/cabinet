package cn.edu.nefu.lib.mapper;

import cn.edu.nefu.lib.domain.Config;
import cn.edu.nefu.lib.mapper.provider.ConfigProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConfigMapper {

    /**
     * 查询开放区域
     *
     * @return 数组<config>
     */
    @SelectProvider(type = ConfigProvider.class, method = "selectOpenArea")
    List<Config> selectOpenArea();

    /**
     * 查询预约时间
     *
     * @return 返回list
     */
    @SelectProvider(type = ConfigProvider.class, method = "selectOpenTime")
    List<Config> selectOpenTime();

    /**
     * 修改
     *
     * @param config
     * @return int
     */
    @UpdateProvider(type = ConfigProvider.class, method = "update")
    int update(Config config);

    /**
     * 修改开放区域
     *
     * @param config
     * @return 修改的个数
     */
    @UpdateProvider(type = ConfigProvider.class, method = "selectOpenAreaBySystemId")
    int selectOpenAreaBySystemId(Config config);

    /**
     * 修改开始和结束年级年级
     *
     * @param config
     * @return 结果数量
     */
    @UpdateProvider(type = ConfigProvider.class, method = "updateGrade")
    int updateGrade(Config config);

    /**
     * 查询开放时间
     *
     * @return config
     */
    @SelectProvider(type = ConfigProvider.class, method = "selectStartTime")
    Config selectStartTime();

    /**
     * 查询开放年级
     *
     * @param config
     * @return config 实例
     */
    @SelectProvider(type = ConfigProvider.class, method = "selectOpenGrade")
    Config selectOpenGrade(Config config);

    /**
     * 查询结束时间
     *
     * @return Config
     */
    @SelectProvider(type = ConfigProvider.class, method = "selectEndTime")
    Config selectEndTime();

    @Select("select config_value from config where config_key = 'startTime'")
    Object getStartTime();

    @Select("select config_value from config where config_key = 'endTime'")
    Object getEndTime();
}
