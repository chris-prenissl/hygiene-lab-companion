package com.christophprenissl.hygienecompanion.presentation.view.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.presentation.view.component.field.ParameterText
import com.christophprenissl.hygienecompanion.presentation.view.util.translation
import com.christophprenissl.hygienecompanion.util.HAS_CERTIFICATE
import com.christophprenissl.hygienecompanion.util.NAME
import com.christophprenissl.hygienecompanion.util.QM_SAMPLER

@Composable
fun SamplerCard(
    user: User
) {
    val hasCertificate = user.hasCertificate
    val isSamplerOfInstitute = user.isSamplerOfInstitute
    BasicCard {
        Column {
            ParameterText(
                title = NAME,
                value = user.name
            )
            ParameterText(
                title = HAS_CERTIFICATE,
                value = hasCertificate.translation()
            )
            ParameterText(
                title = QM_SAMPLER,
                value = isSamplerOfInstitute.translation()
            )
        }
    }
}
