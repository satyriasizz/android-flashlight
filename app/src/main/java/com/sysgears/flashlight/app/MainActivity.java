package com.sysgears.flashlight.app;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    private boolean light = false; // true, if a flashlight is on.

    private Camera camera; // used for flash managing.

    private ImageButton tumbler;

    /**
     * Calls when application starts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creates a new Camera object to access the first back-facing camera on the device.
        camera = Camera.open();
        // searches the tumbler by id (was set by IDE).
        tumbler = (ImageButton) findViewById(R.id.imageButton);
        switchLight(); // turns on the light after the application is started.
    }

    /**
     * onClick handler. Calls when user taps on button.
     *
     * @param v widget pressed
     */
    public void switchLightHandler(View v) {
        switchLight();
    }

    /**
     * Switches light.
     */
    private void switchLight() {
        Parameters param = camera.getParameters();
        param.setFlashMode(light ?
                Parameters.FLASH_MODE_OFF : Parameters.FLASH_MODE_TORCH);
        camera.setParameters(param);
        switchTumblerPic();
        light = !light; // switches the light state.
    }

    /**
     * Switches the button picture.
     */
    private void switchTumblerPic() {
        tumbler.setImageResource(light ? R.drawable.off : R.drawable.on);
    }

    /**
     * Calls when application closes.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.release();
        camera = null;
    }
} 