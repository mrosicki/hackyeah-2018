package com.prototype.hackyeah2018.reader;

import android.util.Log;
import android.util.SparseArray;

import com.prototype.hackyeah2018.reader.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

public class ReaderDetectorProcessor implements Detector.Processor<TextBlock>  {

    private GraphicOverlay<ReaderGraphic> graphicOverlay;

    ReaderDetectorProcessor(GraphicOverlay<ReaderGraphic> readerGraphicOverlay) {
        graphicOverlay = readerGraphicOverlay;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("OcrDetectorProcessor", "Text detected! " + item.getValue());
                ReaderGraphic graphic = new ReaderGraphic(graphicOverlay, item);
                graphicOverlay.add(graphic);
            }
        }
    }

    @Override
    public void release() {
        graphicOverlay.clear();
    }

}
