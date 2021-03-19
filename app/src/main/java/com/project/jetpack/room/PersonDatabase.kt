package com.project.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 此类主要是用来创建数据库
 * 继承自RoomDatabase类，并使用@Database注解;
 * 其中entities参数值是一数组，对应此数据库中的所有表;
 * version表示数据库版本号。
 *
 * PersonDatabase是一个抽象类，其中核心有两个方法:
 * 第一个就是获取PersonDatabase实例，一般情况下，整个项目中有一个PersonDatabase实例即可，所以我们通常使用单例模式。
 * 第二个就是获取其对应的Dao工具类。
 *
 * 数据库操作是耗时操作，所以Room强制要求所有的增删改查不能在主线程中执行，可使用Kotlin的协程来实现
 */
@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {

    abstract val personDao: PersonDao?

    companion object {
        private const val DATABASE_NAME = "person.db"
        private var mPersonDatabase: PersonDatabase? = null

        fun getInstance(context: Context): PersonDatabase {
            if (mPersonDatabase == null) {
                // 使用Room.databaseBuilder初始化Database对象，需要注意传入的Context是整个应用程序的Context。
                mPersonDatabase = Room
                    .databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        DATABASE_NAME
                    )
                    .fallbackToDestructiveMigration()   // 如果升级数据库但没有匹配到对应的Margation，程序就会奔溃，我们可以在初始化Database对象的时候，加入fallbackToDestructiveMigration()语句
                    .addMigrations(MIGRATION_1_2)       // 数据库升级
                    .build()
            }
            return mPersonDatabase!!
        }


        /**
         * Room提供了Migration类，用以实现数据库升级。
         * Migration类的构造方法接收两个参数：startVersion、endVersion
         * startVersion表示当前数据库版本（旧版本），endVersion为新版本
         *
         * 如果数据库版本号从1升级到3，系统会优先匹配1-3的Margation，如果没有的话就会依次执行 1-2，2-3。
         */
        private var MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // TODO: 2021/3/16 0016
                // 通过database的execSQL()方法，传入对应的SQL语句，即可对数据库进行升级管理
            }
        }

    }

}