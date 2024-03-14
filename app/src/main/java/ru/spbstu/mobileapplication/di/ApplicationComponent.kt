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
import ru.spbstu.mobileapplication.presentation.bottom_navigation.BottomNavigationActivity
import ru.spbstu.mobileapplication.presentation.interview.InterviewActivity
import ru.spbstu.mobileapplication.presentation.interview.fragments.ApartmentTypeFragment
import ru.spbstu.mobileapplication.presentation.interview.fragments.AreaFragment
import ru.spbstu.mobileapplication.presentation.interview.fragments.BudgetFragment
import ru.spbstu.mobileapplication.presentation.interview.fragments.PurposeFragment
import ru.spbstu.mobileapplication.presentation.interview.fragments.TermFragment
import ru.spbstu.mobileapplication.presentation.interview.fragments.city.CityFragment
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

    fun inject(activity: BottomNavigationActivity)
    //

    fun inject(activity: InterviewActivity)

    fun inject(fragment: AreaFragment)
    fun inject(fragment: BudgetFragment)
    fun inject(fragment: CityFragment)
    fun inject(fragment: PurposeFragment)
    fun inject(fragment: TermFragment)
    fun inject(fragment: ApartmentTypeFragment)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
