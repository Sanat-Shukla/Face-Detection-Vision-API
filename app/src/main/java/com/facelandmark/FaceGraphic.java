package com.facelandmark;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import com.facelandmark.camera.GraphicOverlay;

/**
 * Created by Sanat.Shukla on 31/07/17.
 */

public class FaceGraphic extends GraphicOverlay.Graphic {

    private PointF leftEye;
    private PointF rightEye;
    private PointF leftCheek;
    private PointF rightCheek;
    private PointF leftEar;
    private PointF rightEar;
    private PointF leftEarTip;
    private PointF rightEarTip;
    private PointF leftMouth;
    private PointF rightMouth;
    private PointF bottomMouth;
    private PointF noseBase;

    public FaceGraphic(GraphicOverlay overlay) {
        super(overlay);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        if(leftEye!=null) {
            leftEye =
                    new PointF(translateX(leftEye.x), translateY(leftEye.y));
            canvas.drawCircle(leftEye.x, leftEye.y, 10, paint);
        }
        if(rightEye!=null) {
            rightEye =
                    new PointF(translateX(rightEye.x), translateY(rightEye.y));
            canvas.drawCircle(rightEye.x, rightEye.y, 10, paint);
        }
        if(leftCheek!=null) {
            leftCheek =
                    new PointF(translateX(leftCheek.x), translateY(leftCheek.y));
            canvas.drawCircle(leftCheek.x, leftCheek.y, 10, paint);
        }
        if(rightCheek!=null) {
            rightCheek =
                    new PointF(translateX(rightCheek.x), translateY(rightCheek.y));
            canvas.drawCircle(rightCheek.x, rightCheek.y, 10, paint);
        }
        if(leftEar!=null) {
            leftEar =
                    new PointF(translateX(leftEar.x), translateY(leftEar.y));
            canvas.drawCircle(leftEar.x, leftEar.y, 10, paint);
        }
        if(rightEar!=null) {
            rightEar =
                    new PointF(translateX(rightEar.x), translateY(rightEar.y));
            canvas.drawCircle(rightEar.x, rightEar.y, 10, paint);
        }
        if(leftEarTip!=null) {
            leftEarTip =
                    new PointF(translateX(leftEarTip.x), translateY(leftEarTip.y));
            canvas.drawCircle(leftEarTip.x, leftEarTip.y, 10, paint);
        }
        if(rightEarTip!=null) {
            rightEarTip =
                    new PointF(translateX(rightEarTip.x), translateY(rightEarTip.y));
            canvas.drawCircle(rightEarTip.x, rightEarTip.y, 10, paint);
        }
        if(leftMouth!=null) {
            leftMouth =
                    new PointF(translateX(leftMouth.x), translateY(leftMouth.y));
            canvas.drawCircle(leftMouth.x, leftMouth.y, 10, paint);
        }
        if(rightMouth!=null) {
            rightMouth =
                    new PointF(translateX(rightMouth.x), translateY(rightMouth.y));
            canvas.drawCircle(rightMouth.x, rightMouth.y, 10, paint);
        }
        if(bottomMouth!=null) {
            bottomMouth =
                    new PointF(translateX(bottomMouth.x), translateY(bottomMouth.y));
            canvas.drawCircle(bottomMouth.x, bottomMouth.y, 10, paint);
        }
        if(noseBase!=null) {
            noseBase =
                    new PointF(translateX(noseBase.x), translateY(noseBase.y));
            canvas.drawCircle(noseBase.x, noseBase.y, 10, paint);
        }
    }

    public void updateFaceData(LandmarkModel landmarkModel) {
        this.leftEye = landmarkModel.getLeftEye();
        this.rightEye = landmarkModel.getRightEye();
        this.leftCheek = landmarkModel.getLeftCheek();
        this.rightCheek = landmarkModel.getRightCheek();
        this.leftEar = landmarkModel.getLeftEar();
        this.rightEar = landmarkModel.getRightEar();
        this.leftEarTip = landmarkModel.getLeftEarTip();
        this.rightEarTip = landmarkModel.getRightEarTip();
        this.leftMouth = landmarkModel.getLeftMouth();
        this.rightMouth = landmarkModel.getRightMouth();
        this.bottomMouth = landmarkModel.getBottomMouth();
        this.noseBase = landmarkModel.getNoseBase();
        postInvalidate();
    }
}
