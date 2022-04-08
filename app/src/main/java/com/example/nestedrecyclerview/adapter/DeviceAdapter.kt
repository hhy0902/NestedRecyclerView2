package com.example.nestedrecyclerview.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.databinding.DeviceItemBinding
import com.example.nestedrecyclerview.model.Device
import java.security.Key

open class DeviceAdapter(val context: Context, var deviceList : MutableList<Device>) :
    RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    var goData = ""
    var spinnerData = ""

    inner class ViewHolder(itemView : View/*val binding: DeviceItemBinding*/) : RecyclerView.ViewHolder(itemView/*binding.root*/){
        val deviceText : TextView
        val btn_delete : ImageButton
        val deviceEdit : EditText
        val data : String
        val spinner : Spinner

        fun moveData(): String {
            goData = deviceEdit.text.toString()
            Toast.makeText(context,"$goData",Toast.LENGTH_SHORT).show()
            return goData
        }

        init {
            spinner = itemView.findViewById(R.id.spinner)
            deviceText = itemView.findViewById(R.id.deviceText)
            btn_delete = itemView.findViewById(R.id.btn_del)
            deviceEdit = itemView.findViewById(R.id.deviceEditText)
            data = deviceEdit.toString()

        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceAdapter.ViewHolder {
        return ViewHolder(return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.device_item,parent,false)))
    }

    override fun onBindViewHolder(holder: DeviceAdapter.ViewHolder, position: Int) {
        holder.deviceText.text = deviceList.get(position).name

        val spinnerAdapter = ArrayAdapter.createFromResource(holder.itemView.context,R.array.planets_array,android.R.layout.simple_spinner_item)
        holder.spinner.adapter = spinnerAdapter
        holder.spinner.onItemSelectedListener
        holder.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Log.d("asdf","${holder.spinner.getItemAtPosition(p2)}")
                spinnerData = holder.spinner.getItemAtPosition(p2).toString()
                Log.d("asdf","${spinnerData}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        holder.btn_delete.setOnClickListener {
            Log.d("asdf","delete device")
            deviceList.removeAt(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
        }

        holder.deviceEdit.doAfterTextChanged {
            Log.d("asdf","afterTextChanged : $it")
            goData = it.toString()
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }
}

// 에러 최소화
// ui에서는 한번에 하나만 해야 한다
// 룸 누르면 상세 페이지로 들어가는 방식

// 010101010101010101010101



















