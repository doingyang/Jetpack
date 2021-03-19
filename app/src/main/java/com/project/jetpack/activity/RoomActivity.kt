package com.project.jetpack.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

import com.project.jetpack.R
import com.project.jetpack.room.Person
import com.project.jetpack.room.PersonDatabase
import com.project.jetpack.viewmodel.RoomViewModel

class RoomActivity : AppCompatActivity() {

    private val insert: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.insert) }
    private val deleteById: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.delete_by_id) }
    private val delete: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.delete) }
    private val update: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.update) }
    private val query: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.query) }
    private val queryById: AppCompatButton by lazy { findViewById<AppCompatButton>(R.id.query_by_id) }

    var i = 0
    var p: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        initEvent()
    }

    private fun initEvent() {
        insert.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                try {
                    p = Person("p$i", 10.1 + i, i % 2 == 0)
                    i++
                    PersonDatabase.getInstance(this@RoomActivity).personDao?.insertPerson(p)
                } catch (e: Exception) {
                }
            }
        }

        deleteById.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                PersonDatabase.getInstance(this@RoomActivity).personDao?.deletePerson(i)
            }
        }

        delete.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                PersonDatabase.getInstance(this@RoomActivity).personDao?.deletePerson(p)
            }
        }

        update.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                PersonDatabase.getInstance(this@RoomActivity).personDao?.updatePerson(p)
            }
        }

        query.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                val p = Person("new p$i", 10.1 + i, i % 2 == 0)
                val persons =
                    PersonDatabase.getInstance(this@RoomActivity).personDao?.queryPersons()
                if (persons != null && persons.isNotEmpty()) {
                    for (pp in persons) {
                        Log.i("TAG", "onCreate: " + pp.toString())
                    }
                }
            }
        }

        queryById.setOnClickListener {
            MainScope().launch(Dispatchers.IO) {
                val person = PersonDatabase.getInstance(this@RoomActivity).personDao?.queryPerson(i)
                if (person != null) {
                    Log.i("TAG", "onCreate: $person")
                }
            }
        }

        val personLiveData =
            PersonDatabase.getInstance(this@RoomActivity).personDao?.queryPersons2()
        personLiveData?.observe(this@RoomActivity, Observer<List<Person?>> { t ->
            Log.e("jia", "onChanged: " + t?.size)
        })

        var vm =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                RoomViewModel::class.java
            )
    }
}