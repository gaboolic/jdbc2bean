<?xml version="1.0" encoding="UTF-8"?><!--Converted at: Mon Aug 20 17:02:06 CST 2012-->
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${className}">

    <resultMap id="${resultMap}" type="${className}">
  <#list propertyList as property>
      <result property="${property.property}" column="${property.column}"/>
  </#list>
    </resultMap>

    <sql id="dynamic_where">
        <where>
            <if test="input != null and input.id != null">and
                ID=${'#'}${'{'}input.id ${'}'}
            </if>
    <#list propertyList as property>
      <if test="input != null and input.${property.property} != null">and
          ${property.column}= ${'#'}${'{'}${'input.'}${property.property}${'}'}
      </if>
    </#list>
        </where>
    </sql>

    <sql id="insertColumns">
        <trim suffixOverrides=",">
    <#list propertyList as property>
        <if test="${property.property} != null">${property.column},</if>
    </#list>
        </trim>
    </sql>

    <sql id="insertValues">
        <trim suffixOverrides=",">
    <#list propertyList as property>
        <if test="${property.property} != null">${"#"}${"{"}${property.property}${"}"},</if>
    </#list>
        </trim>
    </sql>

    <sql id="dynamic_select">
        <trim suffixOverrides=",">

    <#list propertyList as property>
        <if test="output != null and output.${property.property} != null">
            ${property.column} ${property.property},
        </if>
    </#list>
        </trim>
    </sql>

    <insert id="addEntity" parameterType="${className}">
        insert into  ${tableName}
        (
        <include refid="insertColumns"/>
        )
        values
        (
        <include refid="insertValues"/>
        )
    </insert>


    <select id="findEntityListByCond" parameterType="java.util.Map" resultMap="${resultMap}">
        select
        <include refid="dynamic_select"/>
        from ${tableName}
        <include refid="dynamic_where"/>
        <if test="input != null and input.forUpdate != null">
            for update
        </if>
    </select>

    <update id="updateEntity" parameterType="${className}">
        update ${tableName}
        <set>
    <#list propertyList as property>
        <if test="${property.property} != null">
            ${property.column}=${'#'}${'{'}${property.property}${'}'},
        </if>
    </#list>
        </set>
        <where>
            <if test="id != null">
                AND id = ${'#'}${'{'}id${'}'}
            </if>
        </where>
    </update>

</mapper>
