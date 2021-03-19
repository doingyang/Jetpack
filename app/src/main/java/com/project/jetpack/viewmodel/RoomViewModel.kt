package com.project.jetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.jetpack.room.Person
import com.project.jetpack.room.PersonDatabase

/**
 * 继承AndroidViewModel的，那么需要传入Factory去创建ViewModel
 * ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(RoomViewModel::class.java)
 */
class RoomViewModel(application: Application) : AndroidViewModel(application) {

    val mDatabase: PersonDatabase
    val mLiveData: LiveData<List<Person?>>?

    init {
        mDatabase = PersonDatabase.getInstance(application)
        mLiveData = mDatabase.personDao!!.queryPersons2()
    }

}