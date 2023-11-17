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
import com.example.bluetoothmoduletest.databinding.FragmentFirstBinding
import com.example.bluetoothmoduletest.databinding.FragmentMainBinding
import com.example.bt_def.BluetoothConstants
import com.example.bt_def.bluetooth.BluetoothController
import org.json.JSONException
import org.json.JSONObject

class FirstFragment : Fragment(), BluetoothController.Listener {

    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            initBtAdapter()
            val pref = activity?.getSharedPreferences(BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
            bluetoothController = BluetoothController(btAdapter)
            val mac = pref?.getString(BluetoothConstants.MAC, "")
            connect1.setOnClickListener {
                bluetoothController.connect(mac?: "" , this@FirstFragment)

            }
            button2.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_mainFragment)
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
            binding.tvMessage.text = message
            when(message){
                BluetoothController.BLUETOOTH_CONNECTED -> {
                    binding.connect1.backgroundTintList = AppCompatResources
                        .getColorStateList(requireContext(), R.color.red)
                    binding.connect1.text = "Disconnect"
                }
                BluetoothController.BLUETOOTH_NO_CONNECTED -> {
                    binding.connect1.backgroundTintList = AppCompatResources
                        .getColorStateList(requireContext(), R.color.green)
                    binding.connect1.text = "Connect"
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
            binding.tvMessage2.text = "Temp \n $temp1 °C"
            binding.tvMessage3.text = "Temp \n $temp2 °C"
            binding.tvMessage4.text = "Temp \n $temp3°C"
            binding.tvMessage5.text = "Temp \n $temp4 °C"
            binding.tvMessage6.text = "Temp \n $temp5 °C"
            binding.tvMessage7.text = "Temp \n $temp6 °C"
            binding.tvMessage8.text = "Temp \n $temp7°C"
            binding.tvMessage9.text = "Temp \n $temp8 °C"
            binding.tvMessage10.text = "Temp \n $temp9 °C"
            binding.tvMessage11.text = "Temp \n $temp10 °C"

            if (temp1 > 100) {
                binding.overlayImage.setImageResource(R.drawable.wheel_view_top_red)
            } else {
                binding.overlayImage.setImageResource(R.drawable.wheel_view_top)
            }
            if (temp2 > 100) {
                binding.overlayImage2.setImageResource(R.drawable.wheel_view_top_red)
            } else {
                binding.overlayImage2.setImageResource(R.drawable.wheel_view_top)
            }
            if (temp3 > 100) {
                binding.overlayImage3.setImageResource(R.drawable.wheel_view_top_red)
            } else {
                binding.overlayImage3.setImageResource(R.drawable.wheel_view_top)
            }
            if (temp4 > 100) {
                binding.overlayImage4.setImageResource(R.drawable.wheel_view_top_red)
            } else {
                binding.overlayImage4.setImageResource(R.drawable.wheel_view_top)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}