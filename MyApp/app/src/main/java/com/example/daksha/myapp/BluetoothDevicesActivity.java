package com.example.daksha.myapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Set;

public class BluetoothDevicesActivity extends AppCompatActivity {

    private TextView tvDevicesList;
    private final int REQUEST_ENABLE_BT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_devices_layout);

        tvDevicesList = (TextView) findViewById(R.id.tvDevicesList);
    }

    @Override
    protected void onStart() {
        super.onStart();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            tvDevicesList.setText("This Device Doesn't Support Bluetooth");
        } else if (!mBluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    tvDevicesList.setText("Enabled");
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        //List<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        StringBuilder str = new StringBuilder();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                str.append(device.getName() + "\n" + device.getAddress());
                tvDevicesList.setText(str);
            }
        }

    }

}
