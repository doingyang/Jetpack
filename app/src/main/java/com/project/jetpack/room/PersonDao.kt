package com.project.jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Dao类是一个接口，需要使用@Dao注解修饰，
 * 项目build之后，Room会生成对应的Impl类。
 * 在Dao工具类中定义出所有的数据库操作，且使用对应的注解修饰：
 * @Insert：插入数据；
 * @Delete：删除数据；
 * @Update：更新数据；
 * @Query：查找数据，此注解需要传入SQL语句作为参数，SQL语句用来限定查询的条件，注意如果是按条件删除数据等操作，也可以使用此注解来修饰。
 */
@Dao
interface PersonDao {

    @Insert
    fun insertPerson(p: Person?)

    @Delete
    fun deletePerson(p: Person?)

    @Query("DELETE FROM ${tableName} WHERE id = :id")
    fun deletePerson(id: Int)

    @Update
    fun updatePerson(p: Person?)

    @Query("SELECT * FROM ${tableName}")
    fun queryPersons(): List<Person?>?

    @Query("SELECT * FROM ${tableName} WHERE id = :id")
    fun queryPerson(id: Int): Person?

    @Query("SELECT * FROM ${tableName}")
    fun queryPersons2(): LiveData<List<Person?>>?
}