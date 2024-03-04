package ru.spbstu.mobileapplication.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.spbstu.mobileapplication.di.data.DatabaseModule
import ru.spbstu.mobileapplication.di.data.NetworkModule
import ru.spbstu.mobileapplication.di.data.RepositoryModule
import ru.spbstu.mobileapplication.presentation.authorization_activity.MainActivity
import ru.spbstu.mobileapplication.presentation.authorization_activity.fragments.RestoreAccessFragment
import ru.spbstu.mobileapplication.presentation.authorization_activity.fragments.SignInFragment
import ru.spbstu.mobileapplication.presentation.authorization_activity.fragments.SignUpFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Data
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        // ViewModel
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: RestoreAccessFragment)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: SignUpFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
