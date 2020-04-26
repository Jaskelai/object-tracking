package com.github.jaskelai.object_tracking.data.mapper

import com.github.jaskelai.object_tracking.domain.model.label.PhotoLabel
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel
import javax.inject.Inject

class FirebaseVisionImageLabelMapper @Inject constructor() {

    fun execute(label: FirebaseVisionImageLabel): PhotoLabel {
        return PhotoLabel(
            name = label.text,
            entityId = label.entityId,
            confidence = label.confidence
        )
    }
}
