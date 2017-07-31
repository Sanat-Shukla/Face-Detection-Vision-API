package com.facelandmark;

import android.graphics.PointF;

import com.facelandmark.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sanat.Shukla on 31/07/17.
 */

public class FaceTracker extends Tracker<Face> {
    private GraphicOverlay mOverlay;
    private FaceGraphic faceGraphic;
    // Record the previously seen proportions of the landmark locations relative to the bounding box
    // of the face.  These proportions can be used to approximate where the landmarks are within the
    // face bounding box if the eye landmark is missing in a future update.
    private Map<Integer, PointF> mPreviousProportions = new HashMap<>();

    public FaceTracker(GraphicOverlay overlay) {
        mOverlay = overlay;
    }

    @Override
    public void onNewItem(int i, Face face) {
        faceGraphic = new FaceGraphic(mOverlay);
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        mOverlay.add(faceGraphic);
        updatePreviousProportions(face);
        LandmarkModel landmarkModel = new LandmarkModel();
        landmarkModel.setLeftEye(getLandmarkPosition(face, Landmark.LEFT_EYE));
        landmarkModel.setRightEye(getLandmarkPosition(face, Landmark.RIGHT_EYE));

        landmarkModel.setLeftCheek(getLandmarkPosition(face, Landmark.LEFT_CHEEK));
        landmarkModel.setRightCheek(getLandmarkPosition(face, Landmark.RIGHT_CHEEK));

        landmarkModel.setLeftEar(getLandmarkPosition(face, Landmark.LEFT_EAR));
        landmarkModel.setRightEar(getLandmarkPosition(face, Landmark.RIGHT_EAR));
        landmarkModel.setLeftEarTip(getLandmarkPosition(face, Landmark.LEFT_EAR_TIP));
        landmarkModel.setRightEarTip(getLandmarkPosition(face, Landmark.RIGHT_EAR_TIP));

        landmarkModel.setLeftMouth(getLandmarkPosition(face, Landmark.LEFT_MOUTH));
        landmarkModel.setRightMouth(getLandmarkPosition(face, Landmark.RIGHT_MOUTH));
        landmarkModel.setBottomMouth(getLandmarkPosition(face, Landmark.BOTTOM_MOUTH));

        landmarkModel.setNoseBase(getLandmarkPosition(face, Landmark.NOSE_BASE));

        faceGraphic.updateFaceData(landmarkModel);
    }

    private void updatePreviousProportions(Face face) {
        for (Landmark landmark : face.getLandmarks()) {
            PointF position = landmark.getPosition();
            float xProp = (position.x - face.getPosition().x) / face.getWidth();
            float yProp = (position.y - face.getPosition().y) / face.getHeight();
            mPreviousProportions.put(landmark.getType(), new PointF(xProp, yProp));
        }
    }

    /**
     * Finds a specific landmark position, or approximates the position based on past observations
     * if it is not present.
     */
    private PointF getLandmarkPosition(Face face, int landmarkId) {
        for (Landmark landmark : face.getLandmarks()) {
            if (landmark.getType() == landmarkId) {
                return landmark.getPosition();
            }
        }

        PointF prop = mPreviousProportions.get(landmarkId);
        if (prop == null) {
            return null;
        }

        float x = face.getPosition().x + (prop.x * face.getWidth());
        float y = face.getPosition().y + (prop.y * face.getHeight());
        return new PointF(x, y);
    }

    /**
     * Hide the graphic when the corresponding face was not detected.  This can happen for
     * intermediate frames temporarily (e.g., if the face was momentarily blocked from
     * view).
     */
    @Override
    public void onMissing(FaceDetector.Detections<Face> detectionResults) {
        mOverlay.remove(faceGraphic);
    }

    /**
     * Called when the face is assumed to be gone for good. Remove the googly eyes graphic from
     * the overlay.
     */
    @Override
    public void onDone() {
        mOverlay.remove(faceGraphic);
    }
}
