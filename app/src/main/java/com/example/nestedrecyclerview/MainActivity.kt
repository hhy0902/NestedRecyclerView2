package com.example.nestedrecyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nestedrecyclerview.adapter.DeviceAdapter
import com.example.nestedrecyclerview.adapter.RoomAdapter
import com.example.nestedrecyclerview.databinding.ActivityMainBinding
import com.example.nestedrecyclerview.databinding.DeviceItemBinding
import com.example.nestedrecyclerview.databinding.RoomItemBinding
import com.example.nestedrecyclerview.model.Device
import com.example.nestedrecyclerview.model.Room

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var i = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var goData = ""
        val roomList = mutableListOf<Room>()

        roomList.add(Room("room 1"))
        val roomAdapter = RoomAdapter(this, roomList, goData)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = roomAdapter

        binding.button.setOnClickListener {
            i = roomList.size + 1
            roomList.add(Room("room $i"))
            roomAdapter.notifyItemInserted(roomList.size)
            //roomAdapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener {
            //Log.d("asdf",DeviceItemBinding.inflate(layoutInflater).deviceEditText.toString())
            //binding.dataText.text = DeviceItemBinding.inflate(layoutInflater).deviceEditText.toString()
            //binding.dataText.text = roomAdapter.goRoomData

            Log.d("asdf main", roomAdapter.goRoomData)
            Log.d("asdf main", roomAdapter.spinnerData)

            Log.d("asdf main", roomAdapter.sbData.toString())
            Log.d("asdf main", roomAdapter.sbSpinner.toString())

            binding.dataText.text = roomAdapter.sbData.toString()
            binding.dataText2.text = roomAdapter.sbSpinner.toString()

        }
    }

}



































































