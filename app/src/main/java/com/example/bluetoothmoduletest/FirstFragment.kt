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

class FirstFragment : Fragment(){

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
            bBack.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_mainFragment)
                bluetoothController.closeConnection()
            }
            bTruck1.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_truckFragment1)
                bluetoothController.closeConnection()
            }
            bTruck2.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_truckFragment2)
                bluetoothController.closeConnection()
            }
            bTruck3.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_truckFragment6)
                bluetoothController.closeConnection()
            }

        }
    }
    private fun initBtAdapter() {
        val bManager = activity?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        btAdapter = bManager.adapter
    }

}