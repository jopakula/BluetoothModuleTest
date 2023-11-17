package com.example.bluetoothmoduletest

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bluetoothmoduletest.databinding.FragmentMainBinding
import com.example.bt_def.BluetoothConstants
import com.example.bt_def.bluetooth.BluetoothController
import org.json.JSONException
import org.json.JSONObject

class MainFragment : Fragment(), BluetoothController.Listener {

    private lateinit var bluetoothController: BluetoothController
    private lateinit var btAdapter: BluetoothAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            initBtAdapter()
            val pref = activity?.getSharedPreferences(BluetoothConstants.PREFERENCES, Context.MODE_PRIVATE)
            bluetoothController = BluetoothController(btAdapter)
            val mac = pref?.getString(BluetoothConstants.MAC, "")
            btList.setOnClickListener {
                findNavController().navigate(R.id.listFragment)
            }
//            connect.setOnClickListener {
//                bluetoothController.connect(mac?: "" , this@MainFragment)
//
//            }
            overlayImage.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_firstFragment)
            }
            overlayImage2.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
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
//            when(message){
//                BluetoothController.BLUETOOTH_CONNECTED -> {
//                    binding.connect.backgroundTintList = AppCompatResources
//                        .getColorStateList(requireContext(), R.color.red)
//                    binding.connect.text = "Disconnect"
//                }
//                BluetoothController.BLUETOOTH_NO_CONNECTED -> {
//                    binding.connect.backgroundTintList = AppCompatResources
//                        .getColorStateList(requireContext(), R.color.green)
//                    binding.connect.text = "Connect"
//                } else -> {
//            }
//            }
        }
    }
    private fun parseAndDisplayData(jsonData: String) {
        try {
            val json = JSONObject(jsonData)

            val temp1 = json.getInt("temperature1")
            val temp2 = json.getInt("temperature2")
            val temp3 = json.getInt("temperature3")
            val temp4 = json.getInt("temperature4")

            Log.d("MyApp", "Temperature1: $temp1")
            binding.tvMessage2.text = "Temperature \n $temp1 °C"
            binding.tvMessage3.text = "Temperature \n $temp2 °C"
            binding.tvMessage4.text = "Temperature \n $temp3°C"
            binding.tvMessage5.text = "Temperature \n $temp4 °C"

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
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}