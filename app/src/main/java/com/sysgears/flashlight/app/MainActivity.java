package com.sysgears.flashlight.app;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {

    private boolean light = false; // true, if flashlight is on.

    private Camera camera; // we will use camera for flashes handle.

    private ImageButton tumbler;

    /**
     * Calls, then application starts.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera = Camera.open(); // creates a new Camera object to access camera.
        tumbler = (ImageButton) findViewById(R.id.imageButton); // Finds our tumbler by id (was set by IDE).
        switchLight(); // turn on the light, then started.
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
        light = !light; // switch light state.
    }

    /**
     * Switches the button picture.
     */
    private void switchTumblerPic() {
        tumbler.setImageResource(light ? R.drawable.off : R.drawable.on);
    }

    /**
     * Calls, then application close.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.release();
        camera = null;
    }
}
