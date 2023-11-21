package com.example.bluetoothmoduletest

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.example.bluetoothmoduletest.databinding.FragmentSecondBinding
import com.example.bluetoothmoduletest.databinding.FragmentTruck1Binding
import com.example.bt_def.BluetoothConstants
import com.example.bt_def.bluetooth.BluetoothController
import org.json.JSONException
import org.json.JSONObject

class TruckFragment1 : Fragment(), BluetoothController.Listener {

    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var binding: FragmentTruck1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTruck1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            initBtAdapter()
            val pref = activity?.getSharedPreferences(BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
            bluetoothController = BluetoothController(btAdapter)
            val mac = pref?.getString(BluetoothConstants.MAC, "")
            bConnect.setOnClickListener {
                bluetoothController.connect(mac?: "" , this@TruckFragment1)

            }
            bBack.setOnClickListener {
                findNavController().navigate(R.id.action_truckFragment1_to_firstFragment)
                bluetoothController.closeConnection()
            }

        }
    }

    private fun initBtAdapter() {
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }

    override fun onReceive(message: String) {
        activity?.runOnUiThread {
            parseAndDisplayData(message)
//            binding.tvMessage3.text = message
            when(message){
                BluetoothController.BLUETOOTH_CONNECTED -> {
                    binding.bConnect.backgroundTintList = AppCompatResources
                        .getColorStateList(requireContext(), R.color.red)
                    binding.bConnect.text = "Disconnect"
                }
                BluetoothController.BLUETOOTH_NO_CONNECTED -> {
                    binding.bConnect.backgroundTintList = AppCompatResources
                        .getColorStateList(requireContext(), R.color.green)
                    binding.bConnect.text = "Connect"
                } else -> {
            }
            }
        }
    }
    private fun parseAndDisplayData(jsonData: String) {
        try {
            val json = JSONObject(jsonData)

            val temp1 = json.getInt("temperature1")
            val temp2 = json.getInt("temperature2")
            val temp3 = json.getInt("temperature3")
            val temp4 = json.getInt("temperature4")
            val temp5 = json.getInt("temperature1")
            val temp6 = json.getInt("temperature2")
            val temp7 = json.getInt("temperature3")
            val temp8 = json.getInt("temperature4")
            val temp9 = json.getInt("temperature1")
            val temp10 = json.getInt("temperature2")

            Log.d("MyApp", "Temperature1: $temp1")
            binding.i1TV1.text = "Temp \n $temp1 °C"
            binding.i1TV2.text = "Temp \n $temp2 °C"
            binding.i1TV3.text = "Temp \n $temp3°C"
            binding.i1TV4.text = "Temp \n $temp4 °C"
            binding.i1TV5.text = "Temp \n $temp5 °C"
            binding.i1TV6.text = "Temp \n $temp6 °C"
            binding.i1TV7.text = "Temp \n $temp7°C"
            binding.i1TV8.text = "Temp \n $temp8 °C"

            if (temp1 > 100) {
                binding.i1Wheel1.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel1.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp2 > 100) {
                binding.i1Wheel2.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel2.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp3 > 100) {
                binding.i1Wheel3.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel3.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp4 > 100) {
                binding.i1Wheel4.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel4.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp1 > 100) {
                binding.i1Wheel5.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel5.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp2 > 100) {
                binding.i1Wheel6.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel6.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp3 > 100) {
                binding.i1Wheel7.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel7.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }
            if (temp4 > 100) {
                binding.i1Wheel8.setImageResource(R.drawable.wheel_view_top_red1_preview_rev_1)
            } else {
                binding.i1Wheel8.setImageResource(R.drawable.wheel_view_top1_preview_rev_1)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}