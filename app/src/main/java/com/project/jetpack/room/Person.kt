package com.project.jetpack.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

const val tableName = "person"

/**
 * @Entity：修饰类，将该实体类与数据库表对应起来，其中tableName参数值表示对应的表名；
 * @PrimaryKey：修饰属性：表示将该字段作为表中的主键，autoGenerate = true表示自动生成;
 * @ColumnInfo:修饰属性，将该属性与数据库表中字段对应，name参数表示数据库表中的字段名，typeAffinity表示字段类型；
 * @Ignore：修饰方法和属性，用来告诉Room忽略该字段或方法，由于Room只能识别Entity实体类的一个构造方法，所以如果实体类中需要有多个构造方法的时候，其余的需要使用@Ignore修饰。
 */
@Entity(tableName = tableName)
class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    var mId: Int? = null

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    var mName: String? = null

    @ColumnInfo(name = "grade", typeAffinity = ColumnInfo.REAL)
    var mGrade: Double? = null

    @ColumnInfo(name = "isMan", typeAffinity = ColumnInfo.INTEGER)
    var mIsMan: Boolean? = null

    @Ignore
    var mExtraInfo: String? = null

    constructor(name: String, grade: Double, isMan: Boolean) {
        mName = name
        mGrade = grade
        mIsMan = isMan
    }

    // Room只能识别一个构造器
    @Ignore
    constructor(name: String, grade: Double, isMan: Boolean, extraInfo: String?) {
        mName = name
        mGrade = grade
        mIsMan = isMan
        mExtraInfo = extraInfo
    }

    override fun toString(): String {
        return "Person(mId=$mId, mName='$mName', mGrade=$mGrade, mIsMan=$mIsMan, mExtraInfo=$mExtraInfo)"
    }
}