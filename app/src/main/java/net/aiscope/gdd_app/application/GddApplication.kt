package net.aiscope.gdd_app.application

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.smartlook.sdk.smartlook.Smartlook
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import net.aiscope.gdd_app.BuildConfig
import net.aiscope.gdd_app.dagger.DaggerAppComponent
import javax.inject.Inject

class GddApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun androidInjector() = androidInjector

    override fun onCreate() {
        super.onCreate()
        // initialize Dagger
        DaggerAppComponent.builder().application(this).build().inject(this)

        val conf = Configuration.Builder().setWorkerFactory(workerFactory).build()
        WorkManager.initialize(this, conf)

        Smartlook.setup(BuildConfig.SMARTLOOK_API_KEY)
        if (!BuildConfig.DEBUG) {
            Smartlook.startRecording()
            Smartlook.enableCrashlytics(true)
        }
    }
}
