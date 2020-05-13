package com.hl.qbb.dao.product.service.mapper;

import com.hl.qbb.dao.product.api.dataobject.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Hailin
 * @date 2020/2/25
 */
@Mapper
public interface ProductMapper {

    @Update("UPDATE tb_product SET num = #{num} WHERE id = #{id}")
    Integer update(ProductDO productDO);

    @Select("SELECT * FROM tb_product WHERE id = #{id}")
    ProductDO get(Long id);
}
