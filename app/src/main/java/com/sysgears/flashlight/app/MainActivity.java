package com.sysgears.flashlight.app;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {

    private boolean light = false; // true, if flashlight is on.

    private Camera camera; // uses for flash managing.

    private ImageButton tumbler;

    /**
     * Called when application starts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = Camera.open(); // creates a new Camera object to access the first back-facing camera on the device.
        tumbler = (ImageButton) findViewById(R.id.imageButton); // searches the tumbler by id (was set by IDE).
        switchLight(); // turns on the light after the application has started.
    }

    /**
     * On click handler. Switches the light.
     *
     * @param v widget pressed
     */
    public void switchLightHandler(View v) {
        switchLight();
    }

    /**
     * Switches the light.
     */
    private void switchLight() {
        Parameters param = camera.getParameters();
        param.setFlashMode(light ? Parameters.FLASH_MODE_OFF : Parameters.FLASH_MODE_TORCH);
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
