package com.sigvetl.twoDog.mapper;

import com.sigvetl.twoDog.model.Beer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BeerMapper {
    @Select("SELECT * FROM BEER " +
            "ORDER BY NAME")
    List<Beer> getAllBeers();

    @Select("SELECT * FROM BEER WHERE beerid=#{beerId}")
    Integer getBeer(Beer beer);

    @Select("SELECT * FROM BEER WHERE beerid=#{beerId}")
    Beer getBeerById(Integer beerId);

    @Select("SELECT * FROM BEER WHERE name=#{name}")
    Beer getBeerByName(Beer beer);

    @Insert("INSERT INTO BEER(NAME, TYPE, USERID, IBU, RECIPELINK, CREATEDBY, LASTUPDATEDBY, abv, ebc, og, fg, picturelink, usercreated) " +
            "VALUES(#{name}, #{type}, #{userId}, #{ibu}, #{recipeLink}, #{createdBy}, #{lastUpdatedBy}," +
            " #{abv}, #{ebc}, #{og}, #{fg}, #{pictureLink}, #{userCreated})")
    @Options(useGeneratedKeys = true, keyProperty = "beerId")
    void insertBeer(Beer beer);

    @Update("UPDATE BEER SET NAME = #{name}, TYPE = #{type}, USERID = #{userId}, IBU = #{ibu}, " +
            "RECIPELINK = #{recipeLink}, LASTUPDATEDBY = #{lastUpdatedBy}, abv = #{abv}, ebc = #{ebc}," +
            "og = #{og}, fg = #{fg}, picturelink = #{pictureLink}, usercreated = #{userCreated} WHERE BEERID = #{beerId}")
    void updateBeer(Beer beer);

    @Delete("DELETE FROM BEER WHERE BEERID = #{beerId}")
    void deleteBeer(Integer beerId);

}
