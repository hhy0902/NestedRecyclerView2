package com.example.nestedrecyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.databinding.RoomItemBinding
import com.example.nestedrecyclerview.model.Device
import com.example.nestedrecyclerview.model.Room

class RoomAdapter(context: Context, val roomList: MutableList<Room>, var goData : String) :
    RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    var context : Context? =  null
    var goRoomData = ""
    var spinnerData = ""
    var sbSpinner = StringBuffer()
    var sbData = StringBuffer()

    init {
        this.context = context
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val roomText : TextView
        val btn_add : ImageButton
        val btn_delete : ImageButton
        val device_rv : RecyclerView
        val btn_saveCheck : ImageButton

        init {

            roomText = itemView.findViewById(R.id.roomText)
            btn_add = itemView.findViewById(R.id.btn_add)
            btn_delete = itemView.findViewById(R.id.btn_del)
            device_rv = itemView.findViewById(R.id.device_recyclerView)
            btn_saveCheck = itemView.findViewById(R.id.btn_saveCheck)

            btn_delete.setOnClickListener {
                val position :Int = adapterPosition
                Log.d("asdf","delete room")

                roomList.removeAt(position)
                notifyItemRemoved(position)

                goRoomData = ""
                spinnerData = ""
                sbSpinner = StringBuffer()
                sbData = StringBuffer()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.room_item,parent,false))
    }

    override fun onBindViewHolder(holder: RoomAdapter.ViewHolder, position: Int) {
        holder.roomText.text = roomList.get(position).name
        val deviceList = mutableListOf<Device>()
        val deviceAdapter = DeviceAdapter(context!!, deviceList)
        var i = 0
        holder.device_rv.adapter = deviceAdapter
        holder.device_rv.layoutManager = LinearLayoutManager(context)

        holder.btn_add.setOnClickListener {
            i = deviceList.size + 1
            deviceList.add(Device("D. $i",position))
            //deviceAdapter.notifyItemInserted(position)
            deviceAdapter.notifyDataSetChanged()
        }

        /*holder.btn_delete.setOnClickListener {
            Log.d("asdf","delete room")

            roomList.removeAt(position)
            notifyItemRemoved(position)

            goRoomData = ""
            spinnerData = ""
            sbSpinner = StringBuffer()
            sbData = StringBuffer()
        }
         */

        holder.btn_saveCheck.setOnClickListener {
            goRoomData = deviceAdapter.goData
            spinnerData = deviceAdapter.spinnerData

            sbData.append(goRoomData)
            sbSpinner.append(spinnerData)

            Log.d("asdf position ", position.toString())

            Log.d("asdf room",goRoomData)
            Log.d("asdf room",spinnerData)
            Log.d("asdf room",sbData.toString())
            Log.d("asdf room",sbSpinner.toString())
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}

// 에러 최소화
// ui에서는 한번에 하나만 해야 한다
// 룸 누르면 상세 페이지로 들어가는 방식

// main -> room -> device
// main    x <-    device

/*
room layoutㅇㅔ서 아무 동작없이 godata를 goroomdata로 바꾸기? 가능?

fun으로? ??
 */


















