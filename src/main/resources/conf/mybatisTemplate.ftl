<?xml version="1.0" encoding="UTF-8"?><!--Converted at: Mon Aug 20 17:02:06 CST 2012-->
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.xxx.mapper.${className}Mapper">

    <resultMap id="${resultMap}" type="com.xxx.po.${className}PO">
  <#list propertyList as property>
      <result property="${property.property}" column="${property.column}"/>
  </#list>
    </resultMap>


    <sql id="Base_Column_List">
        <trim suffixOverrides=",">
    <#list propertyList as property>
        `${property.column}`,
    </#list>
        </trim>
    </sql>

    <insert id="addEntity" parameterType="com.xxx.po.${className}PO">
        insert into `${tableName}`
        <trim prefix="(" suffix=")" suffixOverrides=",">
           <#list propertyList as property>
               <if test="${property.property} != null">${property.column},</if>
           </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
           <#list propertyList as property>
               <if test="${property.property} != null">${"#"}${"{"}${property.property}${"}"},</if>
           </#list>
        </trim>
    </insert>


    <update id="updateEntity" parameterType="com.xxx.po.${className}PO">
        update `${tableName}`
        <set>
    <#list propertyList as property>
        <if test="${property.property} != null">
            ${property.column}=${'#'}${'{'}${property.property}${'}'},
        </if>
    </#list>
        </set>
        <where>
            id = ${'#'}${'{'}id${'}'}
        </where>
    </update>

</mapper>
