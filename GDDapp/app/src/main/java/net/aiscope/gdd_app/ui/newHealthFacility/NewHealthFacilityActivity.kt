package net.aiscope.gdd_app.ui.newHealthFacility

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.aiscope.gdd_app.R
import net.aiscope.gdd_app.application.GddApplication
import net.aiscope.gdd_app.repository.HospitalRepository
import net.aiscope.gdd_app.repository.SharedPreferencesRepository
import javax.inject.Inject


class NewHealthFacilityActivity : AppCompatActivity() {
    @Inject lateinit var presenter: NewHealthFacilityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_health_facility)
        (application as GddApplication).gddComponent.inject(this)
        presenter.setView(this)

        val saveButton = findViewById<Button>(R.id.button_save_new_health_facility)
        val cancelButton = findViewById<Button>(R.id.button_cancel_new_health_facility)
        val healthFacilityText = findViewById<EditText>(R.id.text_health_facility_name_field)

        saveButton.setOnClickListener {
            handleSaveButtonClick(healthFacilityText)
        }

        cancelButton.setOnClickListener {
            this.finish()
        }

    }

    private fun handleSaveButtonClick(healthFacilityText: EditText) {
        val message =
            if (healthFacilityText.text.isNotEmpty()) {
                presenter.saveHospital(healthFacilityText.text.toString())
                presenter.showToast(R.string.confirmation_message_health_facility_saved)
            } else {
                presenter.showToast(R.string.error_message_field_empty)

            }
    }

}
